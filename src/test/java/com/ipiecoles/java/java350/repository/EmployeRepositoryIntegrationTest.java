package com.ipiecoles.java.java350.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import com.ipiecoles.java.java350.service.EmployeService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeRepositoryIntegrationTest {
    @Autowired
    EmployeService employeService;

    @Autowired
    private EmployeRepository employeRepository;

    @BeforeEach
    @AfterEach
    public void purge(){
        employeRepository.deleteAll();
    }

    @ParameterizedTest
    @CsvSource({
        "C", "T", "M"
    })
    void testAvgPerformanceWhereMatriculeStartsWith(String key) {
        employeRepository.save(new Employe("Doe", "John", "C12389", LocalDate.now(), Entreprise.SALAIRE_BASE, 2, 1.0));
        employeRepository.save(new Employe("Doe", "John", "T12895", LocalDate.now(), Entreprise.SALAIRE_BASE, 2, 1.0));
        employeRepository.save(new Employe("Doe", "John", "M12257", LocalDate.now(), Entreprise.SALAIRE_BASE, 2, 1.0));

        Assertions.assertThat(employeRepository.avgPerformanceWhereMatriculeStartsWith(key)).isEqualTo(2);
    }
}