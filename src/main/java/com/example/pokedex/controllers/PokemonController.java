package com.example.pokedex.controllers;

import com.example.pokedex.dto.PokemonDto;
import com.example.pokedex.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<PokemonDto> findPokemon(@RequestParam String name){
        var pokemon = pokemonService.findPokemonByName(name);
        return ResponseEntity.ok(pokemon);
    }
}
