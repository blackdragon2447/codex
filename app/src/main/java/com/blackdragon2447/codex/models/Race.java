package com.blackdragon2447.codex.models;

import java.io.Serializable;
import java.util.HashMap;

public class Race implements Serializable {

    private String name;
    private String description;
    private HashMap<String, RaceAttribute> attributes;

    public HashMap<String, RaceAttribute> getAttributes() {
        return attributes;
    }

    public Race(String name, String description) {
        this.name = name;
        this.description = description;
        this.attributes = new HashMap<>();
    }

    public void addAttribute(String name, RaceAttribute attribute) {
        this.attributes.put(name, attribute);
    }

    public String getName() {
        return name;
    }

    public String getSystemName() {
        return this.name.toLowerCase().replace(' ', '_');
    }

}
