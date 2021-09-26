package com.mercadolibre.ejerciciomutante.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.mercadolibre.ejerciciomutante.excepcion.MutanteExcepcion;

@Component
public class Validacion {

    public void validacionEntrada(String[] dna, int index) throws MutanteExcepcion {
        if (!Constantes.PATRON.matcher(dna[index]).matches()) {
            throw new MutanteExcepcion("La entrada solo puede tener las letras acgt", HttpStatus.FORBIDDEN.value());
        }
    }

    public void validacionLongitud(String[] dna, int longitudSecuencia, int index) throws MutanteExcepcion {
        if (dna[index].length() != longitudSecuencia) {
            throw new MutanteExcepcion("Entrada invalida", HttpStatus.FORBIDDEN.value());
        }
    }

    public void validarEntradaVacia(String[] dna) throws MutanteExcepcion {
        if (dna.length == 0) {
            throw new MutanteExcepcion("Entrada invalida", HttpStatus.FORBIDDEN.value());
        }
    }
}
