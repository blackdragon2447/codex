package com.blackdragon2447.codex.models;

import java.util.HashMap;

import com.blackdragon2447.codex.util.Pair;

public class Character {

  final Race race;
  final HinderanceHolder hinderances;
  Attributes attributes;
  HashMap<Skills, Pair<Dice, Integer>> skills;

  public Character() {
    this.race = null;
    this.hinderances = null;
  }

  public class Attributes {

    Dice agilityDie = Dice.D4;
    int agilityModifier = 0;

    Dice smartsDie = Dice.D4;
    int smartsModifier = 0;

    Dice spiritDie = Dice.D4;
    int spiritModifier = 0;

    Dice strengthDie = Dice.D4;
    int strengthModifier = 0;

    Dice vigorDie = Dice.D4;
    int vigorModifier = 0;
  }
}
