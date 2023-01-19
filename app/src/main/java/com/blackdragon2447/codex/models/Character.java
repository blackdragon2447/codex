package com.blackdragon2447.codex.models;

import com.blackdragon2447.codex.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Character implements Serializable {

    private int id;
    private String name;
    private Race race;

    private int pace;
    private int parry;
    private int size;
    private int toughness;

    private int wounds;
    private int fatigue;
    private boolean incapacitated;

    private Attribute agilityAttr;
    private Attribute smartsAttr;
    private Attribute spiritAttr;
    private Attribute strengthAttr;
    private Attribute vigorAttr;

    private Skill athleticsSkill;
    private Skill commonKnowledgeSkill;
    private Skill noticeSkill;
    private Skill persuasionSkill;
    private Skill stealthSkill;
    private HashMap<String, Skill> skills;

    private ArrayList<Edge> edges;
    private int advances;

    public void update() {

    }

    public Rank getRank() {
        if (this.advances <= 3)
            return Rank.NOVICE;
        else if (this.advances <= 7)
            return Rank.SEASONED;
        else if (this.advances <= 11)
            return Rank.VETERAN;
        else if (this.advances <= 15)
            return Rank.HEROIC;
        else
            return Rank.LEGENDARY;
    }

}
