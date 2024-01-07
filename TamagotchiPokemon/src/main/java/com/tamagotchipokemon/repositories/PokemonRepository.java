package com.tamagotchipokemon.repositories;

import com.tamagotchipokemon.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    Pokemon getPokemonById(Long id);
}
