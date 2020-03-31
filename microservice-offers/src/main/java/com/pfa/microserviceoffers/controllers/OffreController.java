package com.pfa.microserviceoffers.controllers;

import com.pfa.microserviceoffers.exceptions.ResourceNotFoundException;
import com.pfa.microserviceoffers.models.Offre;
import com.pfa.microserviceoffers.proxies.UsersProxy;
import com.pfa.microserviceoffers.proxies.beans.UserBean;
import com.pfa.microserviceoffers.repositories.OrganismeRepository;
import com.pfa.microserviceoffers.responses.ApiResponse;
import com.pfa.microserviceoffers.services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/offres")
public class OffreController {

    @Autowired
    private OffreService offreService;

    @Autowired
    private OrganismeRepository organismeRepository;

    @Autowired
    private UsersProxy usersProxy;

    @PostMapping("/add/{idManager}/organisme/{nomOrganisme}")
    public ResponseEntity<?> create(@PathVariable(value = "idManager") Long idManager,
                                    @PathVariable(value = "nomOrganisme") String nomOrganisme,
                                    @Valid @RequestBody Offre offre)
    {
        UserBean userBean=this.usersProxy.findById(idManager);
        if(userBean==null)
        {
            throw new ResourceNotFoundException("L'utilisateur avec id :"+idManager+" n'existe pas, tu ne peux pas ajouter cette offre!!");
        }
        offre.setIdManager(idManager);
        organismeRepository.findByNomOrganismeIgnoreCase(nomOrganisme).map(organisme->{
            System.out.println(organisme);
            offre.setOrganisme(organisme);
            this.offreService.save(offre);
            return organisme;
        }).orElseThrow(()-> new ResourceNotFoundException("Organisme : "+nomOrganisme+" n'existe pas, tu ne peux pas ajouter cette offre!!"));
        return new ResponseEntity<Object>(new ApiResponse("Votre offre a été ajoutée avec succès.",true),HttpStatus.CREATED);
    }
    @GetMapping("/getOffreById/{idOffre}")
    public Offre getOffreByID(@PathVariable(value = "idOffre") Long id)
    {
        Offre o=this.offreService.findById(id).get();
        if(o==null)
        {
            throw new ResourceNotFoundException("Cette offre n'existe pas!");
        }
        return o;
    }
    @GetMapping("/getAll")
    public List<Offre> getAllOffres()
    {
        return this.offreService.findAll();
    }
    @GetMapping("/getAllPaginate")
    public Page<Offre> getAllOffresPaginated(Pageable pageable)
    {
        return this.offreService.paginateListOffres(pageable);
    }
    @GetMapping("/getAll/{idManager}")
    public List<Offre> getAllOffresByManager(@PathVariable(value = "idManager") Long id)
    {
        return this.offreService.findByIdManager(id);
    }
    @PutMapping("/update/{idOffre}")
    public Offre updateOffre(@PathVariable(value = "idOffre") Long idOffre,@Valid @RequestBody Offre offre)
    {
        return this.offreService.findById(idOffre).map(o->{
            o.setAnneeExperience(offre.getAnneeExperience());
            o.setDateFin(offre.getDateFin());
            o.setDateOffre(offre.getDateOffre());
            o.setDescription(offre.getDescription());
            o.setEtat(offre.isEtat());
            o.setLieu(offre.getLieu());
            o.setPoste(offre.getPoste());
            o.setNiveauDemande(offre.getNiveauDemande());
            o.setTitre(offre.getTitre());
            return offreService.update(o);
        }).orElseThrow(()->new ResourceNotFoundException("Cette offre "+idOffre+" n'existe pas !"));
    }

    @DeleteMapping("/delete/{idOffre}")
    public ResponseEntity<?> deleteOffre(@PathVariable(value = "idOffre") Long id)
    {
        return this.offreService.findById(id).map(offre->{
            this.offreService.delete(offre);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("Cette offre "+id+" n'existe pas !"));
    }

}
