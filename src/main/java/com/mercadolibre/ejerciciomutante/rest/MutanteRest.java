package com.mercadolibre.ejerciciomutante.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.ejerciciomutante.business.MutanteBusiness;
import com.mercadolibre.ejerciciomutante.domain.DnaDTO;
import com.mercadolibre.ejerciciomutante.domain.Response;

/**
 * 
 * @author john.ramirez
 *
 */

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(value = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class MutanteRest {

    MutanteBusiness business;

    @Autowired
    public MutanteRest(MutanteBusiness service) {
        super();
        this.business = service;
    }

    @PostMapping(value = "mutant", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> validateDna(@RequestBody(required = true) DnaDTO dnaDto) {
        return this.business.validarMutante(dnaDto);
    }

    @GetMapping(value = "status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getEstado() {
        return this.business.getEstado();
    }

}
