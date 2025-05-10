package com.bancobase.payment_microservice.repository;

import com.bancobase.payment_microservice.entity.EstatusPago;
import com.bancobase.payment_microservice.model.EstatusPagoEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstatusPagoRepository extends JpaRepository<EstatusPago, Integer> {

    Optional<EstatusPago> findByDescripcion(EstatusPagoEnum estatus);
}
