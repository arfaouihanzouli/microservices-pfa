package com.pfa.microserviceoffers.proxies;


import com.pfa.microserviceoffers.proxies.beans.CvBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "zuul-server", decode404 = true)
@RibbonClient(name = "microservice-cv")
public interface CvsProxy {

    @GetMapping("/getOne/{id}")
    public CvBean getCv(@PathVariable(value = "id") Long id);
}
