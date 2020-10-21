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
import java.util.stream.Collectors;

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

    @Autowired
    private PokemonConsumerService pokemonConsumerService;

    private final RestTemplate restTemplate;

    private final static String POKEMON_URL = "https://pokeapi.co/api/v2/pokemon/";

    public PokemonService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    //Read
    public List<Pokemon> findPokemonByName(String name, String type){
        var pokemons = pokemonRepository.findAll();
        pokemons = pokemons.stream().filter(pokemon -> pokemon.getName().contains(name)).collect(Collectors.toList());
        if(pokemons.isEmpty()){
            var pokemonDto = pokemonConsumerService.findPokemonByName(name);
            if(pokemonDto != null){
                System.out.println(pokemonDto.getName());
                var pokemon = new Pokemon(pokemonDto.getName(),pokemonDto.getHeight(),
                        pokemonDto.getWeight(),pokemonDto.getBaseExperience(),pokemonDto.getLocationEncounter(),
                        pokemonDto.getTypes(),pokemonDto.getAbilities(),pokemonDto.getGames(),pokemonDto.getSpecie());
                this.savePokemon(pokemon);
                pokemons.add(pokemon);
            }
        }
        System.out.println(pokemons.size());
        return pokemons;
    }

    //Read
    public Pokemon findById(String id){
        return pokemonRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No pokemon found by id: %s", id)));
    }

    //Create
    public Pokemon savePokemon(Pokemon pokemon){
        return pokemonRepository.save(pokemon);
    }

    //Update
    public void updatePokemon(String id, Pokemon pokemon) {
        if (!pokemonRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No pokemon found by id: %s", id));
        }
        pokemon.setPokemonID(id);
        pokemonRepository.save(pokemon);
    }

    //Delete
    public void deletePokemon(String id){
        if (!pokemonRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No pokemon found by id: %s", id));
        }
        pokemonRepository.deleteById(id);
    }
}
