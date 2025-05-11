package com.bancobase.payment_microservice.util;

import com.bancobase.payment_microservice.entity.Cliente;
import com.bancobase.payment_microservice.entity.EstatusPago;
import com.bancobase.payment_microservice.model.EstatusPagoEnum;
import com.bancobase.payment_microservice.repository.ClienteRepository;
import com.bancobase.payment_microservice.repository.EstatusPagoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PoblarTablas implements CommandLineRunner {

    private final ClienteRepository clienteRepository;
    private final EstatusPagoRepository estatusPagoRepository;

    public PoblarTablas(ClienteRepository clienteRepository, EstatusPagoRepository estatusPagoRepository) {
        this.clienteRepository = clienteRepository;
        this.estatusPagoRepository = estatusPagoRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        try {
            if (this.clienteRepository.findAll().isEmpty()) {
                this.clienteRepository.saveAll(
                        List.of(
                                Cliente.builder().nombre("Luis").apellidoPaterno("Lopez").apellidoMaterno("Cruz").build(),
                                Cliente.builder().nombre("Fernando").apellidoPaterno("Martinez").apellidoMaterno("Sanchez").build(),
                                Cliente.builder().nombre("Jose").apellidoPaterno("Benitez").apellidoMaterno("Carbajal").build(),
                                Cliente.builder().nombre("Maria").apellidoPaterno("Cruz").apellidoMaterno("Toledo").build(),
                                Cliente.builder().nombre("Hugo").apellidoPaterno("Perez").apellidoMaterno("Cruz").build(),
                                Cliente.builder().nombre("Gloria").apellidoPaterno("Valencia").apellidoMaterno("Sanchez").build(),
                                Cliente.builder().nombre("Fernanda").apellidoPaterno("Hernandez").apellidoMaterno("Mendez").build(),
                                Cliente.builder().nombre("Julio").apellidoPaterno("Toledo").apellidoMaterno("Vazquez").build(),
                                Cliente.builder().nombre("Mauro").apellidoPaterno("Hernandez").apellidoMaterno("Lamin").build(),
                                Cliente.builder().nombre("Valeria").apellidoPaterno("Ruiz").apellidoMaterno("Vela").build(),
                                Cliente.builder().nombre("Eduardo").apellidoPaterno("Toledo").apellidoMaterno("Soto").build(),
                                Cliente.builder().nombre("Larisa").apellidoPaterno("Padilla").apellidoMaterno("Gonzalez").build(),
                                Cliente.builder().nombre("Karen").apellidoPaterno("Flores").apellidoMaterno("Duarte").build(),
                                Cliente.builder().nombre("Gerardo").apellidoPaterno("Lamin").apellidoMaterno("Vela").build(),
                                Cliente.builder().nombre("Sofia").apellidoPaterno("Soto").apellidoMaterno("Toledo").build(),
                                Cliente.builder().nombre("Fabiola").apellidoPaterno("Toledo").apellidoMaterno("Juarez").build(),
                                Cliente.builder().nombre("Remedios").apellidoPaterno("Duarte").apellidoMaterno("Morelos").build()
                        )
                );
            }

            if(this.estatusPagoRepository.findAll().isEmpty()) {
                this.estatusPagoRepository.saveAll(
                       List.of(
                               EstatusPago.builder().descripcion(EstatusPagoEnum.EN_PROCESO).build(),
                               EstatusPago.builder().descripcion(EstatusPagoEnum.CANCELADO).build(),
                               EstatusPago.builder().descripcion(EstatusPagoEnum.DEVUELTO).build(),
                               EstatusPago.builder().descripcion(EstatusPagoEnum.LIQUIDADO).build(),
                               EstatusPago.builder().descripcion(EstatusPagoEnum.NO_ENCONTRADO).build(),
                               EstatusPago.builder().descripcion(EstatusPagoEnum.RECHAZADO).build()
                       )
                );
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
