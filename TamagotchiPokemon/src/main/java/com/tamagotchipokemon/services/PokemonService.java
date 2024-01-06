package com.tamagotchipokemon.services;

import com.tamagotchipokemon.repositories.AttackRepository;
import com.tamagotchipokemon.repositories.PokemonRepository;
import com.tamagotchipokemon.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {
    private final AttackRepository attackRepository;
    private final PokemonRepository pokemonRepository;
    private final UserRepository userRepository;

    @Autowired
    public PokemonService(AttackRepository attackRepository, PokemonRepository pokemonRepository, UserRepository userRepository) {
        this.attackRepository = attackRepository;
        this.pokemonRepository = pokemonRepository;
        this.userRepository = userRepository;
    }

}
