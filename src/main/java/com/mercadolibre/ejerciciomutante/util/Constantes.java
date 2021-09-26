package com.mercadolibre.ejerciciomutante.util;

import java.util.regex.Pattern;

/**
 * 
 * @author john.ramirez
 *
 */
public class Constantes {

    public Constantes() {
        // TODO Auto-generated constructor stub
    }

    public static final Pattern PATRON = Pattern.compile("[ACGT]+", Pattern.CASE_INSENSITIVE);
    public static final String MUTANTE_A = "aaaa";
    public static final String MUTANTE_C = "cccc";
    public static final String MUTANTE_G = "gggg";
    public static final String MUTANTE_T = "tttt";

    public static final String ES_MUTANTE = "Es mutante";
    public static final String NO_ES_MUTANTE = "No es mutante";
    public static final String DATOS_INVALIDOS = "Datos ingresados son invalidos";
    public static final String ERROR = "Ocurrio un error";

    public static final String MUTANTE = "MUTANTE";
    public static final String HUMANO = "HUMANO";

}
