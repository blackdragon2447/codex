package com.blackdragon2447.codex.models;

import java.util.ArrayList;
import java.util.Arrays;

import com.blackdragon2447.codex.models.Stats.StatModifier;

public record Race(String description, ArrayList<Attribute> atributes){
    
    public Race(String description, Attribute... attributes) {
    
        this(description, new ArrayList<Attribute>(Arrays.asList(attributes)));
    
    }
    
    public final record Attribute (
        AttributeType type,
        String name,
        String description,
        ArrayList<AttributeEffect> effect
    ){
        public Attribute(AttributeType type, String name, String description, AttributeEffect... effects){
            this(type, name, description, new ArrayList<AttributeEffect>(Arrays.asList(effects)));
        }
        
        public enum AttributeType{
            MAJOR,
            MINOR;
        }
    }
    
    public sealed interface AttributeEffect permits 
        Hinderance, 
        StatModifier, 
        StringAttributeEffect
        {}
    
    public record StringAttributeEffect(String description) implements AttributeEffect{}
};
