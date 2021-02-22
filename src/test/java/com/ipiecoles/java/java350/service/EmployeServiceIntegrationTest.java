package com.ipiecoles.java.java350.service;

import java.time.LocalDate;

import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import com.ipiecoles.java.java350.repository.EmployeRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeServiceIntegrationTest {
    @Autowired
    EmployeService employeService;

    @ParameterizedTest
    @CsvSource({
        "C00001, 900, 1000, 4, 3",  "C00001, 1200, 1000, 2, 4", "C00001, 1201, 1000, 2, 7"
    })
    public void testCalculPerformanceCommercial(String matricule, Long caTraite, Long objectifCa, Integer perfBase, Integer perfAttendu) throws EmployeException {
        //Given
        Employe employe = new Employe("Moriceau", "Quentin", matricule, LocalDate.now(), Entreprise.SALAIRE_BASE, perfBase, 1.0);

        //When
        employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);

        //Then
        Assertions.assertThat(EmployeRepository.getPerformance()).isEqualTo(perfAttendu);
    }
}