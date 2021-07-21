package br.com.zupacademy.diego.proposta.dto.request;

import br.com.zupacademy.diego.proposta.models.Biometria;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class BiometriaRequest {
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$", message = "Ocorreu um erro ao cadastrar a biometria!")
    private String fingerprint;

    public BiometriaRequest() {
    }

    public BiometriaRequest(@NotNull @NotEmpty String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public Biometria converter(String id) {
        return new Biometria(this.fingerprint, id);
    }
}
