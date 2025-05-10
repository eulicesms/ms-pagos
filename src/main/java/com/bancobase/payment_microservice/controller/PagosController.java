package com.bancobase.payment_microservice.controller;

import com.bancobase.payment_microservice.dto.Resultado;
import com.bancobase.payment_microservice.dto.*;
import com.bancobase.payment_microservice.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/pago")
@Slf4j
public class PagosController {

    private final PagoService pagoService;

    public PagosController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @Operation(summary = "Guardar pago")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago guardado"),
            @ApiResponse(responseCode = "404", description = "Error al guardar pago")
    })
    @PutMapping
    public ResponseEntity<?> save(@Valid @RequestBody PagoRequestDto pagoRequestDto) {

        log.info("paso request {}", pagoRequestDto);
        Resultado<PagoResponseDto> resultado = this.pagoService.procesaPago(pagoRequestDto);

        return ResponseEntity.ok(resultado);

    }

    @Operation(summary = "Obtiene informacion de pago por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago encontrado"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @GetMapping("/{pagoId}")
    public ResponseEntity<?> getPagoEstatus(@Valid @PathVariable String pagoId) {
        return ResponseEntity.ok(this.pagoService.obtenerEstatusPago(Long.parseLong(pagoId)));
    }

    @Operation(summary = "Actualizar estatus del pago")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estatus actualizado"),
            @ApiResponse(responseCode = "404", description = "Error al actualizar estatus")
    })
    @PostMapping("/actualizarEstatus")
    public ResponseEntity<?> actualizaEstatusPago(@Valid @RequestBody ActualizaEstatusRequest actualizaEstatusRequest) {

        Resultado<String> resultado = this.pagoService.actualizaEstatusPago(actualizaEstatusRequest);

        return ResponseEntity.ok(resultado);
    }

}
