package com.bancobase.payment_microservice.dto;

public enum ResultEstatus {

    FAILED("FAILED"), SUCCEEDED("SUCCEEDED"), UNDEFINED("UNDEFINED");

    private String tipo;

    ResultEstatus(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public static ResultEstatus fromString(String tipo) {
        for (ResultEstatus en : values()) {
            if (en.tipo.equals(tipo)) {
                return en;
            }
        }
        return ResultEstatus.UNDEFINED;
    }
}
