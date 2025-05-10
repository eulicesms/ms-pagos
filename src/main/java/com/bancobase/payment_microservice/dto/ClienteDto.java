package com.bancobase.payment_microservice.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDto {

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
}
