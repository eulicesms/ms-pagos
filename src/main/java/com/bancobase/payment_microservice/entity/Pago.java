package com.bancobase.payment_microservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String concepto;

    @ManyToOne
    @JoinColumn(name = "estatus_pago_id") // Definición de la columna vinculada
    private EstatusPago estatusPago;

    @ManyToOne
    @JoinColumn(name = "c_realiza_pago_id")
    private Cliente clienteRealizaPago;

    private BigDecimal monto;

    @ManyToOne
    @JoinColumn(name = "c_recibe_pago_id")
    private Cliente clienteRecibePago;

    private Timestamp fechaPago;


}
