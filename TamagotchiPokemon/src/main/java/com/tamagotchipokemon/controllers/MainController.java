package com.tamagotchipokemon.controllers;

import com.tamagotchipokemon.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {
    private final PokemonService pokemonService;

    @Autowired
    public MainController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }
}
