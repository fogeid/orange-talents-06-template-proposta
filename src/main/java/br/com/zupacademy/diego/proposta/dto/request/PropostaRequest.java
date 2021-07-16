package br.com.zupacademy.diego.proposta.dto.request;

import br.com.zupacademy.diego.proposta.models.Proposta;
import br.com.zupacademy.diego.proposta.validators.Documento;
import br.com.zupacademy.diego.proposta.validators.ValorUnico;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaRequest {
    @NotNull
    @NotEmpty
    @Documento
    @ValorUnico(obj = Proposta.class, fieldName = "documento", message = "CPF/CNPJ já cadastrado, insira um outro CPF/CNPJ.")
    private String documento;

    @NotNull
    @NotEmpty
    @Email
    @ValorUnico(obj = Proposta.class, fieldName = "email", message = "E-mail já cadastrado, insira um outro e-mail.")
    private String email;

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    private String endereco;

    @NotNull
    @Positive
    private BigDecimal salario;

    public PropostaRequest(@NotNull @NotEmpty String documento, @NotNull @NotEmpty @Email String email,
                           @NotNull @NotEmpty String nome, @NotNull @NotEmpty String endereco,
                           @NotNull @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Proposta converter() {
        return new Proposta(this.documento, this.email, this.nome, this.endereco, this.salario);
    }
}
