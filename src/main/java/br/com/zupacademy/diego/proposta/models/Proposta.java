package br.com.zupacademy.diego.proposta.models;

import br.com.zupacademy.diego.proposta.validators.Documento;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_proposta")
public class Proposta {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String documento;

    @NotNull
    @NotEmpty
    @Email
    @Column(nullable = false)
    private String email;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String nome;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String endereco;

    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal salario;

    @Enumerated(value = EnumType.STRING)
    private StatusProposta status = StatusProposta.NAO_ELEGIVEL;

    private String idCartao;

    public Proposta() {
    }

    public Proposta(@NotNull @NotEmpty String documento,@NotNull @NotEmpty @Email String email,
                    @NotNull @NotEmpty String nome, @NotNull @NotEmpty String endereco,
                    @NotNull @Positive BigDecimal salario) {
        this.documento = documento;
        this.documento = encrypt(documento);
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta(Proposta proposta, String idCartao) {
        this.id = proposta.getId();
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.status = proposta.getStatus();
        this.idCartao = idCartao;
    }

    public String getId() {
        return id;
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

    public StatusProposta getStatus() {
        return status;
    }

    public void setStatus(StatusProposta status) {
        this.status = status;
    }

    public String getIdCartao() {
        return idCartao;
    }

    private String encrypt(String documento) {
        return new BCryptPasswordEncoder().encode(documento);
    }
}
