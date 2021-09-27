package com.mercadolibre.ejerciciomutante.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mercadolibre.ejerciciomutante.domain.DnaDTO;
import com.mercadolibre.ejerciciomutante.domain.Response;
import com.mercadolibre.ejerciciomutante.excepcion.MutanteExcepcion;
import com.mercadolibre.ejerciciomutante.model.Secuencia;
import com.mercadolibre.ejerciciomutante.repository.SecuenciaRepository;
import com.mercadolibre.ejerciciomutante.util.Constantes;
import com.mercadolibre.ejerciciomutante.util.Validacion;

/**
 * 
 * @author john.ramirez
 *
 */
@Service
public class MutanteBusinessImpl implements MutanteBusiness {

    private final Logger LOGGER = LoggerFactory.getLogger(MutanteBusinessImpl.class);
    private final Validacion validacion;
    private final SecuenciaRepository secuenciaRepository;

    @Autowired
    public MutanteBusinessImpl(Validacion validacion, SecuenciaRepository secuenciaRepository) {
        this.validacion = validacion;
        this.secuenciaRepository = secuenciaRepository;
    }

    /**
     * Permite validar la cadena de adn ingresada para conocer si es de un mutante o de un humano
     */
    @Override
    public ResponseEntity<String> validarMutante(DnaDTO dnaDto) {
        try {
            String[] dna = dnaDto.getDna();
            this.validacion.validarEntradaVacia(dna);
            int longitudSecuencia = dnaDto.getDna().length;

            char[][] secuencias = new char[longitudSecuencia][longitudSecuencia];
            for (int i = 0; i < longitudSecuencia; i++) {
                this.validacion.validacionEntrada(dna, i);
                this.validacion.validacionLongitud(dna, longitudSecuencia, i);
                secuencias[i] = dna[i].toCharArray();
            }
            List<String> combinaciones = this.obtenerCombinaciones(dna, secuencias, longitudSecuencia);
            boolean esMutante = validarEsMutante(combinaciones);
            if (esMutante) {
                this.guardarSecuencia(Constantes.MUTANTE, dna);
                return ResponseEntity.status(HttpStatus.OK).body(Constantes.ES_MUTANTE);
            }
            this.guardarSecuencia(Constantes.HUMANO, dna);
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(Constantes.NO_ES_MUTANTE);
        } catch (MutanteExcepcion e) {
            LOGGER.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(Constantes.DATOS_INVALIDOS);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(Constantes.ERROR);
        }
    }

    /**
     * Permite obtener las diferentes combinaciones de la matriz para verificar si tiene una cadena mutante
     * 
     * @param dna
     * @param secuencias
     * @param longitudSecuencia
     * @return
     */
    private List<String> obtenerCombinaciones(String[] dna, char[][] secuencias, int longitudSecuencia) {
        List<String> combinaciones = new ArrayList<>();
        combinaciones.addAll(Arrays.asList(dna));
        combinaciones.addAll(this.obtenerColumnas(secuencias, longitudSecuencia));
        combinaciones.addAll(this.obtenerInfIzq(secuencias, longitudSecuencia));
        combinaciones.addAll(this.obtenerSupIzq(secuencias, longitudSecuencia));
        combinaciones.addAll(this.obtenerInfDer(secuencias, longitudSecuencia));
        combinaciones.addAll(this.obtenerSupDer(secuencias, longitudSecuencia));

        return combinaciones;
    }

    /**
     * Permite obtener las combinaciones de las columnas de la matriz
     * 
     * @param secuenciaDna
     * @param longitudSecuencia
     * @return
     */
    private List<String> obtenerColumnas(char[][] secuenciaDna, int longitudSecuencia) {
        List<String> combinaciones = new ArrayList<>();
        for (int i = 0; i < longitudSecuencia; i++) {
            String columnas = "";
            for (int j = 0; j < longitudSecuencia; j++) {
                columnas += secuenciaDna[j][i];
            }
            combinaciones.add(columnas);
        }
        return combinaciones;
    }

    /**
     * Permite obtener las combinaciones del inferior izquierdo de la matriz
     * 
     * @param secuenciaDna
     * @param longitudSecuencia
     * @return
     */
    private List<String> obtenerInfIzq(char[][] secuenciaDna, int longitudSecuencia) {
        List<String> combinaciones = new ArrayList<>();
        for (int i = 3; i < longitudSecuencia; i++) {
            String combinacion = "";
            int range = i;
            for (int j = 0; j < longitudSecuencia && range >= 0; j++) {
                combinacion += secuenciaDna[range][j];
                range -= 1;
            }
            combinaciones.add(combinacion);
        }
        return combinaciones;
    }

    /**
     * Permite obtener las combinaciones del superior izquierdo de la matriz
     * 
     * @param secuenciaDna
     * @param longitudSecuencia
     * @return
     */
    private List<String> obtenerSupIzq(char[][] secuenciaDna, int longitudSecuencia) {
        List<String> combinaciones = new ArrayList<>();
        for (int i = 1; i < longitudSecuencia - 3; i++) {
            String combinacion = "";
            int range = i;
            for (int j = longitudSecuencia - 1; j >= i; j--) {
                combinacion += secuenciaDna[j][range];
                range += 1;
            }
            combinaciones.add(combinacion);
        }
        return combinaciones;
    }

    /**
     * Permite obtener las combinaciones del inferior derecho de la matriz
     * 
     * @param secuenciaDna
     * @param longitudSecuencia
     * @return
     */
    private List<String> obtenerInfDer(char[][] secuenciaDna, int longitudSecuencia) {
        List<String> combinaciones = new ArrayList<>();
        for (int i = 0; i < longitudSecuencia - 3; i++) {
            String combinacion = "";
            int range = i;
            for (int j = 0; j < longitudSecuencia - i; j++) {
                combinacion += secuenciaDna[range][j];
                range++;
            }
            combinaciones.add(combinacion);
        }
        return combinaciones;
    }

    /**
     * Permite obtener las combinaciones del superior derecho de la matriz
     * 
     * @param secuenciaDna
     * @param longitudSecuencia
     * @return
     */
    private List<String> obtenerSupDer(char[][] secuenciaDna, int longitudSecuencia) {
        List<String> combinaciones = new ArrayList<>();
        for (int i = 1; i < longitudSecuencia - 3; i++) {
            String combinacion = "";
            int range = i;
            for (int j = 0; j < longitudSecuencia - i; j++) {
                combinacion += secuenciaDna[j][range];
                range++;
            }
            combinaciones.add(combinacion);
        }
        return combinaciones;
    }

    /**
     * Permite validar si se encontro una cadena mutante en las combinaciones obtenidas
     * 
     * @param combinaciones
     * @return
     */
    private boolean validarEsMutante(List<String> combinaciones) {
        List<String> cadenasMutantes = combinaciones.parallelStream()
                .filter(elt -> elt.toLowerCase().contains(Constantes.MUTANTE_A) || elt.toLowerCase().contains(Constantes.MUTANTE_G)
                        || elt.toLowerCase().contains(Constantes.MUTANTE_T) || elt.toLowerCase().contains(Constantes.MUTANTE_C))
                .collect(Collectors.toList());

        return !cadenasMutantes.isEmpty();

    }

    /**
     * Permite guardar la secuencia analizada
     * 
     * @param tipo
     * @param secuenciaDna
     */
    private void guardarSecuencia(String tipo, String[] secuenciaDna) {
        Secuencia secuencia = new Secuencia(tipo, secuenciaDna);
        this.secuenciaRepository.save(secuencia);
    }

    /**
     * Permite conocer las estadisticas de los mutantes y humanos analizados
     */
    @Override
    public ResponseEntity<Response> getEstado() {
        Response response = null;
        try {
            Integer mutantes = this.secuenciaRepository.countByTipoSecuencia(Constantes.MUTANTE);
            Integer humanos = this.secuenciaRepository.countByTipoSecuencia(Constantes.HUMANO);
            response = new Response();
            response.setCountMutantDna(mutantes);
            response.setCountHumanDan(humanos);
            if (mutantes > 0 && humanos > 0) {
                response.setRatio((double) ((100 * mutantes) / humanos) / 100);
            } else {
                response.setRatio(0.0);
            }

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(null);
        }
    }

}
