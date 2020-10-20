package com.example.pokedex.services;

import com.example.pokedex.dto.PokemonDto;
import com.example.pokedex.entities.Pokemon;
import com.example.pokedex.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * <CRUD operations, service for pokemon entities>
 *
 * @author Martin Hellström
 * @version 1.0
 * @since 10/19/2020
 */

@Service
public class PokemonService {
    @Autowired
    private PokemonRepository pokemonRepository;

    private final RestTemplate restTemplate;

    private final static String POKEMON_URL = "https://pokeapi.co/api/v2/pokemon/";

    public PokemonService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    //Read
    public PokemonDto findPokemonByName(String name){
        if(name == null){
            return (PokemonDto) findAll();
        }

        var pokemonInDB = pokemonRepository.findByName(name).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No pokemon found by name: %s", name)));
        if(pokemonInDB.size() != 0){
            return (PokemonDto) pokemonInDB;
        }

        var pokemonURLQuery = POKEMON_URL + name;
        System.out.println(pokemonURLQuery);
        var pokemons = restTemplate.getForObject(pokemonURLQuery, PokemonDto.class);

        if(pokemons != null){
            System.out.println(pokemons.getName());
        }
        else{
            System.out.println("Pokemon not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found");
        }
        return pokemons;
    }

    //Read
    public List<Pokemon> findAll(){
        return pokemonRepository.findAll();
    }

    //Create
    public ResponseEntity<String> savePokemon(Pokemon pokemon){
        if(pokemon == null){
            return ResponseEntity.status(406).body("Body required");
        }
        pokemonRepository.save(pokemon);
        return ResponseEntity.ok("Save successful");
    }

    //Update
    public ResponseEntity<String> updatePokemon(String id, Pokemon pokemon) {
        if (!pokemonRepository.existsById(id)) {
            return ResponseEntity.status(400).body(String.format("No pokemon found by id: %s", id));
        }
        pokemon.setPokemonID(id);
        pokemonRepository.save(pokemon);
        return ResponseEntity.ok(String.format("Update successful for pokemon: %s", pokemon.getName()));
    }

    //Delete
    public ResponseEntity<String> deletePokemon(String id){
        if (!pokemonRepository.existsById(id)) {
            return ResponseEntity.status(400).body(String.format("No pokemon found by id: %s", id));
        }
        var pokemon = pokemonRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found"));
        pokemonRepository.deleteById(id);
        return ResponseEntity.ok(String.format("Update successful for pokemon: %s", pokemon.getName()));
    }
}
