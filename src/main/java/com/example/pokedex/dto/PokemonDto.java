package com.example.pokedex.dto;

import com.example.pokedex.entities.Ability;
import com.example.pokedex.entities.Game;
import com.example.pokedex.entities.Specie;
import com.example.pokedex.entities.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <Description>
 *
 * @author Martin Hellström
 * @version 1.0
 * @since 10/19/2020
 */

public class PokemonDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("height")
    private int height;
    @JsonProperty("weight")
    private int weight;
    @JsonProperty("base_experience")
    private int baseExperience;
    @JsonProperty("location_area_encounter")
    private String locationEncounter;
    @JsonProperty("types")
    private List<Type> types;
    @JsonProperty("abilities")
    private List<Ability> abilities;
    @JsonProperty("game_indices")
    private List<Game> games;
    @JsonProperty("species")
    private Specie specie;

    public PokemonDto(String name, int height, int weight, int baseExperience, String locationEncounter, List<Type> types, List<Ability> abilities, List<Game> games, Specie specie) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.baseExperience = baseExperience;
        this.locationEncounter = locationEncounter;
        this.types = types;
        this.abilities = abilities;
        this.games = games;
        this.specie = specie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public String getLocationEncounter() {
        return locationEncounter;
    }

    public void setLocationEncounter(String locationEncounter) {
        this.locationEncounter = locationEncounter;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }
}
