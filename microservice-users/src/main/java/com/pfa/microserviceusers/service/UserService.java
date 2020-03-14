package com.pfa.microserviceusers.service;


import com.pfa.microserviceusers.models.*;
import com.pfa.microserviceusers.models.enumuration.RoleName;

import java.util.Optional;

public interface UserService {
    public User saveUser(User user);
    public User findByUsername(String username);
    public User findByUsernameOrEmail(String username,String email);
    public void addRoleToUser(String username, RoleName role);
    Optional<User> findById(Long id);
}