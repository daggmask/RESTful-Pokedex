package com.example.pokedex.services;

import com.example.pokedex.dto.PokemonDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

/**
 * <Description>
 *
 * @author Martin Hellström
 * @version 1.0
 * @since 10/21/2020
 */
@Service
public class PokemonConsumerService {

    private final RestTemplate restTemplate;

    private final static String POKEMON_URL = "https://pokeapi.co/api/v2/pokemon/";

    public PokemonConsumerService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public PokemonDto findPokemonByName(String name){
        var pokemonURLQuery = POKEMON_URL + name;
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
}
