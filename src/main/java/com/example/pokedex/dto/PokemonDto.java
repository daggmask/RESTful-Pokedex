package com.example.pokedex.dto;

import com.example.pokedex.entities.Ability;
import com.example.pokedex.entities.Game;
import com.example.pokedex.entities.Specie;
import com.example.pokedex.entities.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <Description>
 *
 * @author Martin Hellström
 * @version 1.0
 * @since 10/19/2020
 */

public class PokemonDto {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Height")
    private int height;
    @JsonProperty("Weight")
    private int weight;
    @JsonProperty("Base Experience")
    private int baseExperience;
    @JsonProperty("Location Encounter")
    private String locationEncounter;
    @JsonProperty("Types")
    private List<Object> types;
    @JsonProperty("Abilities")
    private List<Object> abilities;
    @JsonProperty("Games Implementation")
    private List<Object> games;
    @JsonProperty("Species")
    private Object specie;

    public PokemonDto(String name, int height, int weight, int base_experience,
                      String location_area_encounters, List<Object> types, List<Object> abilities,
                      List<Object> game_indices, Object species) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.baseExperience = base_experience;
        this.locationEncounter = location_area_encounters;
        this.types = types;
        this.abilities = abilities;
        this.games = game_indices;
        this.specie = species;
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
