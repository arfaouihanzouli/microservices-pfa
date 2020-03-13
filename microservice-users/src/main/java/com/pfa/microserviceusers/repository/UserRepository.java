package com.pfa.microserviceusers.repository;


import com.pfa.microserviceusers.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUsernameOrEmail(String username,String email);
    Optional<User> findById(Long id);
}