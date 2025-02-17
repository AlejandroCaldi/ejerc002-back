package es.santander.ascender.ejerc002.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ordenador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="colorR")
    private Short colorR;

    @Column(name="colorG")
    private Short colorG;

    @Column(name="colorB")
    private Short colorB;

    @Column(name="peso")
    private Double peso;

    @Column(name="numeroTeclas")  
    private Short numeroTeclas;

    @Column(name="esIntel")  
    private Boolean esIntel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getColorR() {
        return colorR;
    }

    public void setColorR(Short colorR) {
        this.colorR = colorR;
    }

    public Short getColorG() {
        return colorG;
    }

    public void setColorG(Short colorG) {
        this.colorG = colorG;
    }

    public Short getColorB() {
        return colorB;
    }

    public void setColorB(Short colorB) {
        this.colorB = colorB;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Short getNumeroTeclas() {
        return numeroTeclas;
    }

    public void setNumeroTeclas(Short numeroTeclas) {
        this.numeroTeclas = numeroTeclas;
    }

    public Boolean getEsIntel() {
        return esIntel;
    }

    public void setEsIntel(Boolean esIntel) {
        this.esIntel = esIntel;
    }


    
    
}
