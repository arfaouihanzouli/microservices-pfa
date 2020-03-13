package com.pfa.microserviceusers.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String lastName;
    private Integer active=1;
    private boolean isLoacked=false;
    private boolean isExpired=false;
    private boolean isEnabled=true;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles=new ArrayList<>();

    public User( String username, String password, Collection<Role> roles, String email) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.email = email;
    }

    public User(String username, String password, String email, String name, String lastName, Integer active, boolean isLoacked, boolean isExpired, boolean isEnabled, Collection<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.active = active;
        this.isLoacked = isLoacked;
        this.isExpired = isExpired;
        this.isEnabled = isEnabled;
        this.roles = roles;
    }

    public User() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public boolean isLoacked() {
        return isLoacked;
    }

    public void setLoacked(boolean loacked) {
        isLoacked = loacked;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}