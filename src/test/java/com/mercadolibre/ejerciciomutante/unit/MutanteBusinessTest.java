package com.mercadolibre.ejerciciomutante.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.mercadolibre.ejerciciomutante.EjercicioMutanteApplication;
import com.mercadolibre.ejerciciomutante.business.MutanteBusiness;
import com.mercadolibre.ejerciciomutante.domain.DnaDTO;
import com.mercadolibre.ejerciciomutante.domain.Response;
import com.mercadolibre.ejerciciomutante.repository.SecuenciaRepository;

@SpringBootTest(classes = { EjercicioMutanteApplication.class })
@ActiveProfiles("test")
public class MutanteBusinessTest {

    private final String[] DNA_MUTANT = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
    private final String[] DNA_HUMAN = { "ATGCCA", "CCGTGC", "TTATGT", "AGAAGG", "CTCCTA", "TCACTG" };
    private final String[] DNA_INVALID = { "KKKKK", "CCGTGC", "TTATGT", "AGAAGG", "CTCCTA", "TCACTG" };

    @Autowired
    private MutanteBusiness business;
    @MockBean
    private SecuenciaRepository secuenciaRepository;

    @Test
    void validateDnaMutant() {
        DnaDTO dnaDto = new DnaDTO();
        dnaDto.setDna(DNA_MUTANT);

        ResponseEntity<String> response = this.business.validarMutante(dnaDto);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    void validateDnaHuman() {
        DnaDTO dnaDto = new DnaDTO();
        dnaDto.setDna(DNA_HUMAN);

        ResponseEntity<String> response = this.business.validarMutante(dnaDto);

        assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatusCodeValue());
    }

    @Test
    void validateDnaWhenRequestInvalid() {
        DnaDTO dnaDto = new DnaDTO();
        dnaDto.setDna(null);

        ResponseEntity<String> response = this.business.validarMutante(dnaDto);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
    }

    @Test
    void validateDnaWhenNotMatcher() {
        DnaDTO dnaDto = new DnaDTO();
        dnaDto.setDna(DNA_INVALID);

        ResponseEntity<String> response = this.business.validarMutante(dnaDto);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
    }

    @Test
    void getEstado() {

        ResponseEntity<Response> response = this.business.getEstado();

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

}
