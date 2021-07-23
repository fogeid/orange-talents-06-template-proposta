package br.com.zupacademy.diego.proposta.controllers;

import br.com.zupacademy.diego.proposta.dto.request.AvisoCartaoRequest;
import br.com.zupacademy.diego.proposta.dto.response.CartaoResponse;
import br.com.zupacademy.diego.proposta.integrations.CartaoIntegration;
import br.com.zupacademy.diego.proposta.models.AvisoCartao;
import br.com.zupacademy.diego.proposta.repositories.AvisoCartaoRepository;
import feign.FeignException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class AvisoCartaoController {
    @Autowired
    private AvisoCartaoRepository avisoCartaoRepository;

    @Autowired
    private CartaoIntegration integration;

    @PostMapping("/cartoes/{idCartao}/avisos")
    public ResponseEntity<?> insertAvisoViagem(@PathVariable String idCartao, @RequestBody @Valid AvisoCartaoRequest request, @RequestHeader(value = "User-Agent") String userAgent, HttpServletRequest servletRequest, UriComponentsBuilder uriBuilder) {
        try {
            CartaoResponse response = integration.findCartaoById(idCartao);
            integration.avisoViagem(idCartao, request);
        } catch (FeignException e) {
            if (e.status() == 404) {
               return ResponseEntity.notFound().build();
            } if (e.status() == 422) {
                return ResponseEntity.unprocessableEntity().build();
            }
        }

        AvisoCartao avisoCartao = request.converter(idCartao, userAgent, servletRequest);
        avisoCartaoRepository.save(avisoCartao);
        URI uri = uriBuilder.path("/cartao/{idCartao}/avisos").buildAndExpand(avisoCartao.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

}
