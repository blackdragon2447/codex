package com.blackdragon2447.codex.models;

import com.blackdragon2447.codex.models.Rank;
import com.blackdragon2447.codex.models.RaceAttribute.AttributeRaceAttribute;
import com.blackdragon2447.codex.models.RaceAttribute.DerivedRaceAttribute;
import com.blackdragon2447.codex.models.RaceAttribute.SkillRaceAttribute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
    private int agilityAttrMax; // the max value this attribute can go to (0 = 1d12, 1 = 1d12 + 1, ...)
    private Attribute smartsAttr;
    private int smartsAttrMax; // the max value this attribute can go to (0 = 1d12, 1 = 1d12 + 1, ...)
    private Attribute spiritAttr;
    private int spiritAttrMax; // the max value this attribute can go to (0 = 1d12, 1 = 1d12 + 1, ...)
    private Attribute strengthAttr;
    private int strengthAttrMax; // the max value this attribute can go to (0 = 1d12, 1 = 1d12 + 1, ...)
    private Attribute vigorAttr;
    private int vigorAttrMax; // the max value this attribute can go to (0 = 1d12, 1 = 1d12 + 1, ...)

    private Skill athleticsSkill;
    private Skill commonKnowledgeSkill;
    private Skill noticeSkill;
    private Skill persuasionSkill;
    private Skill stealthSkill;
    private HashMap<String, Skill> skills;

    private ArrayList<Edge> edges;
    private int advances;

    public Character() {
        this.pace = 5;
        this.size = 0;

        this.wounds = 0;
        this.fatigue = 0;
        this.incapacitated = false;

        this.agilityAttr = new Attribute();
        this.smartsAttr = new Attribute();
        this.spiritAttr = new Attribute();
        this.strengthAttr = new Attribute();
        this.vigorAttr = new Attribute();

        this.athleticsSkill = new Skill(this.agilityAttr);
        this.commonKnowledgeSkill = new Skill(this.smartsAttr);
        this.noticeSkill = new Skill(this.smartsAttr);
        this.persuasionSkill = new Skill(this.spiritAttr);
        this.stealthSkill = new Skill(this.agilityAttr);
        this.skills = new HashMap<>();

        this.edges = new ArrayList<>();

        this.advances = 0;
    }

    public boolean increaceAgility() {

        if ((agilityAttr.getDie().lessThan(Die.D12) && agilityAttrMax == 0)
                || agilityAttr.getBonus() < agilityAttrMax) {
            agilityAttr.increase();
        }

        return false;

    }

    public boolean increaceSmarts() {

        if ((smartsAttr.getDie().lessThan(Die.D12) && smartsAttrMax == 0)
                || smartsAttr.getBonus() < smartsAttrMax) {
            smartsAttr.increase();
        }

        return false;

    }

    public boolean increaceSpirit() {

        if ((spiritAttr.getDie().lessThan(Die.D12) && spiritAttrMax == 0)
                || spiritAttr.getBonus() < spiritAttrMax) {
            spiritAttr.increase();
        }

        return false;

    }

    public boolean increaceStrength() {

        if ((strengthAttr.getDie().lessThan(Die.D12) && strengthAttrMax == 0)
                || strengthAttr.getBonus() < strengthAttrMax) {
            strengthAttr.increase();
        }

        return false;

    }

    public boolean increaceVigor() {

        if ((vigorAttr.getDie().lessThan(Die.D12) && vigorAttrMax == 0)
                || vigorAttr.getBonus() < vigorAttrMax) {
            vigorAttr.increase();
            return true;
        }

        return false;

    }

    public boolean addEdge(Edge edge) {

        if (true) { // check requirement
            edges.add(edge);
            return true;
        }

        return false;

    }

    public void setRace(Race race) {
        this.race = race;

        for (RaceAttribute attr : race.getAttributes().values()) {
            if (attr instanceof DerivedRaceAttribute derAttr) {
                switch (derAttr.getSkill()) {
                    case "pace" -> this.pace += derAttr.getModifier();
                    case "parry" -> this.parry += derAttr.getModifier();
                    case "size" -> this.size += derAttr.getModifier();
                    case "toughness" -> this.toughness += derAttr.getModifier();
                }
            } else if (attr instanceof SkillRaceAttribute skillAttr) {
                switch (skillAttr.getSkill()) {
                    case "athletics" -> this.athleticsSkill.increase(skillAttr.getModifier());
                    case "common_knowledge" -> this.commonKnowledgeSkill.increase(skillAttr.getModifier());
                    case "notice" -> this.noticeSkill.increase(skillAttr.getModifier());
                    case "persuasion" -> this.persuasionSkill.increase(skillAttr.getModifier());
                    case "stealth" -> this.stealthSkill.increase(skillAttr.getModifier());
                    default -> {
                        if (!skills.containsKey(skillAttr.getSkill())) {
                            Skill skill = new Skill(null);
                            skill.increase(skillAttr.getModifier());
                            skills.put(skillAttr.getSkill(), skill);
                        } else {
                            skills.get(skillAttr.getSkill()).increase(skillAttr.getModifier());
                        }
                    }
                }
            } else if (attr instanceof AttributeRaceAttribute attrAttr) {
                switch (attrAttr.getAttribute()) {
                    case "agility" -> this.increaceAgility();
                    case "smarts" -> this.increaceSmarts();
                    case "spirit" -> this.increaceSpirit();
                    case "strength" -> this.increaceStrength();
                    case "vigor" -> this.increaceVigor();
                }
            }
        }

    }

    public boolean addSkill(String name, Skill skill) {
        if (Arrays.asList(new String[] { "agility", "smarts", "spirit", "strength", "vigor" }).contains(name)
                || this.skills.keySet().contains(name)) {
            return false;
        } else {
            this.skills.put(name, skill);
            return true;
        }
    }

    public Race getRace() {
        return race;
    }

    public void update() {
        if (wounds > 3 || fatigue > 2)
            this.incapacitated = true;
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
