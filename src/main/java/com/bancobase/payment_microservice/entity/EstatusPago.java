package com.bancobase.payment_microservice.entity;

import com.bancobase.payment_microservice.model.EstatusPagoEnum;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "c_estatus_pago")
public class EstatusPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private EstatusPagoEnum descripcion;
}
