package com.pfa.microserviceoffers.repositories;


import com.pfa.microserviceoffers.models.Offre;
import com.pfa.microserviceoffers.models.enumuration.Niveau;
import com.pfa.microserviceoffers.models.enumuration.TypeOffre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OffreRepository extends JpaRepository<Offre,Long> {

    Optional<Offre> findById(Long id);
    Page<Offre> findByOrganismeId(Long organismeId, Pageable pageable);
    Optional<Offre> findByIdAndOrganismeId(Long id, Long organismeId);
    List<Offre> findByIdManager(Long id);
    List<Offre> findByOrganismeNomOrganismeContainingIgnoreCase(String nomOrganisme);
    List<Offre> findByOrganismeSecteurContainingIgnoreCase(String secteur);
    List<Offre> findAllByTitreContainingIgnoreCase(String titre);
    List<Offre> findAllByPosteContainingIgnoreCase(String poste);
    List<Offre> findAllByLieuContainingIgnoreCase(String lieu);
    List<Offre> findAllByTypeOffreContainingIgnoreCase(TypeOffre typeOffre);
    List<Offre> findAllByNiveauContainingIgnoreCase(Niveau niveau);
    List<Offre> findAllByDescriptionContainingIgnoreCase(String description);
    List<Offre> findAllByDateOffre(Date date);
    List<Offre> findAllByDateFin(Date date);
    List<Offre> findAllByDateOffreBetween(Date date1, Date date2);
    @Query("select o from Offre o where o.dateFin <= :dateFin")
    List<Offre> findAllOffresNotEnded(@Param("dateFin") Date dateFin);
}
