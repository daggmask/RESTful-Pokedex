package com.example.pokedex.services;

import com.example.pokedex.entities.PokemonList;
import com.example.pokedex.repositories.BasicInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;

/**
 * <Description>
 *
 * @author Martin Hellström
 * @version 1.0
 * @since 10/22/2020
 */

@Service
public class PokemonStartup {
    @Autowired
    private BasicInfoRepository basicInfoRepository;

    private final RestTemplate restTemplate;



    public PokemonStartup(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @PostConstruct
    public void saveAllPokemon(){
        var pokemonListURL = "https://pokeapi.co/api/v2/pokemon?limit=2000&offset=0";
        var allPokemons = restTemplate.getForObject(pokemonListURL, PokemonList.class);
        if(allPokemons != null){
            var list  = allPokemons.getResults();
            var alreadyInDB = basicInfoRepository.findAll();
            if(!alreadyInDB.isEmpty()){
                basicInfoRepository.saveAll(list);
            }
        }

    }
}
