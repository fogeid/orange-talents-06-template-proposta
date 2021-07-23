package br.com.zupacademy.diego.proposta.dto.request;

import br.com.zupacademy.diego.proposta.models.AvisoCartao;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoCartaoRequest {
    @NotNull
    @NotEmpty
    private String destino;

    @NotNull
    @Future
    private LocalDate validoAte;

    public AvisoCartaoRequest() {
    }

    public AvisoCartaoRequest(@NotNull @NotEmpty String destino, @NotNull @NotEmpty @Future LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public AvisoCartao converter(String idCartao, String userAgent, HttpServletRequest servletRequest) {
        return new AvisoCartao(idCartao, this.destino, this.validoAte, servletRequest.getRemoteAddr(), userAgent);
    }
}
