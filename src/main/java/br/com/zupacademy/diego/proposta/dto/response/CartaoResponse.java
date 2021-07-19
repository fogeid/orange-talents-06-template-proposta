package br.com.zupacademy.diego.proposta.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CartaoResponse {
    private String id;

    public CartaoResponse() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CartaoResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
