package com.example.pokedex.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <Description>
 *
 * @author Martin Hellström
 * @version 1.0
 * @since 10/19/2020
 */
public class Ability{
    public BasicInfo ability;
    @JsonProperty("is_hidden")
    public Boolean isHidden;

    public Ability() {
        super();
    }

    public Ability(String name, String url, Boolean isHidden) {
        this.ability = new BasicInfo(name,url);
        this.isHidden = isHidden;
    }

    public Boolean getHidden() {
        return isHidden;
    }

    public void setHidden(Boolean hidden) {
        isHidden = hidden;
    }
}
