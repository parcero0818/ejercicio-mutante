package com.mercadolibre.ejerciciomutante.excepcion;

public class MutanteExcepcion extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 5584872606780004066L;
    private final int status;

    public MutanteExcepcion(String mensajeUsuario, int status) {
        super(mensajeUsuario);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
