package com.pfa.microserviceusers.service;


import com.pfa.microserviceusers.models.*;

import java.util.Optional;

public interface UserService {
    public User saveUser(User user);
    public Role saveRole(Role role);
    public User findByUsername(String username);
    public User findByUsernameOrEmail(String username,String email);
    public void addRoleToUser(String username, String role);
    Optional<User> findById(Long id);
}