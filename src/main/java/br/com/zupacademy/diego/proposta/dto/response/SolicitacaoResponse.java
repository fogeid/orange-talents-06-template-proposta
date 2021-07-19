package br.com.zupacademy.diego.proposta.dto.response;

import br.com.zupacademy.diego.proposta.models.Proposta;
import br.com.zupacademy.diego.proposta.models.StatusProposta;

public class SolicitacaoResponse {
    private StatusProposta status;

    public SolicitacaoResponse(Proposta proposta) {
        this.status = proposta.getStatus();
    }

    public StatusProposta getStatus() {
        return status;
    }
}
