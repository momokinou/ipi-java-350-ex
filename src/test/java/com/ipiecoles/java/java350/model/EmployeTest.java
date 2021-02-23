package com.ipiecoles.java.java350.model;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EmployeTest {

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

    @Test
    void getNombreAnneeAncienneteNow(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now());

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(anneeAnciennete.intValue()).isZero();
    }

    @Test
    void getNombreAnneeAncienneteNull(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(null);

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(anneeAnciennete).isEqualTo(0);
    }

    @Test
    void getNombreAnneeAncienneteM2(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now().minusYears(2L));

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(anneeAnciennete.intValue()).isEqualTo(2);
    }

    @Test
    void getNombreAnneeAncienneteP2(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now().plusYears(2L));

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(anneeAnciennete).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 'T12345', 0, 1.0, 1000.0",
            "1, 'T12345', 2, 0.5, 600.0",
            "1, 'T12345', 2, 1.0, 1200.0",
            "2, 'T12345', 0, 1.0, 2300.0",
            "2, 'T12345', 1, 1.0, 2400.0",
            "1, 'M12345', 0, 1.0, 1700.0",
            "1, 'M12345', 5, 1.0, 2200.0",
            "2, 'M12345', 0, 1.0, 1700.0",
            "2, 'M12345', 8, 1.0, 2500.0"
    })
    void getPrimeAnnuelle(Integer performance, String matricule, Long nbYearsAnciennete, Double tempsPartiel, Double primeAnnuelle){
        //Given
        Employe employe = new Employe("Doe", "John", matricule, LocalDate.now().minusYears(nbYearsAnciennete), Entreprise.SALAIRE_BASE, performance, tempsPartiel);

        //When
        Double prime = employe.getPrimeAnnuelle();

        //Then
        Assertions.assertThat(prime).isEqualTo(primeAnnuelle);

    }
    
    @Test
    void getPrimeAnnuelleMatriculeNull() {
        //Given
        Employe employe = new Employe("Doe", "John", null, LocalDate.now(), 1500d, 1, 1.0);

        //When
        Double prime = employe.getPrimeAnnuelle();

        //Then
        Assertions.assertThat(prime).isEqualTo(1000);
    }

    @Test
    void getPrimeAnnuellePerfNull() {
        //Given
        Employe employe = new Employe("Doe", "John", "T12345", LocalDate.now(), 1500d, null, 1.0);

        //When
        Double prime = employe.getPrimeAnnuelle();

        //Then
        Assertions.assertThat(prime).isEqualTo(1000);
    }
}
