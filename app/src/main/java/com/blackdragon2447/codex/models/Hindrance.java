package com.blackdragon2447.codex.models;

import com.blackdragon2447.codex.models.Race.AttributeEffect;

//TODO: Add type
public record Hindrance(String name, String description, HindranceType type) implements AttributeEffect {

    public enum HindranceType {
        MAYOR,
        MINOR,
    }

}
