package com.pfa.microserviceoffers.services;

import com.pfa.microserviceoffers.models.Offre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OffreService  {

    Optional<Offre> findById(Long id);
    Page<Offre> findByOrganismeId(Long organismeId, Pageable pageable);
    Optional<Offre> findByIdAndOrganismeId(Long id, Long organismeId);
    Offre save(Offre offre);
    Offre update(Offre offre);
    void delete(Offre offre);
    boolean existsById(Long id);
    List<Offre> findAll();
    Page<Offre> paginateListOffres(Pageable pageable);
    List<Offre> findByIdManager(Long id);
}
