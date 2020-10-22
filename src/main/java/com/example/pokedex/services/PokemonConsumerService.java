package com.example.pokedex.services;

import com.example.pokedex.dto.PokemonDto;
import com.example.pokedex.entities.PokemonList;
import com.example.pokedex.repositories.BasicInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * <Description>
 *
 * @author Martin Hellström
 * @version 1.0
 * @since 10/21/2020
 */

@Service
public class PokemonConsumerService {

    @Autowired
    private BasicInfoRepository basicInfoRepository;

    private final RestTemplate restTemplate;

    private final static String POKEMON_URL = "https://pokeapi.co/api/v2/pokemon/";

    public PokemonConsumerService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    //find pokemon by name in db of basicInfo, return object containing name and url, get rest of pokemon attributes using PokemonDto and the url.

    public PokemonDto findPokemonByName(String name){
        var pokemon = basicInfoRepository.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found"));
        var match = pokemon.name + " " + pokemon.url;
        System.out.println(pokemon.name + " " + pokemon.url);
        var pokemonInfo = restTemplate.getForObject(pokemon.url,PokemonDto.class);
        if(pokemonInfo != null){
            pokemonInfo.setName(pokemon.name);

        }
        else{
            System.out.println("Pokemon not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found");
        }
        return pokemonInfo;
    }

/*    public PokemonDto findPokemonByName(String name){

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
    }*/
}
