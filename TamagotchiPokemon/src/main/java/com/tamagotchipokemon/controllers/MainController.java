package com.tamagotchipokemon.controllers;

import com.tamagotchipokemon.models.DTOs.UserDTO;
import com.tamagotchipokemon.models.Pokemon;
import com.tamagotchipokemon.models.User;
import com.tamagotchipokemon.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.List;

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

    @PostMapping("/search")
    public String search(Model model, @RequestParam String search, RedirectAttributes redirectAttributes) {
        List<Pokemon> foundedPokemons = pokemonService.getPokemonsBySearchedParameter(search, pokemonService.findUserByUsername(loggedInUser.getUsername()).getCurrentPokemons().stream().sorted(Comparator.comparing(Pokemon::getId)).toList());
        if (foundedPokemons.isEmpty()) {
            redirectAttributes.addFlashAttribute("notFounded", true);
            return "redirect:/pokedex";
        }
        model.addAttribute(foundedPokemons);
        model.addAttribute("user", pokemonService.findUserByUsername(loggedInUser.getUsername()));
        return "pokedex";
    }

    @GetMapping("/{id}/train")
    public String train(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (pokemonService.trainAttack(id)) {
            redirectAttributes.addFlashAttribute("trained", true);
        } else {
            redirectAttributes.addFlashAttribute("trained", false);
        }
        return "redirect:/" + pokemonService.getPokemonByAttackId(id).getId() + "/info";
    }

    @GetMapping("/{id}/feed")
    public String feed(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (pokemonService.feedPokemon(id)) {
            redirectAttributes.addFlashAttribute("fed", true);
        } else {
            redirectAttributes.addFlashAttribute("fed", false);
        }
        return "redirect:/" + pokemonService.getPokemonById(id).getId() + "/info";
    }

    @GetMapping("/{id}/evolve")
    public String evolve(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
        if (!pokemonService.checkIfPokemonCanBeEvolved(id)) {
            model.addAttribute("pokemon", pokemonService.getPokemonById(id));
            return "delete";
        }
        Pokemon newPokemon = pokemonService.evolvePokemon(id);
        redirectAttributes.addFlashAttribute("justEvolved", true);
        return "redirect:/" + newPokemon.getId() + "/info";
    }
    
}
