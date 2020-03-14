package com.pfa.microserviceusers.models.token;

import com.pfa.microserviceusers.models.User;
import com.pfa.microserviceusers.models.audit.AbstractEntity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class ConfirmationToken extends AbstractEntity {

    @Column(name="confirmation_token")
    private String confirmationToken;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public ConfirmationToken() {
    }

    public ConfirmationToken(User user) {
        this.user = user;
        confirmationToken = UUID.randomUUID().toString();
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}