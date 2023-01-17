package com.blackdragon2447.codex.models;

import com.blackdragon2447.codex.util.Pair;

import java.util.HashMap;

public class Character {

    String name;
    Race race;
    HindranceHolder hindrances;
    Attributes attributes;
    int pace;
    int parry;
    int toughness;

    //? Size
    HashMap<String, Pair<Dice, Integer>> skills;

    protected Character() {

    }

    public Character(String name, Attributes attributes, Race race) {
        this.name = name;
        this.race = race;
        this.hindrances = new HindranceHolder();
    }

    public Character addHindrance(Hindrance hindrance) {

        try {
            this.hindrances.addHindrance(hindrance);
        } catch (HindranceHolder.OutOfPointsException ignored) {

        }

        return this;
    }

    public record Attributes(

            Dice agilityDie,
            int agilityModifier,

            Dice smartsDie,
            int smartsModifier,

            Dice spiritDie,
            int spiritModifier,

            Dice strengthDie,
            int strengthModifier,

            Dice vigorDie,
            int vigorModifier
    ) {
    }
}
