package com.example.pokedex.entities;

/**
 * <Description>
 *
 * @author Martin Hellström
 * @version 1.0
 * @since 10/19/2020
 */
public class Game {

    public BasicInfo version;
    public int game_index;
    public Game(int id, String name, String url) {
        this.version = new BasicInfo(name,url);
        this.game_index = id;
    }
}
