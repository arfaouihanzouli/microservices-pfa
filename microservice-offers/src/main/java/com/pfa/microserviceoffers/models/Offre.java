package com.pfa.microserviceoffers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pfa.microserviceoffers.models.audit.AbstractEntity;
import com.pfa.microserviceoffers.models.enumuration.Niveau;
import com.pfa.microserviceoffers.models.enumuration.TypeOffre;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Offre extends AbstractEntity {

    @Column
    private String titre;

    //@JsonIgnore
    @Column
    private Date dateOffre;

    @Column
    private Date dateFin;

    @Column
    private String poste;

    @Column
    private String lieu;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeOffre typeOffre;

    @Column
    private String description;

    @Column
    private int anneeExperience;

    @Column
    @Enumerated(EnumType.STRING)
    private Niveau niveauDemande;

    @Column
    private boolean etat;

    @Column
    private Long idManager;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organisme_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private Organisme organisme;

    @OneToMany(cascade = CascadeType.ALL,
               fetch = FetchType.LAZY,
                mappedBy = "offre")
    private Set<Candidature> candidatures=new HashSet<>();

    public Offre() {
        super();
    }

    public Offre(String titre, Date dateOffre, Date dateFin, String poste, String lieu, TypeOffre typeOffre, String description, int anneeExperience, Niveau niveauDemande, boolean etat, Long idManager, Organisme organisme) {
        super();
        this.titre = titre;
        this.dateOffre = dateOffre;
        this.dateFin = dateFin;
        this.poste = poste;
        this.lieu = lieu;
        this.typeOffre = typeOffre;
        this.description = description;
        this.anneeExperience = anneeExperience;
        this.niveauDemande = niveauDemande;
        this.etat = etat;
        this.idManager = idManager;
        this.organisme=organisme;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDateOffre() {
        return dateOffre;
    }

    public void setDateOffre(Date dateOffre) {
        this.dateOffre = dateOffre;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public TypeOffre getTypeOffre() {
        return typeOffre;
    }

    public void setTypeOffre(TypeOffre typeOffre) {
        this.typeOffre = typeOffre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAnneeExperience() {
        return anneeExperience;
    }

    public void setAnneeExperience(int anneeExperience) {
        this.anneeExperience = anneeExperience;
    }

    public Niveau getNiveauDemande() {
        return niveauDemande;
    }

    public void setNiveauDemande(Niveau niveauDemande) {
        this.niveauDemande = niveauDemande;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public Long getIdManager() {
        return idManager;
    }

    public void setIdManager(Long idManager) {
        this.idManager = idManager;
    }

    public Organisme getOrganisme() {
        return organisme;
    }

    public void setOrganisme(Organisme organisme) {
        this.organisme = organisme;
    }

    public Set<Candidature> getCandidatures() {
        return candidatures;
    }

    public void setCandidatures(Set<Candidature> candidatures) {
        this.candidatures = candidatures;
    }

    @Override
    public String toString() {
        return "Offre{" +
                "titre='" + titre + '\'' +
                ", dateOffre=" + dateOffre +
                ", dateFin=" + dateFin +
                ", poste='" + poste + '\'' +
                ", lieu='" + lieu + '\'' +
                ", typeOffre=" + typeOffre +
                ", description='" + description + '\'' +
                ", anneeExperience=" + anneeExperience +
                ", niveauDemande=" + niveauDemande +
                ", etat=" + etat +
                ", idManager=" + idManager +
                ", organisme=" + organisme +
                '}';
    }
}
