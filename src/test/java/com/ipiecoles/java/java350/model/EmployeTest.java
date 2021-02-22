package com.ipiecoles.java.java350.model;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EmployeTest {

    @ParameterizedTest
    @CsvSource({
        "1000, 8, 1080", "2500, 100, 5000", "2000, 0.10, 2200", "1500, 0.01, 1515", "1000, 0, 1000"
    })
    void testAugmenterSalaire(Double salaireBase, Double pourcentage,Double salaireAugmente){
        //Given
        Employe employe = new Employe();
        employe.setSalaire(salaireBase);

        //When
        employe.augmenterSalaire(pourcentage);

        //Then
        Assertions.assertThat(employe.getSalaire()).isEqualTo(salaireAugmente);
    }

    @ParameterizedTest
    @CsvSource({
        "2019, 8", "2021, 10", "2022, 10", "2032, 11", "2026, 9", "2016, 9"
    })
    void testGetNbRtt(Integer year, Integer ret) {
        //Given
        Employe employe = new Employe();

        //When
        Integer nbRtt = employe.getNbRtt(LocalDate.of(year, 1, 1));

        //Then
        Assertions.assertThat(nbRtt).isEqualTo(ret);

    }
}
