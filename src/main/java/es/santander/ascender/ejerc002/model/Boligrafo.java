package es.santander.ascender.ejerc002.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

@Entity
public class Boligrafo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="color")
    private int[] color;

    @Column(name="escribe")
    private Boolean escribe;

    @Column(name="nombre", unique = true)
    private String nombre;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int[] getColor() {
        return this.color;
    }

    public void setColor(int[] color) {
        this.color = color;
    }

    public Boolean getEscribe() {
        return this.escribe;
    }

    public void setEscribe(Boolean escribe) {
        this.escribe = escribe;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
}
