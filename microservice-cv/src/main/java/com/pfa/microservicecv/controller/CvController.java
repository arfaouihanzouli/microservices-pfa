package com.pfa.microservicecv.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfa.microservicecv.beans.CandidatBean;
import com.pfa.microservicecv.exceptions.ResourceNotFoundException;
import com.pfa.microservicecv.models.Cv;
import com.pfa.microservicecv.models.Fichier;
import com.pfa.microservicecv.proxy.CandidatProxy;
import com.pfa.microservicecv.service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Base64;

@RestController
@RequestMapping("/cvs")
public class CvController {

    @Autowired
    private CvService cvService;

    @Autowired
    private CandidatProxy candidatProxy;


    RestTemplate restTemplate=new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity;

    @PostMapping(value = "/add/{idCandidat}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Cv create(@PathVariable(value = "idCandidat") Long id,
                     @RequestParam("cv") String cv,
                     @RequestParam("file") MultipartFile file) throws IOException
    {
        CandidatBean candidatBean=this.candidatProxy.findCandidatById(id);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String type=file.getContentType();
        byte[] fileContent = new byte[0];
        try {
            fileContent = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        Fichier fichier=new Fichier(fileName,type,encodedString);
        ObjectMapper mapper = new ObjectMapper();
        Cv cvObject = mapper.readValue(cv, Cv.class);
        cvObject.setFichier(fichier);
        cvObject.setIdCandidat(id);
        //parser
        headers.setAccept(Arrays.asList(MediaType.MULTIPART_FORM_DATA,MediaType.APPLICATION_PDF,MediaType.IMAGE_JPEG,MediaType.IMAGE_PNG));
        headers.set("apikey","dab475022388957");
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        //body.add("filename", fileName);
        OutputStream out = new FileOutputStream(fileName);
        out.write(fileContent);
        out.close();
        body.add("file",out);
        //body.add("apikey","dab475022388957");
        body.add("isOverlayRequired",false);
        body.add("language","fre");

        entity= new HttpEntity<String>(body.toString(),headers);
        ResponseEntity responseEntity=restTemplate.exchange("https://api.ocr.space/parse/image", HttpMethod.POST, entity, String.class);
        String parse=responseEntity.getBody().toString();
        int status=responseEntity.getStatusCodeValue();
        if(status==200)
        {
            System.out.println(parse);
            cvObject.setCvParser(parse);
            return this.cvService.create(cvObject);
        }
        else
        {
            throw new ResourceNotFoundException("Le parsing de fichier est échoué");
        }
    }
}
