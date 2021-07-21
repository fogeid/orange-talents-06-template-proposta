package br.com.zupacademy.diego.proposta.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_biometria")
public class Biometria {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String fingerprint;
    private String idCartao;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    public Biometria() {
    }

    public Biometria(String fingerprint, String idCartao) {
        this.fingerprint = fingerprint;
        this.idCartao = idCartao;
    }

    public String getId() {
        return id;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
