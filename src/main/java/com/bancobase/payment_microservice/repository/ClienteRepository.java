package com.bancobase.payment_microservice.repository;

import com.bancobase.payment_microservice.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

     Optional<Cliente> findById(Long id);
}
