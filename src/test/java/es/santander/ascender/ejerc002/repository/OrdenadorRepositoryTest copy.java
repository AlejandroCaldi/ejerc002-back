package es.santander.ascender.ejerc002.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.santander.ascender.ejerc002.model.Ordenador;

@SpringBootTest
public class OrdenadorRepositoryTest {

    @Autowired
    private OrdenadorRepository repository;

    @Test
    public void testCreate() {
        Ordenador ordenador = new Ordenador();
        ordenador.setNombre("La firma de un grande");


        repository.save(ordenador);

        assertTrue(
            repository
                .findById(ordenador.getId())
                .isPresent());
    }


    @Test
    public void delete() {

        Ordenador ordenador = new Ordenador();
        ordenador.setColor("#0100fe");
        ordenador.setEscribe(true);
        ordenador.setNombre("Mi primer boli azul");
        repository.save(ordenador);

        assertTrue(repository.existsById(ordenador.getId()));

        // Borrar registro y comprobar que fue borrado
        repository.deleteById(ordenador.getId());

        assertFalse(repository.existsById(ordenador.getId()));
    }

    @Test
    public void view() {

        String color = "#2f0189";
        Ordenador ordenador = new Ordenador();
        ordenador.setColor(color);
        ordenador.setEscribe(true);
        ordenador.setNombre("Mi firmador de contratos");
        repository.save(ordenador);

        Optional<Ordenador> registro = repository.findById(ordenador.getId());

        assertTrue(registro.isPresent());
        assertTrue(registro.get().getColor().equals(color));
    }

    @Test
    public void update() {

        String colorOriginal = "#2f0189";
        String colorNuevo = "#de0189";
        Ordenador ordenador = new Ordenador();
        ordenador.setColor(colorOriginal);
        ordenador.setEscribe(true);
        ordenador.setNombre("Para cartas de amor");
        repository.save(ordenador);

        assertTrue(repository.existsById(ordenador.getId()));

        ordenador.setColor(colorNuevo);
        repository.save(ordenador);

        Optional<Ordenador> updatedOrdenador = repository.findById(ordenador.getId());

        assertTrue(updatedOrdenador.isPresent());
        assertTrue(updatedOrdenador.get().getColor().equals(colorNuevo));
    }

}
