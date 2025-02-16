package es.santander.ascender.ejerc002.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

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


    @Test
    public void delete() {

        Boligrafo boligrafo = new Boligrafo();
        boligrafo.setColor("#0100fe");
        boligrafo.setEscribe(true);
        boligrafo.setNombre("Mi primer boli azul");
        repository.save(boligrafo);

        assertTrue(repository.existsById(boligrafo.getId()));

        // Borrar registro y comprobar que fue borrado
        repository.deleteById(boligrafo.getId());

        assertFalse(repository.existsById(boligrafo.getId()));
    }

    @Test
    public void view() {

        String color = "#2f0189";
        Boligrafo boligrafo = new Boligrafo();
        boligrafo.setColor(color);
        boligrafo.setEscribe(true);
        boligrafo.setNombre("Mi firmador de contratos");
        repository.save(boligrafo);

        Optional<Boligrafo> registro = repository.findById(boligrafo.getId());

        assertTrue(registro.isPresent());
        assertTrue(registro.get().getColor().equals(color));
    }

    @Test
    public void update() {

        String colorOriginal = "#2f0189";
        String colorNuevo = "#de0189";
        Boligrafo boligrafo = new Boligrafo();
        boligrafo.setColor(colorOriginal);
        boligrafo.setEscribe(true);
        boligrafo.setNombre("Para cartas de amor");
        repository.save(boligrafo);

        assertTrue(repository.existsById(boligrafo.getId()));

        boligrafo.setColor(colorNuevo);
        repository.save(boligrafo);

        Optional<Boligrafo> updatedBoligrafo = repository.findById(boligrafo.getId());

        assertTrue(updatedBoligrafo.isPresent());
        assertTrue(updatedBoligrafo.get().getColor().equals(colorNuevo));
    }

}
