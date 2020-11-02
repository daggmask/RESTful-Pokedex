package com.example.pokedex.controllers;

import com.example.pokedex.entities.Pokemon;
import com.example.pokedex.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <Description>
 *
 * @author Martin Hellström
 * @version 1.0
 * @since 10/22/2020
 */

@RestController
public class OpenPokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/OpenPokedex")
    public ResponseEntity<List<Pokemon>> findPokemonByNameAndType(@RequestParam String name,
                                                                  @RequestParam(required = false) String type){
        var pokemon = pokemonService.findPokemonByName(name, type);
        if(pokemon.isEmpty()){
            return ResponseEntity.status(404).body(pokemon);
        }
        return ResponseEntity.ok(pokemon);
    }
}
