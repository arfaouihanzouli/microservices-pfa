package com.pfa.microserviceoffers.services;

import com.pfa.microserviceoffers.models.Offre;
import com.pfa.microserviceoffers.repositories.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OffreServiceImp implements OffreService{

    @Autowired
    private OffreRepository offreRepository;

    @Override
    public Optional<Offre> findById(Long id) {
        return this.offreRepository.findById(id);
    }

    @Override
    public Page<Offre> findByOrganismeId(Long organismeId, Pageable pageable) {
        return this.offreRepository.findByOrganismeId(organismeId,pageable);
    }

    @Override
    public Optional<Offre> findByIdAndOrganismeId(Long id, Long organismeId) {
        return this.offreRepository.findByIdAndOrganismeId(id,organismeId);
    }

    @Override
    public Offre save(Offre offre) {
        return this.offreRepository.save(offre);
    }

    @Override
    public Offre update(Offre offre) {
        return this.offreRepository.save(offre);
    }

    @Override
    public void delete(Offre offre) {
        this.offreRepository.delete(offre);
    }

    @Override
    public boolean existsById(Long id) {
        return this.offreRepository.existsById(id);
    }

    @Override
    public List<Offre> findAll() {
        return this.offreRepository.findAll();
    }

    @Override
    public Page<Offre> paginateListOffres(Pageable pageable) {
        return this.offreRepository.findAll(pageable);
    }

    @Override
    public List<Offre> findByIdManager(Long id) {
        return this.offreRepository.findByIdManager(id);
    }
}
