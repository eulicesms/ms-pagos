package com.bancobase.payment_microservice.dto;

public enum ResultEstatus {

    FAILED("FAILED"), SUCCEEDED("SUCCEEDED");

    private String tipo;

    ResultEstatus(String tipo) {
        this.tipo = tipo;
    }
}
