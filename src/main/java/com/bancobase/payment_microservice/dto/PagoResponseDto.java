package com.bancobase.payment_microservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagoResponseDto {

    private Long id;
    private String concepto;
    private EstatusPagoDto estatusPago;
    private ClienteDto quienRealizaPago;
    private BigDecimal monto;
    private ClienteDto quienRecibePago;
    private String fechaPago;
}
