package com.mercadolibre.ejerciciomutante.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mercadolibre.ejerciciomutante.model.Secuencia;

public interface SecuenciaRepository extends MongoRepository<Secuencia, String> {

    Optional<Secuencia> findById(String secuenciaDna);

}
