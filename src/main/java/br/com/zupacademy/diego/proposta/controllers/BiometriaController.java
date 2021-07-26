package br.com.zupacademy.diego.proposta.controllers;

import br.com.zupacademy.diego.proposta.dto.request.BiometriaRequest;
import br.com.zupacademy.diego.proposta.dto.response.CartaoResponse;
import br.com.zupacademy.diego.proposta.integrations.CartaoIntegration;
import br.com.zupacademy.diego.proposta.models.Biometria;
import br.com.zupacademy.diego.proposta.repositories.BiometriaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class BiometriaController {
    @Autowired
    private BiometriaRepository biometriaRepository;

    @Autowired
    private CartaoIntegration integration;

    @PostMapping("/cartoes/{idCartao}/biometrias")
    @Transactional
    public ResponseEntity<?> associarBiometria(@PathVariable String idCartao, @RequestBody @Valid BiometriaRequest request, UriComponentsBuilder uriBuilder) {
        try {
            CartaoResponse response = integration.findCartaoById(idCartao);
        } catch (FeignException e) {
            if (e.status() == 404) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().build();
        }

        Biometria biometria = request.converter(idCartao);
        biometriaRepository.save(biometria);

        URI uri = uriBuilder.path("/{idCartao}").buildAndExpand(biometria.getIdCartao()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
