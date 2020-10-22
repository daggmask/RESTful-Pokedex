package com.example.pokedex.entities;

import org.springframework.data.annotation.Id;

/**
 * <Description>
 *
 * @author Martin Hellström
 * @version 1.0
 * @since 10/19/2020
 */
public class Specie extends BasicInfo {
    public Specie(String name, String url) {
        super(name, url);
    }
}
