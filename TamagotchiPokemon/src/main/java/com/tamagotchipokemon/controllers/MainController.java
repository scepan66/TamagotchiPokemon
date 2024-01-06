package com.tamagotchipokemon.controllers;

import com.tamagotchipokemon.models.DTOs.UserDTO;
import com.tamagotchipokemon.models.Pokemon;
import com.tamagotchipokemon.models.User;
import com.tamagotchipokemon.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        if (loggedInUser == null) {
            return "redirect:/";
        }
        User user = pokemonService.findUserByUsername(loggedInUser.getUsername());
        model.addAttribute("pokemonList", user.getCurrentPokemons().stream().sorted(Comparator.comparing(Pokemon::getId)).toList());
        model.addAttribute("user", user);
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
    @GetMapping("{id}/info")
    public String info(@PathVariable Long id, Model model) {
        model.addAttribute("pokemonList", pokemonService.findUserByUsername(loggedInUser.getUsername()).getCurrentPokemons().stream().sorted(Comparator.comparing(Pokemon::getId)).toList());
        model.addAttribute("pokemon", pokemonService.getPokemonById(id));
        return "info";
    }
}
