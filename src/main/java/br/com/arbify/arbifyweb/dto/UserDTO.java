package br.com.arbify.arbifyweb.dto;

import java.util.UUID;

import jakarta.annotation.Nonnull;

public class UserDTO {

    private UUID id;
    @Nonnull
    private String name;
    @Nonnull
    private String username;
    @Nonnull
    private String email;
    @Nonnull
    private String password;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
