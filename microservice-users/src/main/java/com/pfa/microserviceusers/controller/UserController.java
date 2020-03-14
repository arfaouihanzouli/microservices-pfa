package com.pfa.microserviceusers.controller;

import com.pfa.microserviceusers.models.User;
import com.pfa.microserviceusers.models.enumuration.RoleName;
import com.pfa.microserviceusers.requests.*;
import com.pfa.microserviceusers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User signUp(@Valid @RequestBody RegistrationForm data)
    {
        String username=data.getUsername();
        String email=data.getEmail();
        User user=userService.findByUsernameOrEmail(username,email);
        if(user!=null)
        {
            throw new RuntimeException("This user already exists, Try with another user");
        }
        String password=data.getPassword();
        String repassword=data.getRepassword();
        if(!password.equals(repassword))
        {
            throw new RuntimeException("You must confirm your password");
        }
        User u=new User();
        u.setPassword(password);
        u.setUsername(username);
        u.setEmail(email);
        userService.saveUser(u);
        userService.addRoleToUser(username, RoleName.USER);
        return u;
    }
    @GetMapping("/findByUsername/{username}")
    public User findByUsername(@Valid @PathVariable String username)
    {
        User user=userService.findByUsername(username);
        if(user==null)
        {
            throw new RuntimeException("This user not exists, Try with another user");
        }
        return user;
    }
    @GetMapping("/findByUsernameOrEmail/{usernameOrEmail}")
    public User findByUsernameOrEmail(@Valid @PathVariable String usernameOrEmail)
    {
        User user=userService.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail);
        if(user==null)
        {
            throw new RuntimeException("This user not exists, Try with another user");
        }
        return user;
    }
    @GetMapping("/findById/{id}")
    public User findById(@Valid @PathVariable Long id)
    {
        Optional<User> user=userService.findById(id);
        if(user==null)
        {
            throw new RuntimeException("This user not exists, Try with another user");
        }
        return user.get();
    }
}
