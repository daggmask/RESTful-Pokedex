package com.example.pokedex.entities;

import org.springframework.data.annotation.Id;

/**
 * <Description>
 *
 * @author Martin Hellström
 * @version 1.0
 * @since 10/19/2020
 */
public class BasicInfo {
    public String name;
    public String url;

    public BasicInfo() {
    }

    public BasicInfo(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
