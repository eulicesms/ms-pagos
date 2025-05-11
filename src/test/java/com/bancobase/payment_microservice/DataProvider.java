package com.bancobase.payment_microservice;

import com.bancobase.payment_microservice.dto.ClienteDto;
import com.bancobase.payment_microservice.dto.EstatusPagoDto;
import com.bancobase.payment_microservice.dto.PagoResponseDto;
import com.bancobase.payment_microservice.dto.Resultado;
import com.bancobase.payment_microservice.entity.Cliente;
import com.bancobase.payment_microservice.entity.EstatusPago;
import com.bancobase.payment_microservice.entity.Pago;
import com.bancobase.payment_microservice.model.EstatusPagoEnum;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

public class DataProvider  {

    public static Optional<EstatusPago> findByEstatusPago() {
        return Optional.of(EstatusPago.builder().id(1).descripcion(EstatusPagoEnum.EN_PROCESO).build());
    }

    public static Optional<EstatusPago> findByEstatusPagoError() {
        return Optional.empty();
    }

    public static Optional<Cliente> findByIdCliente() {
        return Optional.of(Cliente.builder().id(1L).nombre("Luis").apellidoPaterno("Lopez").apellidoMaterno("Cruz").build());
    }

    public static Optional<Cliente> findByIdClienteError() {
        return Optional.empty();
    }

    public static Pago savePago() {
        return Pago.builder()
                .id(1L)
                .monto(new BigDecimal(300))
                .estatusPago(EstatusPago.builder().id(1).descripcion(EstatusPagoEnum.EN_PROCESO).build())
                .fechaPago(new Timestamp(System.currentTimeMillis()))
                .concepto("Pago TC")
                .clienteRealizaPago(Cliente.builder().id(1L).nombre("Luis").apellidoPaterno("Lopez").apellidoMaterno("Cruz").build())
                .clienteRecibePago(Cliente.builder().id(2L).nombre("Juan").apellidoPaterno("Cuevas").apellidoMaterno("Farias").build())
                .build();
    }

    public static Optional<Pago> findByIdPago() {
        return Optional.of(Pago.builder()
                        .id(1L)
                        .monto(new BigDecimal(300))
                        .estatusPago(EstatusPago.builder().id(1).descripcion(EstatusPagoEnum.EN_PROCESO).build())
                        .fechaPago(new Timestamp(System.currentTimeMillis()))
                        .concepto("Pago TC")
                        .clienteRealizaPago(Cliente.builder().id(1L).nombre("Luis").apellidoPaterno("Lopez").apellidoMaterno("Cruz").build())
                        .clienteRecibePago(Cliente.builder().id(2L).nombre("Juan").apellidoPaterno("Cuevas").apellidoMaterno("Farias").build())
                .build());
    }
    public static Optional<Pago> findByIdPagoError() {
        return Optional.empty();
    }

    public static Resultado<PagoResponseDto> procesaPago() {
        PagoResponseDto pagoResponseDto= PagoResponseDto.builder()
                .id(1L)
                .concepto("Pago")
                .monto(new BigDecimal("200.09"))
                .estatusPago(EstatusPagoDto.builder().id(1).descripcion(EstatusPagoEnum.EN_PROCESO.toString()).build())
                .quienRealizaPago(ClienteDto.builder().id(1L).nombre("Luis").apellidoPaterno("Lopez").apellidoMaterno("Cruz").build())
                .quienRecibePago(ClienteDto.builder().id(2L).nombre("Juan").apellidoPaterno("Lopez").apellidoMaterno("Cruz").build())
                .fechaPago(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .build();

        return Resultado.successResult(pagoResponseDto);
    }

    public static Resultado<PagoResponseDto> getPago() {
        PagoResponseDto pagoResponseDto= PagoResponseDto.builder()
                .id(1L)
                .concepto("Pago")
                .monto(new BigDecimal("200.09"))
                .estatusPago(EstatusPagoDto.builder().id(1).descripcion(EstatusPagoEnum.EN_PROCESO.toString()).build())
                .quienRealizaPago(ClienteDto.builder().id(1L).nombre("Luis").apellidoPaterno("Lopez").apellidoMaterno("Cruz").build())
                .quienRecibePago(ClienteDto.builder().id(2L).nombre("Juan").apellidoPaterno("Lopez").apellidoMaterno("Cruz").build())
                .fechaPago(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .build();

        return Resultado.successResult(pagoResponseDto);
    }

    public static Resultado<PagoResponseDto> getPagoError() {

        return Resultado.failedResult("Error");
    }

    public static Resultado<String> actualizaEstatusPago() {

        return Resultado.successResult("Se ha actualizado exitosamente el pago con id 1L al estatus EN_PROCESO");
    }

    public static Resultado<String> actualizaEstatusPagoError() {

        return Resultado.failedResult("Error");
    }

    public static Resultado<PagoResponseDto> procesaPagoError() {
        return Resultado.failedResult( "error");
    }
}
