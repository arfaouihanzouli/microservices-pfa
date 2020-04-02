package com.pfa.microserviceoffers.proxies;


import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "microservice-cv",url="localhost:9200", decode404 = true)
@RibbonClient(name = "microservice-cv")
public interface CvsProxy {
}
