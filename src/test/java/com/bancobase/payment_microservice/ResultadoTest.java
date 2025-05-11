package com.bancobase.payment_microservice;

import com.bancobase.payment_microservice.dto.Resultado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResultadoTest {

    @Test
    public void test() {
        Resultado<String> resultado = Resultado.failedResult("Error", "404");
        assertNotNull(resultado);
    }
}
