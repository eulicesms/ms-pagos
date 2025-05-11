package com.bancobase.payment_microservice.controller;

import com.bancobase.payment_microservice.DataProvider;
import com.bancobase.payment_microservice.dto.ActualizaEstatusRequest;
import com.bancobase.payment_microservice.dto.PagoRequestDto;
import com.bancobase.payment_microservice.service.impl.PagoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PagosController.class)
public class PagosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PagoServiceImpl pagoService;

    @Test
     public void savePagoTest() throws Exception {
        PagoRequestDto pagoRequestDto = PagoRequestDto.builder()
                .clienteIdRealizaPago(1L)
                .clienteIdRecibePago(2L)
                .concepto("Pago")
                .monto("200.09")
                .build();

        when(pagoService.procesaPago(any(PagoRequestDto.class))).thenReturn(DataProvider.procesaPago());
        mockMvc.perform(put("/api/pago").content(objectMapper.writeValueAsString(pagoRequestDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
     }

    @Test
    public void savePagoErrorTest() throws Exception {
        PagoRequestDto pagoRequestDto = PagoRequestDto.builder()
                .clienteIdRealizaPago(1L)
                .clienteIdRecibePago(2L)
                .concepto("Pago")
                .monto("200.09")
                .build();

        when(pagoService.procesaPago(any(PagoRequestDto.class))).thenReturn(DataProvider.procesaPagoError());
        mockMvc.perform(put("/api/pago").content(objectMapper.writeValueAsString(pagoRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

     @Test
     public void getPagoTest() throws Exception {
        when(pagoService.obtenerEstatusPago(anyLong())).thenReturn(DataProvider.getPago());
        mockMvc.perform(get("/api/pago/1"))
                .andExpect(status().isOk());
     }

    @Test
    public void getPagoErrorTest() throws Exception {
        when(pagoService.obtenerEstatusPago(anyLong())).thenReturn(DataProvider.getPagoError());
        mockMvc.perform(get("/api/pago/1"))
                .andExpect(status().is(404));
    }

     @Test
     public void actualizaEstatusPagoTest() throws Exception {
         ActualizaEstatusRequest actualizaEstatusRequest = ActualizaEstatusRequest.builder()
                 .idEstatusPago(1)
                 .idPago(1L)
                 .build();
         when(pagoService.actualizaEstatusPago(any())).thenReturn(DataProvider.actualizaEstatusPago());
         mockMvc.perform(post("/api/pago/actualizarEstatus").content(objectMapper.writeValueAsString(actualizaEstatusRequest))
                         .contentType(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk());
     }

    @Test
    public void actualizaEstatusPagoErrorTest() throws Exception {
        ActualizaEstatusRequest actualizaEstatusRequest = ActualizaEstatusRequest.builder()
                .idEstatusPago(1)
                .idPago(1L)
                .build();
        when(pagoService.actualizaEstatusPago(any())).thenReturn(DataProvider.actualizaEstatusPagoError());
        mockMvc.perform(post("/api/pago/actualizarEstatus").content(objectMapper.writeValueAsString(actualizaEstatusRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }
}
