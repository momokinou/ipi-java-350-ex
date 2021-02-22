package com.ipiecoles.java.java350.service;

import java.time.LocalDate;
import java.util.List;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeServiceIntegrationTest {
    @Autowired
    EmployeService employeService;

    @Autowired
    private EmployeRepository employeRepository;

    @BeforeEach
    @AfterEach
    public void purge(){
        employeRepository.deleteAll();
    }

    @Test
    public void testIntegrationCalculPerformanceCommercial() throws EmployeException {
        // Given
        String nom = "Doe";
        String prenom = "John";
        Poste poste = Poste.COMMERCIAL;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1.0;

        //When
        employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);
        List<Employe> employes = employeRepository.findAll();
        Assertions.assertThat(employes).hasSize(1);
        Employe employe = employes.get(0);
        employeService.calculPerformanceCommercial(employe.getMatricule(), 1000L, 1000L);
        //Then
        Assertions.assertThat(employe.getPerformance()).isEqualTo(1);
    }
}