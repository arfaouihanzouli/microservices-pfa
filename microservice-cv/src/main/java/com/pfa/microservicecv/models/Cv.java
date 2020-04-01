package com.pfa.microservicecv.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Document(indexName = "cvs", type = "cv")
public class Cv {

    @Id
    private Long id;

    private String cvParser;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Fichier fichier;

    private LocalDateTime dateDepot=LocalDateTime.now();

    private boolean etat;

    private Long idCandidat;

    public Cv() {
        this.etat=true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCvParser() {
        return cvParser;
    }

    public void setCvParser(String cvParser) {
        this.cvParser = cvParser;
    }

    public Fichier getFichier() {
        return fichier;
    }

    public void setFichier(Fichier fichier) {
        this.fichier = fichier;
    }

    public LocalDateTime getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(LocalDateTime dateDepot) {
        this.dateDepot = dateDepot;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public Long getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(Long idCandidat) {
        this.idCandidat = idCandidat;
    }
}
