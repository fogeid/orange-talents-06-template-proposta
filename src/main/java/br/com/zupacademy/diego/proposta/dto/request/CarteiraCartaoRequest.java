package br.com.zupacademy.diego.proposta.dto.request;

import br.com.zupacademy.diego.proposta.models.CarteiraCartao;
import br.com.zupacademy.diego.proposta.models.TipoCarteira;
import br.com.zupacademy.diego.proposta.validators.ValorUnico;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CarteiraCartaoRequest {
    @NotNull
    @NotEmpty
    @Email
    private String email;

    @ValorUnico(obj = CarteiraCartao.class, fieldName = "carteira")
    private TipoCarteira carteira;

    public CarteiraCartaoRequest() {
    }

    public CarteiraCartaoRequest(@NotNull @NotEmpty @Email String email, TipoCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }

    public CarteiraCartao converter() {
        return new CarteiraCartao(this.email, this.carteira);
    }
}
