package com.mercadolibre.ejerciciomutante.domain;

/**
 * 
 * @author john.ramirez
 *
 * @param <T>
 */
public class Response {

    private Integer countMutantDna;
    private Integer countHumanDan;
    private Double ratio;

    public Integer getCountMutantDna() {
        return countMutantDna;
    }

    public void setCountMutantDna(Integer countMutantDna) {
        this.countMutantDna = countMutantDna;
    }

    public Integer getCountHumanDan() {
        return countHumanDan;
    }

    public void setCountHumanDan(Integer countHumanDan) {
        this.countHumanDan = countHumanDan;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

}
