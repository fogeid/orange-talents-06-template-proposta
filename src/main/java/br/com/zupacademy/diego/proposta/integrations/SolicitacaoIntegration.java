package br.com.zupacademy.diego.proposta.integrations;

import br.com.zupacademy.diego.proposta.dto.request.SolicitacaoRequest;
import br.com.zupacademy.diego.proposta.dto.response.SolicitacaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "solicitacao", url = "http://localhost:9999", path = "/api/solicitacao")
public interface SolicitacaoIntegration {
    @PostMapping
    SolicitacaoResponse enviarSolicitacao(@RequestBody SolicitacaoRequest request);
}
