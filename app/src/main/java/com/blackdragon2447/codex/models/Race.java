package com.blackdragon2447.codex.models;

import java.io.Serializable;
import java.util.HashMap;

public class Race implements Serializable {

    private String name;
    private String description;
    private HashMap<String, RaceAttribute> attributes;

}
