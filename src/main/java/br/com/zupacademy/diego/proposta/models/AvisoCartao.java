package br.com.zupacademy.diego.proposta.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "tb_aviso_cartao")
public class AvisoCartao {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String idCartao;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String destino;

    @NotNull
    @Future
    @Column(nullable = false)
    private LocalDate validoAte;
    private Instant dataCriacao = Instant.now();
    private String ipCliente;
    private String userAgent;

    public AvisoCartao() {
    }

    public AvisoCartao(String idCartao, @NotNull @NotEmpty String destino, @NotNull @Future LocalDate validoAte, String ipCliente, String userAgent) {
        this.idCartao = idCartao;
        this.destino = destino;
        this.validoAte = validoAte;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
    }

    public String getId() {
        return id;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
