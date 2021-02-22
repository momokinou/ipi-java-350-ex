/* package com.ipiecoles.java.java350.service;

import java.time.LocalDate;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class EmployeServiceIntegrationTest {
    @Autowired
    EmployeService employeService;

    @Autowired
    private EmployeRepository employeRepository;

    @Test
    public void testIntegrationCalculPerformanceCommercial() throws EmployeException {
        // Given
        employeRepository.save(new Employe("Moriceau", "Quentin", "C00001", LocalDate.now(), Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));
        employeService.embaucheEmploye("Moriceau", "Quentin", Poste.COMMERCIAL, NiveauEtude.BAC, 1.0);
        //When
        Employe employe = employeRepository.findByMatricule("C00001");
        employeService.calculPerformanceCommercial("C00001", 1000L, 1000L);

        //Then
        
        Assertions.assertThat(employe.getPerformance()).isEqualTo(2);
    }
} */