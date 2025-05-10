package com.bancobase.payment_microservice.repository;

import com.bancobase.payment_microservice.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagosRepository extends JpaRepository<Pago, Long> {
}
