package com.bancobase.payment_microservice.service.impl;

import com.bancobase.payment_microservice.dto.Resultado;
import com.bancobase.payment_microservice.dto.*;
import com.bancobase.payment_microservice.entity.Cliente;
import com.bancobase.payment_microservice.entity.EstatusPago;
import com.bancobase.payment_microservice.entity.Pago;
import com.bancobase.payment_microservice.model.EstatusPagoEnum;
import com.bancobase.payment_microservice.repository.ClienteRepository;
import com.bancobase.payment_microservice.repository.EstatusPagoRepository;
import com.bancobase.payment_microservice.repository.PagosRepository;
import com.bancobase.payment_microservice.service.PagoService;
import com.bancobase.payment_microservice.util.EntityToDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PagoServiceImpl implements PagoService {

    @Value("${pagos.queue.name}")
    private String cola;

    private final PagosRepository pagosRepository;
    private final ClienteRepository clienteRepository;
    private final EstatusPagoRepository estatusPagoRepository;
    private final RabbitTemplate rabbitTemplate;

    public PagoServiceImpl(PagosRepository pagosRepository, ClienteRepository clienteRepository,
                           EstatusPagoRepository estatusPagoRepository, RabbitTemplate rabbitTemplate) {
        this.pagosRepository = pagosRepository;
        this.clienteRepository = clienteRepository;
        this.estatusPagoRepository = estatusPagoRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Resultado<PagoResponseDto> procesaPago(PagoRequestDto pagoDto) {

        Optional<Cliente> clienteRealiza = this.clienteRepository.findById(pagoDto.getClienteIdRealizaPago());
        Optional<Cliente> clienteRecibe = this.clienteRepository.findById(pagoDto.getClienteIdRecibePago());
        Optional<EstatusPago> estatusPago = this.estatusPagoRepository.findByDescripcion(EstatusPagoEnum.EN_PROCESO);

        if (clienteRealiza.isEmpty())
            return Resultado.failedResult("No existe el cliente que realiza el pago con id ".concat(pagoDto.getClienteIdRealizaPago().toString()));

        if (clienteRecibe.isEmpty())
            return Resultado.failedResult("No existe el cliente que recibe el pago con id ".concat(pagoDto.getClienteIdRecibePago().toString()));

        Pago pago = Pago.builder()
                .monto(new BigDecimal(pagoDto.getMonto()))
                .concepto(pagoDto.getConcepto())
                .clienteRealizaPago(clienteRealiza.get())
                .clienteRecibePago(clienteRecibe.get())
                .estatusPago(estatusPago.get())
                .fechaPago(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        return Resultado.successResult(pagoToPagoResponse(this.pagosRepository.save(pago)), "Pago procesado exitosamente.");
    }

    @Override
    public Resultado<PagoResponseDto> obtenerEstatusPago(long estatusPagoId) {

        Optional<Pago> pago = this.pagosRepository.findById(estatusPagoId);
        return pago.map(value -> Resultado.successResult(pagoToPagoResponse(value)))
                .orElseGet(() -> Resultado.failedResult("No existe un pago con el id: " + estatusPagoId));

    }

    @Override
    public Resultado<String> actualizaEstatusPago(ActualizaEstatusRequest actualizaEstatusRequest) {

        Optional<EstatusPago> opEstatusPago = this.estatusPagoRepository.findById(actualizaEstatusRequest.getIdEstatusPago());
        if(opEstatusPago.isEmpty())
            return Resultado.failedResult("No existe el estatus con id ".concat(actualizaEstatusRequest.getIdEstatusPago().toString()));

        Optional<Pago> OpPago = this.pagosRepository.findById(actualizaEstatusRequest.getIdPago());
        if(OpPago.isEmpty())
            return Resultado.failedResult("No existe el pago con id ".concat(actualizaEstatusRequest.getIdPago().toString()));

        Pago pago = OpPago.get();
        pago.setEstatusPago(opEstatusPago.get());
        this.pagosRepository.save(pago);

        String resultado = "Se ha actualizado exitosamente el pago con id " + actualizaEstatusRequest.getIdPago() + " al estatus " + opEstatusPago.get().getDescripcion().toString();
        this.rabbitTemplate.convertAndSend(cola, resultado);
        return Resultado.successResult(resultado);
    }

   /* @RabbitListener(queues = "cola_msj_pagos")
    public void receiveMessage(String message) {
        log.info("Mensaje recibido {}", message);
    }*/

    private PagoResponseDto pagoToPagoResponse(Pago pago) {
        return PagoResponseDto.builder()
                .id(pago.getId())
                .concepto(pago.getConcepto())
                .monto(pago.getMonto())
                .estatusPago(EntityToDTO.estatusPagoToEstatusPagoDto(pago.getEstatusPago()))
                .quienRealizaPago(EntityToDTO.clienteToClienteDTO(pago.getClienteRealizaPago()))
                .quienRecibePago(EntityToDTO.clienteToClienteDTO(pago.getClienteRecibePago()))
                .fechaPago(pago.getFechaPago().toString())
                .build();
    }

}
