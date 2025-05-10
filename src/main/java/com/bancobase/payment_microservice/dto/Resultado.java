package com.bancobase.payment_microservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Setter
@Getter
public class Resultado<T> {

    private ResultEstatus estatus;
    private Optional<T> data;
    private String mensaje;
    private String code;

    public static <T> Resultado<T> failedResult(String errorMessage) {
        Resultado<T> resultado = new Resultado<T>();
        resultado.setEstatus(ResultEstatus.FAILED);
        resultado.setMensaje(errorMessage);
        return resultado;
    }

    public static <T> Resultado<T> failedResult(String errorMessage, String code) {
        Resultado<T> resultado = new Resultado<T>();
        resultado.setEstatus(ResultEstatus.FAILED);
        resultado.setMensaje(errorMessage);
        resultado.setCode(code);
        return resultado;
    }

    public static <T> Resultado<T> successResult(T t) {
        Resultado<T> resultado = new Resultado<T>();
        resultado.setEstatus(ResultEstatus.SUCCEEDED);
        resultado.setCode(HttpStatus.OK.toString());
        resultado.setData(Optional.of(t));
        return resultado;
    }

    public static <T> Resultado<T> successResult(T t, String mensaje) {
        Resultado<T> resultado = new Resultado<T>();
        resultado.setEstatus(ResultEstatus.SUCCEEDED);
        resultado.setCode(HttpStatus.OK.toString());
        resultado.setMensaje(mensaje);
        resultado.setData(Optional.of(t));
        return resultado;
    }

}
