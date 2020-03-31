package com.pfa.microserviceoffers.repositories;


import com.pfa.microserviceoffers.models.Offre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OffreRepository extends JpaRepository<Offre,Long> {

    Optional<Offre> findById(Long id);
    Page<Offre> findByOrganismeId(Long organismeId, Pageable pageable);
    Optional<Offre> findByIdAndOrganismeId(Long id, Long organismeId);
    List<Offre> findByIdManager(Long id);
}
