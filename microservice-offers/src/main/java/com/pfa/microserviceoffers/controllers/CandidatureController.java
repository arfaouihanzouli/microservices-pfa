package com.pfa.microserviceoffers.controllers;


import com.pfa.microserviceoffers.exceptions.ResourceExistsException;
import com.pfa.microserviceoffers.exceptions.ResourceNotFoundException;
import com.pfa.microserviceoffers.models.Candidature;
import com.pfa.microserviceoffers.proxies.UsersProxy;
import com.pfa.microserviceoffers.proxies.beans.UserBean;
import com.pfa.microserviceoffers.repositories.CandidatureRepository;
import com.pfa.microserviceoffers.services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/candidatures")
public class CandidatureController {

    @Autowired
    private CandidatureRepository candidatureRepository;

    @Autowired
    private UsersProxy usersProxy;

    @Autowired
    private OffreService offreService;

    @PostMapping("/add/candidat/{idCandidat}/offre/{idOffre}/cv/{idCv}")
    public Candidature create(@Valid @RequestBody Candidature candidature,
                              @PathVariable("idCandidat") Long idCandidat,
                              @PathVariable("idOffre") Long idOffre,
                              @PathVariable("idCv") Long idCv)
    {
        UserBean candidat=this.usersProxy.findCandidatById(idCandidat);
        if(candidat==null)
        {
            throw new ResourceNotFoundException("Ce candidat : "+idCandidat+" n'existe pas, tu ne peux pas ajouter cette candidature");
        }
        if(!offreService.existsById(idOffre))
        {
            throw new ResourceNotFoundException("Cette offre : "+idOffre+" n'existe pas, tu ne peux pas ajouter cette candidature");
        }
        //Il manque le test avec sur le idCv
        Candidature candidature1=this.candidatureRepository.findByIdCandidatAndOffreId(idCandidat,idOffre);
        if(candidature1!=null)
        {
            throw new ResourceExistsException("Tu a déjà postulé sur cette offre : "+idOffre);
        }
        candidature.setIdCandidat(idCandidat);
        candidature.setIdCv(idCv);
        candidature.setOffre(this.offreService.findById(idOffre).get());
        return this.candidatureRepository.save(candidature);
    }

    @GetMapping("/getAll")
    public List<Candidature> getAll()
    {
        return this.candidatureRepository.findAll();
    }
}
