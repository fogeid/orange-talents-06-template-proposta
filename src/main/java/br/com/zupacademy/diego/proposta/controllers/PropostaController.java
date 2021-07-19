package br.com.zupacademy.diego.proposta.controllers;

import br.com.zupacademy.diego.proposta.dto.request.PropostaRequest;
import br.com.zupacademy.diego.proposta.dto.request.SolicitacaoRequest;
import br.com.zupacademy.diego.proposta.dto.response.SolicitacaoResponse;
import br.com.zupacademy.diego.proposta.integrations.SolicitacaoIntegration;
import br.com.zupacademy.diego.proposta.models.Proposta;
import br.com.zupacademy.diego.proposta.models.StatusProposta;
import br.com.zupacademy.diego.proposta.repositories.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {
    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private SolicitacaoIntegration integration;

    @PostMapping
    @Transactional
    public ResponseEntity<?> inserir(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriBuilder) {
        Proposta proposta = request.converter();
        propostaRepository.save(proposta);

        try {
            SolicitacaoRequest solicitacaoRequest = new SolicitacaoRequest(proposta);
            SolicitacaoResponse response = integration.enviarSolicitacao(solicitacaoRequest);
            proposta.setStatus(StatusProposta.converter(response.getResultadoSolicitacao()));
        } catch (FeignException e) {
            URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }

        URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
