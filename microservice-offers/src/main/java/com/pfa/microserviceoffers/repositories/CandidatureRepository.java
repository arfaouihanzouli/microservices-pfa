package com.pfa.microserviceoffers.repositories;

import com.pfa.microserviceoffers.models.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {

    Candidature findByIdCandidatAndOffreId(Long idCandidat, Long idOffre);
    List<Candidature> findByIdCandidat(Long idCandidat);
    List<Candidature> findByOffreId(Long idOffre);
    List<Candidature> findByIdCv(Long idCandidat);
    List<Candidature> findByEtat(boolean etat);
    List<Candidature> findByDateCandidature(Instant date);
}
