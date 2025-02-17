package es.santander.ascender.ejerc002.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.santander.ascender.ejerc002.model.Ordenador;
import es.santander.ascender.ejerc002.repositoryOrdenadorRepository;

@Service
public class OrdenadorService {

    @Autowired
    private OrdenadorRepository repository;

    public Ordenador create(Ordenador ordenador) {
        if (ordenador.getId() != null) {
            throw new CrudSecurityException("Han tratado de modificar un registro de ordenador utilizando la creación",
                                                 CRUDOperation.CREATE, 
                                                 ordenador.getId());
        }
        return repository.save(ordenador);
    }

    public Ordenador read(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Ordenador> read() {
        return repository.findAll();
    }

    public Ordenador update(Ordenador ordenador) {
        if (ordenador.getId() == null) {
            throw new CrudSecurityException("Han tratado de crear un registro ordenador utilizando la modifición",
                                                 CRUDOperation.UPDATE, 
                                                 null);
            
        }
        return repository.save(ordenador);
    }

    public void delete(Long id) {
        repository.deleteById(id);
        return;
    }
}
