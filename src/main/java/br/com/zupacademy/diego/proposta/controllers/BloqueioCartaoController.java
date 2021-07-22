package br.com.zupacademy.diego.proposta.controllers;

import br.com.zupacademy.diego.proposta.dto.response.CartaoResponse;
import br.com.zupacademy.diego.proposta.integrations.CartaoIntegration;
import br.com.zupacademy.diego.proposta.models.BloqueioCartao;
import br.com.zupacademy.diego.proposta.repositories.BloqueioCartaoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Map;

@RestController
public class BloqueioCartaoController {
    @Autowired
    private BloqueioCartaoRepository bloqueioCartaoRepository;

    @Autowired
    private CartaoIntegration integration;

    @Value("${sistema.nome}")
    private String sistemaResponsavel;

    @PostMapping("/cartoes/{idCartao}/bloqueios")
    public ResponseEntity<?> bloquearCartao(@PathVariable String idCartao, @RequestHeader(value = "User-Agent") String userAgent, HttpServletRequest servletRequest, UriComponentsBuilder uriBuilder) {
        try {
            CartaoResponse response = integration.findCartaoById(idCartao);
            integration.bloquearCartaoNoSistemaLegado(idCartao, Map.of("sistemaResponsavel", this.sistemaResponsavel));
        } catch (FeignException e) {
            if (e.status() == 404) {
                return ResponseEntity.notFound().build();
            } if (e.status() == 422) {
                return ResponseEntity.unprocessableEntity().build();
            }
            return ResponseEntity.badRequest().build();
        }

        BloqueioCartao bloqueioCartao = new BloqueioCartao(idCartao, servletRequest.getRemoteAddr(), userAgent);
        bloqueioCartaoRepository.save(bloqueioCartao);

        URI uri = uriBuilder.path("/{idCartao").buildAndExpand(bloqueioCartao.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
