package com.mercadolibre.ejerciciomutante.business;

import org.springframework.http.ResponseEntity;

import com.mercadolibre.ejerciciomutante.domain.DnaDTO;

public interface MutanteService {

    ResponseEntity<String> validarMutante(DnaDTO dnaDto);

}
