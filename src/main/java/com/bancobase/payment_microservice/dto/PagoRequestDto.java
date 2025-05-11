package com.bancobase.payment_microservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Valid
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagoRequestDto {

    @NotNull(message = "El concepto no puede ser nulo")
    @Size(max = 30)
    private String concepto;

    @NotNull(message = "El cliente que realiza el pago no puede ser nulo")
    private Long clienteIdRealizaPago;

    @NotNull(message = "El monto no puede ser nulo")
    @NotBlank(message = "El monto no puede estar vacio")
    private String monto;

    @NotNull(message = "El cliente que recibe el pago no puede ser nulo")
    private Long clienteIdRecibePago;

}
