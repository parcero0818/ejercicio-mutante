package com.mercadolibre.ejerciciomutante.domain;

import java.io.Serializable;

/**
 * 
 * @author john.ramirez
 *
 */

public class DnaDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7180930490129713878L;
    private String[] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

}
