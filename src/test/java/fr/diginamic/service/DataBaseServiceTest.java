package fr.diginamic.service;

import fr.diginamic.services.DataBaseService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataBaseServiceTest {

    @BeforeAll
    public static void setUpBeforAllTests() {
        System.out.println("Befor all");
    }

    @BeforeEach
    public void setUpBeforeEachTests() {
        System.out.println("Before");
    }

    @Test
    public void testConnexioDataBase() {

        EntityManager em = DataBaseService.connexionDataBase();
        assertNotNull(em);

        em.close();
    }

    @AfterEach
    public void tearDownAfterEachTests() {
        System.out.println("After");
    }

    @AfterAll
    public static void tearDownAfterAllTests() {
        System.out.println("After all");
    }
}
