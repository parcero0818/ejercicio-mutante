package com.mercadolibre.ejerciciomutante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.ejerciciomutante.business.MutanteService;
import com.mercadolibre.ejerciciomutante.domain.DnaDTO;

/**
 * 
 * @author john.ramirez
 *
 */

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(value = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class MutanteController {

    @Autowired
    MutanteService service;

    @PostMapping(value = "validacion-dna", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> validateDna(@RequestBody(required = true) DnaDTO dnaDto) {
        return this.service.validarMutante(dnaDto);
    }

}
