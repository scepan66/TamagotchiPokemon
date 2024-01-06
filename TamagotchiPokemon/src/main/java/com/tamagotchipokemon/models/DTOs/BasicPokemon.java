package com.tamagotchipokemon.models.DTOs;

import com.tamagotchipokemon.models.Attack;

import java.util.Set;

public class BasicPokemon {
    private String name;
    private String image;
    private int health;
    private int experience;
    private int evolveStage;
    private int hunger;
    private Set<BasicAttack> attacks;

    public BasicPokemon(String name, String image, int health, int experience, int evolveStage, int hunger, Set<BasicAttack> attacks) {
        this.name = name;
        this.image = image;
        this.health = health;
        this.experience = experience;
        this.evolveStage = evolveStage;
        this.hunger = hunger;
        this.attacks = attacks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getEvolveStage() {
        return evolveStage;
    }

    public void setEvolveStage(int evolveStage) {
        this.evolveStage = evolveStage;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public Set<BasicAttack> getAttacks() {
        return attacks;
    }

    public void setAttacks(Set<BasicAttack> attacks) {
        this.attacks = attacks;
    }
}
