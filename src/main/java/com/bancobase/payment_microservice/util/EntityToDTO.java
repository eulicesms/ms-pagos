package com.bancobase.payment_microservice.util;

import com.bancobase.payment_microservice.dto.ClienteDto;
import com.bancobase.payment_microservice.dto.EstatusPagoDto;
import com.bancobase.payment_microservice.entity.Cliente;
import com.bancobase.payment_microservice.entity.EstatusPago;

public class EntityToDTO {

    public static ClienteDto clienteToClienteDTO(Cliente cliente) {

        return ClienteDto.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .apellidoMaterno(cliente.getApellidoMaterno())
                .apellidoPaterno(cliente.getApellidoPaterno())
                .build();
    }

    public static EstatusPagoDto estatusPagoToEstatusPagoDto(EstatusPago estatusPago) {
        return EstatusPagoDto.builder()
                .id(estatusPago.getId())
                .descripcion(estatusPago.getDescripcion().toString())
                .build();
    }
}
