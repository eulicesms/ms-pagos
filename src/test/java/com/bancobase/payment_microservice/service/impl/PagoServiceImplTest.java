package com.bancobase.payment_microservice.service.impl;

import com.bancobase.payment_microservice.DataProvider;
import com.bancobase.payment_microservice.dto.ActualizaEstatusRequest;
import com.bancobase.payment_microservice.dto.PagoRequestDto;
import com.bancobase.payment_microservice.dto.PagoResponseDto;
import com.bancobase.payment_microservice.dto.Resultado;
import com.bancobase.payment_microservice.entity.Pago;
import com.bancobase.payment_microservice.repository.ClienteRepository;
import com.bancobase.payment_microservice.repository.EstatusPagoRepository;
import com.bancobase.payment_microservice.repository.PagosRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PagoServiceImplTest {

    @Mock
    private PagosRepository pagosRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EstatusPagoRepository estatusPagoRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private PagoServiceImpl pagoService;

    @Test
    public void obtenerEstatusPagoTest(){
        when(pagosRepository.findById(anyLong())).thenReturn(DataProvider.findByIdPago());
        Resultado<PagoResponseDto> resultado = pagoService.obtenerEstatusPago(1L);
        assertNotNull(resultado);
        verify(this.pagosRepository).findById(anyLong());
    }

    @Test
    public void obtenerEstatusPagoErrorTest(){
        when(pagosRepository.findById(anyLong())).thenReturn(DataProvider.findByIdPagoError());
        Resultado<PagoResponseDto> resultado = pagoService.obtenerEstatusPago(1L);
        assertNotNull(resultado);
        verify(this.pagosRepository).findById(anyLong());
    }

    @Test
    public void actualizaEstatusPagoTest(){
        Long id = 1L;
        when(pagosRepository.findById(anyLong())).thenReturn(DataProvider.findByIdPago());
        when(estatusPagoRepository.findById(anyInt())).thenReturn(DataProvider.findByEstatusPago());

        Resultado<String> resultado = pagoService.actualizaEstatusPago(new ActualizaEstatusRequest(id, 2));
        assertNotNull(resultado);
        verify(this.pagosRepository).findById(anyLong());
        verify(this.estatusPagoRepository).findById(anyInt());
    }

    @Test
    public void actualizaEstatusPagoPagoErrorTest(){
        Long id = 1L;

        when(estatusPagoRepository.findById(anyInt())).thenReturn(DataProvider.findByEstatusPago());
        when(pagosRepository.findById(anyLong())).thenReturn(DataProvider.findByIdPagoError());

        Resultado<String> resultado = pagoService.actualizaEstatusPago(new ActualizaEstatusRequest(id, 2));

        assertNotNull(resultado);
        verify(this.pagosRepository).findById(anyLong());
    }

    @Test
    public void actualizaEstatusPagoEstatusErrorTest(){
        Long id = 1L;
        when(estatusPagoRepository.findById(anyInt())).thenReturn(DataProvider.findByEstatusPagoError());
        Resultado<String> resultado = pagoService.actualizaEstatusPago(new ActualizaEstatusRequest(id, 2));
        assertNotNull(resultado);
        verify(this.estatusPagoRepository).findById(anyInt());
    }

    @Test
    public void procesaPagoTest(){

        when(clienteRepository.findById(anyLong())).thenReturn(DataProvider.findByIdCliente());
        when(estatusPagoRepository.findByDescripcion(any())).thenReturn(DataProvider.findByEstatusPago());
        when(pagosRepository.save(any(Pago.class))).thenReturn(DataProvider.savePago());

        Resultado<PagoResponseDto> resultado = pagoService.
                procesaPago(new PagoRequestDto("pago", 1L, "345", 2L));

        assertNotNull(resultado);
        verify(this.clienteRepository, times(2)).findById(anyLong());
        verify(this.estatusPagoRepository).findByDescripcion(any());
        verify(this.pagosRepository).save(any(Pago.class));
    }

    @Test
    public void procesaPagoClienteRealizaErrorTest(){

        when(clienteRepository.findById(anyLong())).thenReturn(DataProvider.findByIdClienteError());
        Resultado<PagoResponseDto> resultado = pagoService.
                procesaPago(new PagoRequestDto("pago", 1L, "345", 2L));

        assertNotNull(resultado);
        verify(this.clienteRepository, times(2)).findById(anyLong());
    }

    @Test
    public void procesaPagoClienteRecibeErrorTest(){

        when(clienteRepository.findById(anyLong())).thenReturn(DataProvider.findByIdCliente(), DataProvider.findByIdClienteError());
        Resultado<PagoResponseDto> resultado = pagoService.
                procesaPago(new PagoRequestDto("pago", 1L, "345", 2L));
        assertNotNull(resultado);
        verify(this.clienteRepository, times(2)).findById(anyLong());
    }
}
