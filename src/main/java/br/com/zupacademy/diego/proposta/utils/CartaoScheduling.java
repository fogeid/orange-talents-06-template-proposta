package br.com.zupacademy.diego.proposta.utils;

import br.com.zupacademy.diego.proposta.integrations.CartaoIntegration;
import br.com.zupacademy.diego.proposta.models.Proposta;
import br.com.zupacademy.diego.proposta.models.StatusProposta;
import br.com.zupacademy.diego.proposta.repositories.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@EnableAsync
@EnableScheduling
public class CartaoScheduling {
    @Autowired
    private CartaoIntegration cartaoIntegration;

    @Autowired
    private PropostaRepository propostaRepository;

    @Scheduled(fixedDelay = 5000)
    private void associarCartao() {
        List<Proposta> propostas =  propostaRepository.findByStatusAndNumeroCartaoIsNull(StatusProposta.ELEGIVEL);
        propostas.forEach(p -> {
            System.out.println(p.getId());
            try {
                var response = cartaoIntegration.findCartao(p.getId().toString());
                propostaRepository.save(new Proposta(p, response.getId()));
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Não foi possível associar o cartão a proposta: " + e);
            }
        });
    }
}
