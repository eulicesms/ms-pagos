package com.bancobase.payment_microservice.service;

import com.bancobase.payment_microservice.dto.Resultado;
import com.bancobase.payment_microservice.dto.ActualizaEstatusRequest;
import com.bancobase.payment_microservice.dto.PagoRequestDto;
import com.bancobase.payment_microservice.dto.PagoResponseDto;

public interface PagoService {

    Resultado<PagoResponseDto> procesaPago(PagoRequestDto pagoDto);

    Resultado<PagoResponseDto> obtenerEstatusPago(long estatusPagoId);

    Resultado<String> actualizaEstatusPago(ActualizaEstatusRequest actualizaEstatusRequest);
}
