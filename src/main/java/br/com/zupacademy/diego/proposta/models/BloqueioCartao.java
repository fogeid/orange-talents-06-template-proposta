package br.com.zupacademy.diego.proposta.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "tb_bloqueio_cartao")
public class BloqueioCartao {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String idCartao;
    private Instant dataBloqueio = Instant.now();
    private String ipCliente;
    private String userAgent;

    public BloqueioCartao() {
    }

    public BloqueioCartao(String idCartao, String ipCliente, String userAgent) {
        this.idCartao = idCartao;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
    }

    public String getId() {
        return id;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public Instant getDataBloqueio() {
        return dataBloqueio;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
