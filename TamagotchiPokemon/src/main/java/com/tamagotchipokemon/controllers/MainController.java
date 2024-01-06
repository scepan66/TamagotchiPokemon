package com.tamagotchipokemon.controllers;

import com.tamagotchipokemon.models.DTOs.UserDTO;
import com.tamagotchipokemon.models.User;
import com.tamagotchipokemon.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    private final PokemonService pokemonService;
    static User loggedInUser;

    @Autowired
    public MainController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/pokedex")
    public String pokedex(Model model) {
        return "pokedex";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute UserDTO userDTO) {
        User foundUser = pokemonService.findUserByUsername(userDTO.getUsername());
        if (foundUser == null) {
            pokemonService.createNewUser(userDTO);
            foundUser = pokemonService.findUserByUsername(userDTO.getUsername());
        }
        loggedInUser = foundUser;
        return "redirect:/pokedex";
    }
}
