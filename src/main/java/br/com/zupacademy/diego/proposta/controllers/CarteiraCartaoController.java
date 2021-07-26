package br.com.zupacademy.diego.proposta.controllers;

import br.com.zupacademy.diego.proposta.dto.request.CarteiraCartaoRequest;
import br.com.zupacademy.diego.proposta.dto.response.CartaoResponse;
import br.com.zupacademy.diego.proposta.integrations.CartaoIntegration;
import br.com.zupacademy.diego.proposta.models.CarteiraCartao;
import br.com.zupacademy.diego.proposta.repositories.CarteiraCartaoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class CarteiraCartaoController {
    @Autowired
    private CarteiraCartaoRepository carteiraCartaoRepository;

    @Autowired
    private CartaoIntegration integration;

    @PostMapping("/cartoes/{idCartao}/carteiras")
    @Transactional
    public ResponseEntity<?> associarCarteira(@PathVariable String idCartao, @RequestBody @Valid CarteiraCartaoRequest request, UriComponentsBuilder uriBuilder) {
        try {
            CartaoResponse response = integration.findCartaoById(idCartao);
            integration.inserirCarteira(idCartao, request);
            CarteiraCartao carteiraCartao = request.converter();
            carteiraCartaoRepository.save(carteiraCartao);
            URI uri = uriBuilder.path("/cartoes/{idCartao}/carteiras").buildAndExpand(idCartao).toUri();

            return ResponseEntity.created(uri).build();
        } catch (FeignException e) {
            if (e.status() == 404) {
                return ResponseEntity.notFound().build();
            } if (e.status() == 422) {
                return ResponseEntity.unprocessableEntity().build();
            }

            return ResponseEntity.badRequest().build();
        }
    }
}
