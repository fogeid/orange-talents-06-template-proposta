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

import javax.validation.Valid;
import java.net.URI;

@RestController
public class BiometriaController {
    @Autowired
    private BiometriaRepository biometriaRepository;

    @Autowired
    private CartaoIntegration integration;

    @PostMapping("/cartoes/{id}/biometria")
    public ResponseEntity<?> associarBiometria(@PathVariable String id, @RequestBody @Valid BiometriaRequest request, UriComponentsBuilder uriBuilder) {
        try {
            CartaoResponse response = integration.findCartaoById(id);
        } catch (FeignException e) {
            if (e.status() == 404) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.badRequest().build();
        }

        Biometria biometria = request.converter(id);
        biometriaRepository.save(biometria);

        URI uri = uriBuilder.path("/{id}").buildAndExpand(biometria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
