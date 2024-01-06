package com.tamagotchipokemon.models;

import com.tamagotchipokemon.models.Attack;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Pokemon {
    @Id
    private Long id;
    private String name;
    private String image;
    private String health;
    private int experience;
    private int evolveStage;
    private int hunger;
    @OneToMany(mappedBy = "pokemon")
    private Set<Attack> attacks;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Pokemon() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public Set<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(Set<Attack> attacks) {
        this.attacks = attacks;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
