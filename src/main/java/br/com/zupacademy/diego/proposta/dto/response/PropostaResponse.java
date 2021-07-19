package br.com.zupacademy.diego.proposta.dto.response;

import br.com.zupacademy.diego.proposta.models.Proposta;
import br.com.zupacademy.diego.proposta.models.StatusProposta;

public class PropostaResponse {
    private String nome;
    private String numeroCartao;
    private StatusProposta statusProposta;

    public PropostaResponse() {
    }

    public PropostaResponse(Proposta proposta) {
        this.nome = proposta.getNome();
        this.numeroCartao = proposta.getNumeroCartao();
        this.statusProposta = proposta.getStatus();
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }
}
