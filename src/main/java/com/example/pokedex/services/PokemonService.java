package com.example.pokedex.services;

import com.example.pokedex.dto.PokemonDto;
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
    public List<Pokemon> findPokemonByParams(String name, String type, String ability, String game){

        var pokemonList = pokemonRepository.findAll();
        if(name != null) {
            pokemonList = this.filterPokemonsByName(name, pokemonList);
        }
        if(type != null){
            pokemonList = filterPokemonsByType(type,pokemonList);
        }
        if(ability != null){
            pokemonList = filterPokemonsByAbility(ability, pokemonList);
        }
        if(game != null){
            pokemonList = filterPokemonsByGame(game, pokemonList);
        }
        return  pokemonList;
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

    //Internal classes
    private List<Pokemon> getPokemonFromAPIAndSave(String name){
        var pokemons = basicInfoRepository.findAll();
        pokemons = pokemons.stream()
                .filter(pokemon -> pokemon.getName().contains(name))
                .collect(Collectors.toList());

        var pokemonsWithDetail = new ArrayList<Pokemon>();

        pokemons.forEach(pokemon -> {
            var pokemonDto = pokemonConsumerService.findPokemonByName(pokemon.getName());
            var pokemonWithDetail = new Pokemon(pokemonDto.getName(),pokemonDto.getHeight(),
                    pokemonDto.getWeight(),pokemonDto.getBaseExperience(),pokemonDto.getLocationEncounter(),
                    pokemonDto.getTypes(),pokemonDto.getAbilities(),pokemonDto.getGames(),pokemonDto.getSpecie());

            var pokemonExistInDB = pokemonInDBMatchingDto(pokemonDto);
            if(!pokemonExistInDB){
                this.savePokemon(pokemonWithDetail);
            }
            pokemonsWithDetail.add(pokemonWithDetail);
        });
        return pokemonsWithDetail;
    }

    private Boolean pokemonInDBMatchingDto(PokemonDto pokemonDto){
        var pokemonExistInDB = pokemonRepository.findAll();
        pokemonExistInDB = pokemonExistInDB.stream()
                .filter(pokemonInDB -> pokemonInDB.getName().toLowerCase().equals(pokemonDto.getName())).collect(Collectors.toList());
        return !pokemonExistInDB.isEmpty();
    }

    private List<Pokemon> filterPokemonsByName(String name, List<Pokemon> pokemonList){
        pokemonList = pokemonList.stream()
                .filter(pokemon -> pokemon.getName().toLowerCase().contains(name))
                .collect(Collectors.toList());
        if(pokemonList.isEmpty()){
            pokemonList = this.getPokemonFromAPIAndSave(name);
        }
        if(pokemonList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No pokemon found by name: %s", name));
        }
        return pokemonList;
    }

    private List<Pokemon> filterPokemonsByType( String type, List<Pokemon> pokemonList){
        pokemonList = pokemonList.stream()
                .filter(pokemon -> pokemon.getTypes().stream().anyMatch(pokeType -> pokeType.getType().name.toLowerCase().contains(type)))
                .collect(Collectors.toList());
        if(pokemonList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No pokemon found by type: %s", type));
        }
        return pokemonList;
    }

    private List<Pokemon> filterPokemonsByAbility( String ability, List<Pokemon> pokemonList){
        pokemonList = pokemonList.stream()
                .filter(pokemon -> pokemon.getAbilities().stream().anyMatch(pokeAbility -> pokeAbility.ability.name.toLowerCase().contains(ability)))
                .collect(Collectors.toList());
        if(pokemonList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No pokemon found by ability: %s", ability));
        }
        return pokemonList;
    }

    private List<Pokemon> filterPokemonsByGame( String game, List<Pokemon> pokemonList){
        pokemonList = pokemonList.stream()
                .filter(pokemon -> pokemon.getGames().stream().anyMatch(pokeGame -> pokeGame.version.name.toLowerCase().contains(game)))
                .collect(Collectors.toList());
        if(pokemonList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No pokemon found by game: %s", game));
        }
        return pokemonList;
    }
}
