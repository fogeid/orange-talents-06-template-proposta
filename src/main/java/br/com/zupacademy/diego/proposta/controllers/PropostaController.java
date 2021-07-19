package br.com.zupacademy.diego.proposta.controllers;

import br.com.zupacademy.diego.proposta.dto.request.PropostaRequest;
import br.com.zupacademy.diego.proposta.dto.request.SolicitacaoRequest;
import br.com.zupacademy.diego.proposta.dto.response.PropostaResponse;
import br.com.zupacademy.diego.proposta.dto.response.SolicitacaoResponse;
import br.com.zupacademy.diego.proposta.integrations.SolicitacaoIntegration;
import br.com.zupacademy.diego.proposta.models.Proposta;
import br.com.zupacademy.diego.proposta.models.StatusProposta;
import br.com.zupacademy.diego.proposta.repositories.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

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

    @GetMapping("/{idProposta}")
    @Transactional
    public ResponseEntity<PropostaResponse> findById(@PathVariable String idProposta) {
        Optional<Proposta> proposta = propostaRepository.findById(idProposta);

        if (proposta.isPresent()) {
            return ResponseEntity.ok(new PropostaResponse(proposta.get()));
        }

        return ResponseEntity.notFound().build();
    }
}
