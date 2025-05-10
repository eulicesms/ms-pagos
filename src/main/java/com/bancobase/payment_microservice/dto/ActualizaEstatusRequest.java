package com.bancobase.payment_microservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Valid
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActualizaEstatusRequest {

    @NotNull(message = "El id de pago no puede ser nulo")
    private Long idPago;

    @NotNull(message = "El id del estatus del pago no puede ser nulo")
    private Integer idEstatusPago;

}
