package com.pfa.microserviceoffers.services;

import com.pfa.microserviceoffers.models.Offre;
import com.pfa.microserviceoffers.models.enumuration.Niveau;
import com.pfa.microserviceoffers.models.enumuration.TypeOffre;
import com.pfa.microserviceoffers.repositories.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

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

    @Override
    public List<Offre> findByOrganismeNomOrganismeContainingIgnoreCase(String nomOrganisme) {
        return this.offreRepository.findByOrganismeNomOrganismeContainingIgnoreCase(nomOrganisme);
    }

    @Override
    public List<Offre> findByOrganismeSecteurContainingIgnoreCase(String secteur) {
        return this.offreRepository.findByOrganismeSecteurContainingIgnoreCase(secteur);
    }

    @Override
    public List<Offre> findAllByTitreContainingIgnoreCase(String titre) {
        return this.offreRepository.findAllByTitreContainingIgnoreCase(titre);
    }

    @Override
    public List<Offre> findAllByPosteContainingIgnoreCase(String poste) {
        return this.offreRepository.findAllByPosteContainingIgnoreCase(poste);
    }

    @Override
    public List<Offre> findAllByLieuContainingIgnoreCase(String lieu) {
        return this.offreRepository.findAllByLieuContainingIgnoreCase(lieu);
    }

    @Override
    public List<Offre> findAllByTypeOffre(TypeOffre typeOffre) {
        return this.offreRepository.findAllByTypeOffre(typeOffre);
    }

    @Override
    public List<Offre> findAllByNiveau(Niveau niveau) {
        return this.offreRepository.findAllByNiveauDemande(niveau);
    }

    @Override
    public List<Offre> findAllByDescriptionContainingIgnoreCase(String description) {
        return this.offreRepository.findAllByDescriptionContainingIgnoreCase(description);
    }

    @Override
    public List<Offre> findAllByDateOffre(String date) throws ParseException {
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
//        Date d=simpleDateFormat.parse(date);
        return this.offreRepository.findAllByDateOffre(LocalDate.parse(date));
    }

    @Override
    public List<Offre> findAllByDateFin(String date) throws ParseException{
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
//        Date d=simpleDateFormat.parse(date);
        return this.offreRepository.findAllByDateFin(LocalDate.parse(date));
    }

    @Override
    public List<Offre> findAllByDateOffreBetween(String date1, String date2) throws ParseException{
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
//        Date d1=simpleDateFormat.parse(date1);
//        Date d2=simpleDateFormat.parse(date2);
        return this.offreRepository.findAllByDateOffreBetween(LocalDate.parse(date1),LocalDate.parse(date2));
    }

    @Override
    public List<Offre> findAllOffresNotEnded(String dateFin) throws ParseException{
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
//        Date d=simpleDateFormat.parse(dateFin);
        return this.offreRepository.findAllOffresNotEnded(LocalDate.parse(dateFin));
    }
}
