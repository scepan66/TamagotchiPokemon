package com.tamagotchipokemon.services;

import com.tamagotchipokemon.models.Attack;
import com.tamagotchipokemon.models.DTOs.BasicAttack;
import com.tamagotchipokemon.models.DTOs.BasicPokemon;
import com.tamagotchipokemon.models.DTOs.UserDTO;
import com.tamagotchipokemon.models.Pokemon;
import com.tamagotchipokemon.models.User;
import com.tamagotchipokemon.repositories.AttackRepository;
import com.tamagotchipokemon.repositories.PokemonRepository;
import com.tamagotchipokemon.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PokemonService {
    private final AttackRepository attackRepository;
    private final PokemonRepository pokemonRepository;
    private final UserRepository userRepository;

    private final Set<BasicPokemon> starters = new HashSet<>(Arrays.asList(
            new BasicPokemon("Bulbasaur", "bulbasaur.png", 40, 0, 1, 0,
                    new HashSet<>(Arrays.asList(new BasicAttack("Leech seed", "Plants a seed on the target", 20)))),
            new BasicPokemon("Charmander", "charmander.png", 50, 0, 2, 0,
                    new HashSet<>(Arrays.asList(new BasicAttack("Scratch", "Scratches with sharp claws", 10), new BasicAttack("Ember", "A weak fire attack that inflicts a burn.", 30)))),
            new BasicPokemon("Squirtle", "squirtle.png", 40, 0, 3, 0,
                    new HashSet<>(Arrays.asList(new BasicAttack("Bubble", "A spray of countless bubbles is jetted at the target, deals damage and slow down the enemy", 10)))),
            new BasicPokemon("Pikachu", "pikachu.png", 40, 0, 4, 0,
                    new HashSet<>(Arrays.asList(new BasicAttack("Gnaw", "Bites the enemy with sharp teeth", 10), new BasicAttack("Thunder jolt", "Shoots a small ball of electricity on the opponent", 30))))));

    private final List<BasicPokemon> evolves = new ArrayList<>(Arrays.asList(
            new BasicPokemon("Ivysaur", "ivysaur.png", 60, 0, 1, 0,
                    new HashSet<>(Arrays.asList(new BasicAttack("Vine whip", "Strikes the opponent with the slender, whiplikes vines", 30), new BasicAttack("Poison powder", "A cloud of toxic dust is scattered that poisons the opponent", 20)))),
            new BasicPokemon("Venusaur", "venusaur.png", 100, 0, 1, 0,
                    new HashSet<>(Arrays.asList(new BasicAttack("Solar beam", "Venusaur gathers the ligth of the sun and blasts a bundled beam on the opponent", 60)))),
            new BasicPokemon("Charmeleon", "charmeleon.png", 80, 0, 2, 0,
                    new HashSet<>(Arrays.asList(new BasicAttack("Slash", "Strikes the opponent with the sharp claws", 30), new BasicAttack("Flamethrower", "The target is scorched with an intense blast of fire.", 50)))),
            new BasicPokemon("Charizard", "charizard.png", 120, 0, 2, 0,
                    new HashSet<>(Arrays.asList(new BasicAttack("Fire spin", "The opponent is trapped in an intense spiral of fire that rages", 100)))),
            new BasicPokemon("Wartortle", "wartortle.png", 70, 0, 3, 0,
                    new HashSet<>(Arrays.asList(new BasicAttack("Bite", "Wartortle bites opponent with vicious fangs, it deals damage and cause opponent to flinch", 40)))),
            new BasicPokemon("Blastoise", "blastoise.png", 100, 0, 3, 0,
                    new HashSet<>(Arrays.asList(new BasicAttack("Hydro pump", "Blastoise blasts huge volume of water launched under great pressure", 70)))),
            new BasicPokemon("Raichu", "raichu.png", 80, 0, 4, 0,
                    new HashSet<>(Arrays.asList(new BasicAttack("Agility", "Sharply increase speed and attacks enemy furiously", 20), new BasicAttack("Thunder", "Raichu sends wicked thunderbolt on the opponent which leaves them paralyzed.", 60))))));

    @Autowired
    public PokemonService(AttackRepository attackRepository, PokemonRepository pokemonRepository, UserRepository userRepository) {
        this.attackRepository = attackRepository;
        this.pokemonRepository = pokemonRepository;
        this.userRepository = userRepository;
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElse(null);
    }

    public void createNewUser(UserDTO userDTO) {
        User newUser = new User(userDTO.getUsername());
        userRepository.save(newUser);
        for (BasicPokemon basicPokemon : starters.stream().sorted(Comparator.comparing(BasicPokemon::getEvolveStage)).toList()) {
            Pokemon newPokemon = new Pokemon(basicPokemon.getName(), basicPokemon.getImage(), basicPokemon.getHealth(), basicPokemon.getExperience(), basicPokemon.getEvolveStage(), basicPokemon.getHunger(), newUser);
            pokemonRepository.save(newPokemon);
            for (BasicAttack basicAttack : basicPokemon.getAttacks()) {
                Attack attack = new Attack(basicAttack.getName(), basicAttack.getDamage(), newPokemon, basicAttack.getDescription());
                attackRepository.save(attack);
            }
        }
    }

    public Pokemon getPokemonById(Long id) {
        return pokemonRepository.getPokemonById(id);
    }

    public List<Pokemon> getPokemonsBySearchedParameter(String search, List<Pokemon> pokemons) {
        return pokemons.stream()
                .filter(pokemon ->
                        pokemon.getName().toLowerCase().contains(search) ||
                                pokemon.getAttacks().stream().anyMatch(attack -> attack.getDescription().toLowerCase().contains(search))
                )
                .toList();
    }

    public boolean trainAttack(Long id) {
        Attack attack = attackRepository.getAttackById(id);
        Pokemon pokemon = attack.getPokemon();
        if (pokemon.getHunger() > 7) {
            return false;
        }
        Random random = new Random();
        attack.setDamage(attack.getDamage() + random.nextInt(3) + 1);
        attackRepository.save(attack);
        pokemon.setHunger(pokemon.getHunger() + 1);
        pokemon.setExperience(pokemon.getExperience() + random.nextInt(5) + 1);
        pokemonRepository.save(pokemon);
        return true;
    }

    public boolean feedPokemon(Long id) {
        Pokemon pokemon = pokemonRepository.getPokemonById(id);
        if (pokemon.getHunger() == 0) {
            return false;
        }
        pokemon.setHunger(pokemon.getHunger() - 1);
        pokemonRepository.save(pokemon);
        return true;
    }

    public Pokemon getPokemonByAttackId(Long id) {
        return attackRepository.getAttackById(id).getPokemon();
    }

    public boolean checkIfPokemonCanBeEvolved(Long id) {
        Pokemon pokemonToBeEvolved = pokemonRepository.getPokemonById(id);
        List<BasicPokemon> evolveTree = evolves.stream().filter(p -> p.getEvolveStage() == pokemonToBeEvolved.getEvolveStage()).toList();
        if (pokemonToBeEvolved.getHealth() == evolveTree.get(evolveTree.size()-1).getHealth()) {
            return false;
        }
        return true;
    }

    public Pokemon evolvePokemon(Long id) {
        Pokemon pokemonToBeEvolved = pokemonRepository.getPokemonById(id);
        User user = pokemonToBeEvolved.getUser();
        List<BasicPokemon> evolveTree = evolves.stream().filter(p -> p.getEvolveStage() == pokemonToBeEvolved.getEvolveStage()).toList();
        BasicPokemon unsavedPokemon = evolveTree.stream().filter(p ->p.getHealth() > pokemonToBeEvolved.getHealth()).findFirst().get();
        Pokemon newPokemon = new Pokemon(unsavedPokemon.getName(), unsavedPokemon.getImage(), unsavedPokemon.getHealth(), unsavedPokemon.getExperience(), unsavedPokemon.getEvolveStage(), unsavedPokemon.getHunger(), user);
        pokemonRepository.save(newPokemon);
        for (BasicAttack basicAttack : unsavedPokemon.getAttacks()) {
            Attack attack = new Attack(basicAttack.getName(), basicAttack.getDamage(), newPokemon, basicAttack.getDescription());
            attackRepository.save(attack);
        }
        user.getCurrentPokemons().remove(pokemonToBeEvolved);
        userRepository.save(user);
        return newPokemon;
    }

}
