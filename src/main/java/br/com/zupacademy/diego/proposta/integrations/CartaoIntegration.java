package br.com.zupacademy.diego.proposta.integrations;

import br.com.zupacademy.diego.proposta.dto.response.CartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartao", url = "${cartao.url}", path = "${cartao.path}")
public interface CartaoIntegration {
    @GetMapping
    CartaoResponse findCartao(@RequestParam(name = "idProposta") String id);

    @GetMapping("/{idCartao}")
    CartaoResponse findCartaoById(@PathVariable String idCartao);

    @PostMapping("/{idCartao}/bloqueios")
    void bloquearCartaoNoSistemaLegado(@PathVariable String idCartao, Object sistemaResponsavel);

    @PostMapping("/{idCartao}/avisos")
    void avisoViagem(@PathVariable String idCartao, Object request);

    @PostMapping("/{idCartao}/carteiras")
    void inserirCarteira(@PathVariable String idCartao, Object request);
}
