package com.pfa.microserviceoffers.controllers;


import com.pfa.microserviceoffers.models.Candidature;
import com.pfa.microserviceoffers.proxies.UsersProxy;
import com.pfa.microserviceoffers.repositories.CandidatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/candidatures")
public class CandidatureController {

    @Autowired
    private CandidatureRepository candidatureRepository;

    @Autowired
    private UsersProxy usersProxy;

//    @PostMapping("/add/candidat/{idCandidat}/offre/{idOffre}/cv/{idCv}")
//    public Candidature create(@Valid @RequestBody Candidature candidature,
//                              @PathVariable("idCandidat") Long idCandidat,
//                              @PathVariable("idOffre") Long idOffre,
//                              @PathVariable("idCv") Long idCv)
//    {
//
//    }
}
