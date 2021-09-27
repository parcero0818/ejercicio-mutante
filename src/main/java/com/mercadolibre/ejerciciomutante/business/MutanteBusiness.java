package com.mercadolibre.ejerciciomutante.business;

import org.springframework.http.ResponseEntity;

import com.mercadolibre.ejerciciomutante.domain.DnaDTO;
import com.mercadolibre.ejerciciomutante.domain.Response;

public interface MutanteBusiness {

    ResponseEntity<String> validarMutante(DnaDTO dnaDto);

    ResponseEntity<Response> getEstado();
}
