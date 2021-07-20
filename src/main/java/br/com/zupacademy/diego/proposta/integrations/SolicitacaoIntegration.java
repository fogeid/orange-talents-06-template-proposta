package br.com.zupacademy.diego.proposta.integrations;

import br.com.zupacademy.diego.proposta.dto.request.SolicitacaoRequest;
import br.com.zupacademy.diego.proposta.dto.response.SolicitacaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "solicitacao", url = "${solicitacao.url}", path = "${solicitacao.path}")
public interface SolicitacaoIntegration {
    @PostMapping
    SolicitacaoResponse enviarSolicitacao(@RequestBody SolicitacaoRequest request);
}
