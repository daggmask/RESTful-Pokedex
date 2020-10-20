package com.example.pokedex.entities;

/**
 * <Description>
 *
 * @author Martin Hellström
 * @version 1.0
 * @since 10/19/2020
 */
public class Ability extends BasicInfo {
    private Boolean isHidden;


    public Ability(String name, String url, Boolean isHidden) {
        super(name, url);
        this.isHidden = isHidden;
    }

    public Boolean getHidden() {
        return isHidden;
    }

    public void setHidden(Boolean hidden) {
        isHidden = hidden;
    }
}
