package com.example.pokedex.entities;

import java.util.List;

/**
 * <Description>
 *
 * @author Martin Hellström
 * @version 1.0
 * @since 10/21/2020
 */
public class PokemonList {
    private List<BasicInfo> results;

    public PokemonList() {
    }

    public PokemonList(List<BasicInfo> results) {
        this.results = results;
    }

    public List<BasicInfo> getResults() {
        return results;
    }

    public void setResults(List<BasicInfo> results) {
        this.results = results;
    }
}
