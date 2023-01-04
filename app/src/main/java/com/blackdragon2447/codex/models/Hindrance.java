package com.blackdragon2447.codex.models;


//TODO: Add type
public record Hindrance(String name, String description, HindranceType type) {

    public enum HindranceType {
        MAYOR,
        MINOR,
    }

}
