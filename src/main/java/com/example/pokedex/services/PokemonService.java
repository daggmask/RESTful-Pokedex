package com.example.pokedex.services;

import com.example.pokedex.entities.Pokemon;
import com.example.pokedex.repositories.BasicInfoRepository;
import com.example.pokedex.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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

    @Autowired
    private BasicInfoRepository basicInfoRepository;


    //Read
    public List<Pokemon> findPokemonByName(String name, String type){
/*        var pokemons = pokemonRepository.findAll();
        pokemons = pokemons.stream().filter(pokemon -> pokemon.getName().contains(name)).collect(Collectors.toList());*/

/*        pokemons = pokemons.stream()
                .filter(pokemon -> pokemon.getName().contains(name))
                .filter(pokemon -> pokemon.getTypes().stream()
                .anyMatch(pokeType -> pokeType.getType().name.contains(type))
        ).collect(Collectors.toList());*/

/*        try{
            if(type != null && !pokemons.isEmpty()){
                List<Pokemon> pokemonsFilteredByType = pokemons;
                pokemonsFilteredByType.forEach(pokemon -> {
                    pokemon.getTypes().forEach(pokemonType -> {
                        if (!pokemonType.getType().name.equals(type)){
                            pokemonsFilteredByType.remove(pokemon);
                        }
                    });
                });
                pokemons = pokemonsFilteredByType;
            }
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No pokemon found by search: name = %s and type = %s",name,type));
        }*/

/*            var pokemonDto = pokemonConsumerService.findPokemonByName(name);
            if(pokemonDto != null){
                System.out.println(pokemonDto.getName());
                var pokemon = new Pokemon(pokemonDto.getName(),pokemonDto.getHeight(),
                        pokemonDto.getWeight(),pokemonDto.getBaseExperience(),pokemonDto.getLocationEncounter(),
                        pokemonDto.getTypes(),pokemonDto.getAbilities(),pokemonDto.getGames(),pokemonDto.getSpecie());
                this.savePokemon(pokemon);
                pokemons.add(pokemon);
            }
        if(pokemons.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No pokemon found by search: name = %s and type = %s",name,type));
        }*/

        var pokemons = basicInfoRepository.findAll();
        pokemons = pokemons.stream().filter(pokemon -> pokemon.getName().contains(name)).collect(Collectors.toList());
        System.out.println(pokemons.get(0).getName());

        var pokemonsWithDetail = new ArrayList<Pokemon>();

        pokemons.forEach(pokemon -> {
            var pokemonDto = pokemonConsumerService.findPokemonByName(pokemon.getName());
            var pokemonWithDetail = new Pokemon(pokemonDto.getName(),pokemonDto.getHeight(),
                    pokemonDto.getWeight(),pokemonDto.getBaseExperience(),pokemonDto.getLocationEncounter(),
                    pokemonDto.getTypes(),pokemonDto.getAbilities(),pokemonDto.getGames(),pokemonDto.getSpecie());
            this.savePokemon(pokemonWithDetail);
            pokemonsWithDetail.add(pokemonWithDetail);
        });
        return pokemonsWithDetail;
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
