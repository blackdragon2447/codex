package com.blackdragon2447.codex.models;

import java.io.Serializable;
import java.util.List;

public class Edge implements Serializable {

    private String name;
    private EdgeType type;
    private String description;
    // That `?` needs looking at
    private List<Requirement<?>> requirements;

    public enum EdgeType {
        Background, Combat, Leadership, Power, Professional, Social, Weird, Wild_Card, Legendary
    }

}
