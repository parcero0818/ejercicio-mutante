package com.mercadolibre.ejerciciomutante.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "secuencia")
public class Secuencia {

    private String tipoSecuencia;
    @Id
    private String secuenciaDna;

    public Secuencia(String tipoSecuencia, String[] secuenciaDna) {
        super();
        this.tipoSecuencia = tipoSecuencia;
        this.secuenciaDna = String.join("", secuenciaDna);
    }

    public String getTipoSecuencia() {
        return tipoSecuencia;
    }

    public void setTipoSecuencia(String tipoSecuencia) {
        this.tipoSecuencia = tipoSecuencia;
    }

    public String getSecuenciaDna() {
        return secuenciaDna;
    }

    public void setSecuenciaDna(String secuenciaDna) {
        this.secuenciaDna = secuenciaDna;
    }

}
