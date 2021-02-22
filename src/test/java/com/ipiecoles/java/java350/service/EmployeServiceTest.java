package com.ipiecoles.java.java350.service;

import static org.mockito.Mockito.times;

import java.time.LocalDate;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import com.ipiecoles.java.java350.repository.EmployeRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class EmployeServiceTest{
    @InjectMocks
    EmployeService employeService;

    @Mock
    EmployeRepository employeRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this.getClass());
    }



    @Test
    void testCalculPerformanceCommercialCaTraiteNull() throws EmployeException {
        //Given
        //Employe employe = new Employe("Moriceau", "Quentin", "C00001", LocalDate.now(), Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0);
        //Mockito.when(employeRepository.findByMatricule("C00001")).thenReturn(employe);

        //When
        
        //ArgumentCaptor<Employe> employeArgumentCaptor = ArgumentCaptor.forClass(Employe.class);
        //Mockito.verify(employeRepository, times(1)).save(employeArgumentCaptor.capture());

        //Then
        try{
            employeService.calculPerformanceCommercial("C00001", null, 1000L);
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("Le chiffre d'affaire traité ne peut être négatif ou null !");
        }
    }

    @Test
    void testCalculPerformanceCommercialObjectifCaNull() throws EmployeException {
        //Given
        //Employe employe = new Employe("Moriceau", "Quentin", "C00001", LocalDate.now(), Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0);
        //Mockito.when(employeRepository.findByMatricule("C00001")).thenReturn(employe);

        //When
        //ArgumentCaptor<Employe> employeArgumentCaptor = ArgumentCaptor.forClass(Employe.class);
        //Mockito.verify(employeRepository, times(1)).save(employeArgumentCaptor.capture());

        //Then
        try{
            employeService.calculPerformanceCommercial("C00001", 1000L, null);
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("L'objectif de chiffre d'affaire ne peut être négatif ou null !");
        }
    }

   @Test
    void testCalculPerformanceCommercialMauvaisMatricule() throws EmployeException {
        //Given
        //Employe employe = new Employe("Moriceau", "Quentin", "T00001", LocalDate.now(), Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0);
        //Mockito.when(employeRepository.findByMatricule("T00001")).thenReturn(employe);

        //When
        //ArgumentCaptor<Employe> employeArgumentCaptor = ArgumentCaptor.forClass(Employe.class);
        //Mockito.verify(employeRepository, times(1)).save(employeArgumentCaptor.capture());

        //Then
        try{
            employeService.calculPerformanceCommercial("T00001", 1000L, 1000L);
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("Le matricule ne peut être null et doit commencer par un C !");
        }
    }

    @Test
    void testCalculPerformanceCommercialMatriculNull() throws EmployeException {
        //Given
        //Employe employe = new Employe("Moriceau", "Quentin", "C00001", LocalDate.now(), Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0);
        //Mockito.when(employeRepository.findByMatricule("C00001")).thenReturn(employe);

        //When
        //ArgumentCaptor<Employe> employeArgumentCaptor = ArgumentCaptor.forClass(Employe.class);
        //Mockito.verify(employeRepository, times(1)).save(employeArgumentCaptor.capture());

        //Then
        try{
            employeService.calculPerformanceCommercial(null, 1000L, 1000L);
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("Le matricule ne peut être null et doit commencer par un C !");
        }
    }

    @Test
    void testCalculPerformanceCommercialMatriculNonExistant() throws EmployeException {
        //Given
        //Employe employe = new Employe("Moriceau", "Quentin", "C00001", LocalDate.now(), Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0);
        //Mockito.when(employeRepository.findByMatricule("C00001")).thenReturn(employe);

        //When
        //ArgumentCaptor<Employe> employeArgumentCaptor = ArgumentCaptor.forClass(Employe.class);
        //Mockito.verify(employeRepository, times(1)).save(employeArgumentCaptor.capture());

        //Then
        try{
            employeService.calculPerformanceCommercial("C00002", 1000L, 1000L);
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("Le matricule C00002 n'existe pas !");
        }
    }

    @ParameterizedTest
    @CsvSource({
        "C00001, 900, 1000, 4, 3",  "C00001, 1200, 1000, 2, 4", "C00001, 1201, 1000, 2, 7"
    })
    void testCalculPerformanceCommercial(String matricule, Long caTraite, Long objectifCa, Integer perfBase, Integer perfAttendu) throws EmployeException {
        //Given
        Employe employe = new Employe("Moriceau", "Quentin", matricule, LocalDate.now(), Entreprise.SALAIRE_BASE, perfBase, 1.0);
        Mockito.when(employeRepository.findByMatricule(matricule)).thenReturn(employe);

        //When
        employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);
        ArgumentCaptor<Employe> employeArgumentCaptor = ArgumentCaptor.forClass(Employe.class);
        Mockito.verify(employeRepository, times(1)).save(employeArgumentCaptor.capture());

        //Then
        Assertions.assertThat(employeArgumentCaptor.getValue().getPerformance()).isEqualTo(perfAttendu);
    }
}