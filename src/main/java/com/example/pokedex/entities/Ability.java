package com.example.pokedex.entities;

import org.springframework.data.annotation.Id;

/**
 * <Description>
 *
 * @author Martin Hellström
 * @version 1.0
 * @since 10/19/2020
 */
public class Ability{
    @Id
    private String id;
    public BasicInfo ability;
    public Boolean is_hidden;

    public Ability() {
        super();
    }

    public Ability(String name, String url, Boolean is_hidden) {
        this.ability = new BasicInfo(name,url);
        this.is_hidden = is_hidden;
    }

    public Boolean getHidden() {
        return is_hidden;
    }

    public void setHidden(Boolean hidden) {
        is_hidden = hidden;
    }
}
