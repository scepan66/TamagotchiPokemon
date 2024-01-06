package com.tamagotchipokemon.models;

import jakarta.persistence.*;

@Entity
public class Attack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int damage;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pokemon_id")
    private Pokemon pokemon;

    public Attack(String name, int damage, Pokemon pokemon, String description) {
        this.name = name;
        this.damage = damage;
        this.description = description;
        this.pokemon = pokemon;
    }


    public Attack() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pokemon getPokemonFromUser() {
        return pokemon;
    }
    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
