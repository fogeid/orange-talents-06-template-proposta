package br.com.zupacademy.diego.proposta.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_carteira_cartao")
public class CarteiraCartao {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private TipoCarteira carteira;

    public CarteiraCartao() {
    }

    public CarteiraCartao(@NotNull @NotEmpty @Email String email, TipoCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }
}
