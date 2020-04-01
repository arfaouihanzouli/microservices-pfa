package com.pfa.microservicecv.controller;

import com.pfa.microservicecv.service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cvs")
public class CvController {

    @Autowired
    private CvService cvService;


}
