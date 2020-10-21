package com.example.pokedex.entities;

import com.example.pokedex.dto.PokemonDto;
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
public class Pokemon {
    @Id
    private String pokemonID;
    @NotNull
    private String name;
    private int height;
    private int weight;
    private int baseExperience;
    private String locationEncounter;
    private List<Object> types;
    private List<Object> abilities;
    private List<Object> games;
    private Object specie;

    public Pokemon() {
    }

    public Pokemon(@NotNull String name, int height, int weight, int baseExperience, String locationEncounter, List<Object> types, List<Object> abilities, List<Object> games, Object specie) {
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

    public String getPokemonID() {
        return pokemonID;
    }

    public void setPokemonID(String pokemonID) {
        this.pokemonID = pokemonID;
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

    public List<Object> getTypes() {
        return types;
    }

    public void setTypes(List<Object> types) {
        this.types = types;
    }

    public List<Object> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Object> abilities) {
        this.abilities = abilities;
    }

    public List<Object> getGames() {
        return games;
    }

    public void setGames(List<Object> games) {
        this.games = games;
    }

    public Object getSpecie() {
        return specie;
    }

    public void setSpecie(Object specie) {
        this.specie = specie;
    }
}
