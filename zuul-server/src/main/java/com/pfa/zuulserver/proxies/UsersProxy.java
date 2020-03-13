package com.pfa.zuulserver.proxies;

import com.pfa.zuulserver.beans.UserBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "zuul")
@RibbonClient(name = "microservice-users")
public interface UsersProxy {

    @GetMapping("/microservice-users/users/findByUsername/{username}")
    UserBean findByUsername(@PathVariable String username);

}