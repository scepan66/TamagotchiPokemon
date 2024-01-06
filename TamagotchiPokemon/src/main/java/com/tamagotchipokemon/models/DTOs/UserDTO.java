package com.tamagotchipokemon.models.DTOs;

import com.tamagotchipokemon.models.Pokemon;

import java.util.Set;

public class UserDTO {
    private Long id;
    private String username;
    private Set<Pokemon> currentPokemons;

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Pokemon> getCurrentPokemons() {
        return currentPokemons;
    }

    public void setCurrentPokemons(Set<Pokemon> currentPokemons) {
        this.currentPokemons = currentPokemons;
    }
}
