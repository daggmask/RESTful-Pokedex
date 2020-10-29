package com.example.pokedex.controllers;

import com.example.pokedex.entities.Pokemon;
import com.example.pokedex.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * <Description>
 *
 * @author Martin Hellström
 * @version 1.0
 * @since 10/20/2020
 */
@RestController
@RequestMapping("/api/v1/pokedex")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;


    @GetMapping
    public ResponseEntity<List<Pokemon>> findPokemonByNameAndType(@RequestParam String name, @RequestParam(required = false) String type){
        var pokemon = pokemonService.findPokemonByName(name, type);
        if(pokemon.isEmpty()){
            return ResponseEntity.status(404).body(pokemon);
        }
        return ResponseEntity.ok(pokemon);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> findPokemonById(@PathVariable String id){
        return ResponseEntity.ok(pokemonService.findById(id));
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Pokemon> savePokemon(@RequestBody Pokemon pokemon){
        var savedPokemon = pokemonService.savePokemon(pokemon);
        var uri = URI.create("/api/v1/pokedex" + savedPokemon.getPokemonID());
        return ResponseEntity.created(uri).body(savedPokemon);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public void updatePokemon(@PathVariable String id, @RequestBody Pokemon pokemon){
        pokemonService.updatePokemon(id,pokemon);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePokemon(@PathVariable String id){
        pokemonService.deletePokemon(id);
    }
}
