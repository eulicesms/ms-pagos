package com.bancobase.payment_microservice.controller;

import com.bancobase.payment_microservice.dto.Resultado;
import com.bancobase.payment_microservice.dto.*;
import com.bancobase.payment_microservice.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/pago")
public class PagosController {

    private final PagoService pagoService;

    public PagosController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @Operation(
            summary = "Guardar Pago",
            description = "Guarda la informacion relacionada al pago que hace un cliente a otro cliente.",
            tags = {"Guardar Pago"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto que se debe enviar en la peticion para el guardado de un pago",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PagoRequestDto.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recurso creado",
                    content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Resultado.class))
            ),
            @ApiResponse(responseCode = "400", description = "Peticion inconrrecta [BAD_REQUEST]",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Resultado.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado [NOT FOUND]",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Resultado.class)
                    )
            )
    })
    @PutMapping
    public ResponseEntity<?> save(@Valid @RequestBody PagoRequestDto pagoRequestDto) {

        Resultado<PagoResponseDto> resultado = this.pagoService.procesaPago(pagoRequestDto);
        if(resultado.isSuccessful()) {
            resultado.setCode(HttpStatus.CREATED.toString());
            return new ResponseEntity<>(resultado, HttpStatus.CREATED);
        }

        resultado.setCode(HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);

    }

    @Operation(
            summary = "Obtener informacion de un Pago",
            description = "Obtiene la informacion completa de un pago.",
            tags = {"Obtener Pago por ID"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recurso encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Resultado.class))
            ),
            @ApiResponse(responseCode = "400", description = "Peticion inconrrecta [BAD_REQUEST]",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Resultado.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado [NOT FOUND]",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Resultado.class)
                    )
            )
    })
    @GetMapping("/{pagoId}")
    public ResponseEntity<?> getPagoEstatus(@Valid @PathVariable String pagoId) {

        Resultado<PagoResponseDto> resultado = this.pagoService.obtenerEstatusPago(Long.parseLong(pagoId));
        if(resultado.isSuccessful())
            return ResponseEntity.ok(resultado);

        resultado.setCode(HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);

    }

    @Operation(
            summary = "Actualizar estatus de Pago",
            description = "Actualiza el estatus de un pago.",
            tags = {"Actualizar estatus de Pago"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto que se debe enviar en la peticion para actualizar el estatus de un pago",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ActualizaEstatusRequest.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recurso actualizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Resultado.class))
            ),
            @ApiResponse(responseCode = "400", description = "Peticion inconrrecta [BAD_REQUEST]",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Resultado.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado [NOT FOUND]",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Resultado.class)
                    )
            )
    })
    @PostMapping("/actualizarEstatus")
    public ResponseEntity<?> actualizaEstatusPago(@Valid @RequestBody ActualizaEstatusRequest actualizaEstatusRequest) {

        Resultado<String> resultado = this.pagoService.actualizaEstatusPago(actualizaEstatusRequest);
        if(resultado.isSuccessful())
            return ResponseEntity.ok(resultado);

        resultado.setCode(HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
    }

}
