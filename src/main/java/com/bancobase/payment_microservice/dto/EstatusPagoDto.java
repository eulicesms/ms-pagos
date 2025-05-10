package com.bancobase.payment_microservice.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstatusPagoDto {
    private Integer id;
    private String descripcion;
}
