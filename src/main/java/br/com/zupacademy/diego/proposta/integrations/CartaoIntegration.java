package br.com.zupacademy.diego.proposta.integrations;

import br.com.zupacademy.diego.proposta.dto.response.CartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartao", url = "http://localhost:8888", path = "/api/cartoes")
public interface CartaoIntegration {
    @GetMapping
    CartaoResponse findCartao(@RequestParam(name = "idProposta") String id);
}
