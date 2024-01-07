package com.tamagotchipokemon.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "pokemons")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private int health;
    private int experience;
    private int evolveStage;
    private int hunger;
    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Attack> attacks;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Pokemon(String name, String image, int health, int experience, int evolveStage, int hunger, User user) {
        this.name = name;
        this.image = image;
        this.health = health;
        this.experience = experience;
        this.evolveStage = evolveStage;
        this.hunger = hunger;
        this.user = user;
    }

    public Pokemon(String name, String image, int health, int experience, int evolveStage, int hunger, Set<Attack> attacks, User user) {
        this.name = name;
        this.image = image;
        this.health = health;
        this.experience = experience;
        this.evolveStage = evolveStage;
        this.hunger = hunger;
        this.attacks = attacks;
        this.user = user;
    }

    public Pokemon() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(Set<Attack> attacks) {
        this.attacks = attacks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
