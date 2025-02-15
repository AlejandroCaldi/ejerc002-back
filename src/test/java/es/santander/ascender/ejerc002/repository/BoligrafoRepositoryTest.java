package es.santander.ascender.ejerc002.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.santander.ascender.ejerc002.model.Boligrafo;

@SpringBootTest
public class BoligrafoRepositoryTest {

    @Autowired
    private BoligrafoRepository repository;

    @Test
    public void testCreate() {
        Boligrafo boligrafo = new Boligrafo();
        boligrafo.setNombre("La firma de un grande");


        repository.save(boligrafo);

        assertTrue(
            repository
                .findById(boligrafo.getId())
                .isPresent());
    }

}
