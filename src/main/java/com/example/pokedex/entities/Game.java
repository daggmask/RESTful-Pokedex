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

    public Game() {
    }

    public Game(int id, String name, String url) {
        this.version = new BasicInfo(name,url);
        this.game_index = id;
    }

    public BasicInfo getVersion() {
        return version;
    }

    public void setVersion(BasicInfo version) {
        this.version = version;
    }

    public int getGame_index() {
        return game_index;
    }

    public void setGame_index(int game_index) {
        this.game_index = game_index;
    }
}
