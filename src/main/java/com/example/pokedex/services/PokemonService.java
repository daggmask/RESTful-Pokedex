package com.example.pokedex.services;

import com.example.pokedex.entities.Pokemon;
import com.example.pokedex.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * <Description>
 *
 * @author Martin Hellström
 * @version 1.0
 * @since 10/19/2020
 */

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    public List<Pokemon> findAll(){
        return pokemonRepository.findAll();
    }

    public ResponseEntity<List<Pokemon>> findByName(String name){
            return ResponseEntity.ok(pokemonRepository.findByName(name).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No pokemon found by name: %s", name))));
    }

    public ResponseEntity<String> savePokemon(Pokemon pokemon){
        if(pokemon == null){
            return ResponseEntity.status(406).body("Body required");
        }
        pokemonRepository.save(pokemon);
        return ResponseEntity.ok("Save successful");
    }

    public ResponseEntity<String> updatePokemon(String id, Pokemon pokemon) {
        if (!pokemonRepository.existsById(id)) {
            return ResponseEntity.status(400).body(String.format("No pokemon found by id: %s", id));
        }
        pokemon.setPokemonID(id);
        pokemonRepository.save(pokemon);
        return ResponseEntity.ok(String.format("Update successful for pokemon: %s", pokemon.getName()));
    }
    public ResponseEntity<String> deletePokemon(String id){
        if (!pokemonRepository.existsById(id)) {
            return ResponseEntity.status(400).body(String.format("No pokemon found by id: %s", id));
        }
        var pokemon = pokemonRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found"));
        pokemonRepository.deleteById(id);
        return ResponseEntity.ok(String.format("Update successful for pokemon: %s", pokemon.getName()));
    }
}
