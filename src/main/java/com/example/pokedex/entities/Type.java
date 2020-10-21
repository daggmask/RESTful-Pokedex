package com.example.pokedex.entities;

/**
 * <Description>
 *
 * @author Martin Hellström
 * @version 1.0
 * @since 10/19/2020
 */
public class Type{
    private BasicInfo type;

    public Type() {
    }

    public Type(String name, String url) {
        this.type = new BasicInfo(name,url);
    }

    public BasicInfo getType() {
        return type;
    }

    public void setType(BasicInfo type) {
        this.type = type;
    }
}
