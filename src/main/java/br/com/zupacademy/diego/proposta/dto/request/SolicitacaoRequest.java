package br.com.zupacademy.diego.proposta.dto.request;

import br.com.zupacademy.diego.proposta.models.Proposta;

public class SolicitacaoRequest {
    private String documento;
    private String nome;
    private String idProposta;

    public SolicitacaoRequest(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
