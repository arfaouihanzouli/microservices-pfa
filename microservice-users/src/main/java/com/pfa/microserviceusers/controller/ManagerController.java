package com.pfa.microserviceusers.controller;

import com.pfa.microserviceusers.exceptions.ResourceNotFoundException;
import com.pfa.microserviceusers.models.Manager;
import com.pfa.microserviceusers.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/managers")
public class ManagerController {

    @Autowired
    private ManagerRepository managerRepository;

    @PutMapping("/update/{id}")
    public Manager updateManager(@RequestBody Manager manager, @PathVariable Long id)
    {
        return managerRepository.findById(id).map(m -> {
            m.setLastName(manager.getLastName());
            m.setName(manager.getName());
            m.setTelephone(manager.getTelephone());
            m.setUsername(manager.getUsername());
            m.setLoacked(manager.isLoacked());
            m.setNameEntreprise(manager.getNameEntreprise());
            return managerRepository.save(m);
        }).orElseThrow(()-> new ResourceNotFoundException("Ce manager n'existe pas!!!"));
    }
}
