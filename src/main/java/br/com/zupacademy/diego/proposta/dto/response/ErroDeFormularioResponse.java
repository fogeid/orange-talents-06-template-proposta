package br.com.zupacademy.diego.proposta.dto.response;

public class ErroDeFormularioResponse {
    private String campo;
    private String erro;

    public ErroDeFormularioResponse(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
