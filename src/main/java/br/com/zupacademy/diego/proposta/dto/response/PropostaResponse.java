package br.com.zupacademy.diego.proposta.dto.response;

import br.com.zupacademy.diego.proposta.models.Proposta;
import br.com.zupacademy.diego.proposta.models.StatusProposta;

public class PropostaResponse {
    private String nome;
    private String idCartao;
    private StatusProposta statusProposta;

    public PropostaResponse() {
    }

    public PropostaResponse(Proposta proposta) {
        this.nome = proposta.getNome();
        this.idCartao = proposta.getIdCartao();
        this.statusProposta = proposta.getStatus();
    }

    public String getNome() {
        return nome;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }
}
