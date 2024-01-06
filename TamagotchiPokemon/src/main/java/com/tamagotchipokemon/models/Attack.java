package com.tamagotchipokemon.models;

import jakarta.persistence.*;

@Entity
public class Attack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private int damage;
    @ManyToOne
    @JoinColumn(name = "pokemon_id")
    private Pokemon pokemon;

    public Attack(Long id, String name, int damage, Pokemon pokemon, String description) {
        this.id = id;
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

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
