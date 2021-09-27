package com.mercadolibre.ejerciciomutante.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.mercadolibre.ejerciciomutante.excepcion.MutanteExcepcion;

@Component
public class Validacion {
    /**
     * Permite validar que la cadena de adn que ingresen solo este compuesta por las letras validas
     * 
     * @param dna
     * @param index
     * @throws MutanteExcepcion
     */
    public void validacionEntrada(String[] dna, int index) throws MutanteExcepcion {
        if (!Constantes.PATRON.matcher(dna[index]).matches()) {
            throw new MutanteExcepcion("La entrada solo puede tener las letras acgt", HttpStatus.FORBIDDEN.value());
        }
    }

    /**
     * Permite validar que la secuencia de entrada sea correcta en cuanto a su tamano
     * 
     * @param dna
     * @param longitudSecuencia
     * @param index
     * @throws MutanteExcepcion
     */
    public void validacionLongitud(String[] dna, int longitudSecuencia, int index) throws MutanteExcepcion {
        if (dna[index].length() != longitudSecuencia) {
            throw new MutanteExcepcion("Entrada invalida", HttpStatus.FORBIDDEN.value());
        }
    }

    /**
     * Permite validar que la cadena de adn de entrada no sea vacia
     * 
     * @param dna
     * @throws MutanteExcepcion
     */
    public void validarEntradaVacia(String[] dna) throws MutanteExcepcion {
        if (dna == null || dna.length == 0) {
            throw new MutanteExcepcion("Entrada invalida", HttpStatus.FORBIDDEN.value());
        }
    }
}
