package com.tamagotchipokemon.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @OneToMany(mappedBy = "user")
    private Set<Pokemon> currentPokemons;

    public User(String username) {
        this.username = username;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Pokemon> getCurrentPokemons() {
        return currentPokemons;
    }

    public void setCurrentPokemons(Set<Pokemon> currentPokemons) {
        this.currentPokemons = currentPokemons;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
