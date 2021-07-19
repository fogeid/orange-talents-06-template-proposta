package br.com.zupacademy.diego.proposta.models;

import java.util.HashMap;
import java.util.Map;

public enum StatusProposta {
    NAO_ELEGIVEL, ELEGIVEL;

    public static StatusProposta converter(String status){
        Map<String, StatusProposta> mapper = new HashMap<>();
        mapper.put("COM_RESTRICAO", StatusProposta.NAO_ELEGIVEL);
        mapper.put("SEM_RESTRICAO", StatusProposta.ELEGIVEL);

        return mapper.get(status);
    }
}
