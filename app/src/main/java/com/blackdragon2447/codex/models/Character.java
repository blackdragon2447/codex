package com.blackdragon2447.codex.models;

import com.blackdragon2447.codex.models.RaceAttribute.AttributeRaceAttribute;
import com.blackdragon2447.codex.models.RaceAttribute.DerivedRaceAttribute;
import com.blackdragon2447.codex.models.RaceAttribute.SkillRaceAttribute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Character implements Serializable {

    private String systemName;
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

    /**
     * Tries to increase the characters agility stat, will not increase above the
     * limit (by default 1d12 + 0)
     *
     * @return indicates if the increase was successfull
     */
    public boolean increaceAgility() {

        if ((agilityAttr.getDie().lessThan(Die.D12) && agilityAttrMax == 0)
                || agilityAttr.getBonus() < agilityAttrMax) {
            agilityAttr.increase();
            return true;
        }

        return false;

    }

    /**
     * Tries to increase the characters smarts stat, will not increase above the
     * limit (by default 1d12 + 0)
     *
     * @return indicates if the increase was successfull
     */
    public boolean increaceSmarts() {

        if ((smartsAttr.getDie().lessThan(Die.D12) && smartsAttrMax == 0)
                || smartsAttr.getBonus() < smartsAttrMax) {
            smartsAttr.increase();
            return true;
        }

        return false;

    }

    /**
     * Tries to increase the characters spirit stat, will not increase above the
     * limit (by default 1d12 + 0)
     *
     * @return indicates if the increase was successfull
     */
    public boolean increaceSpirit() {

        if ((spiritAttr.getDie().lessThan(Die.D12) && spiritAttrMax == 0)
                || spiritAttr.getBonus() < spiritAttrMax) {
            spiritAttr.increase();
            return true;
        }

        return false;

    }

    /**
     * Tries to increase the characters strength stat, will not increase above the
     * limit (by default 1d12 + 0)
     *
     * @return indicates if the increase was successfull
     */
    public boolean increaceStrength() {

        if ((strengthAttr.getDie().lessThan(Die.D12) && strengthAttrMax == 0)
                || strengthAttr.getBonus() < strengthAttrMax) {
            strengthAttr.increase();
            return true;
        }

        return false;

    }

    /**
     * Tries to increase the characters vigor stat, will not increase above the
     * limit (by default 1d12 + 0)
     *
     * @return indicates if the increase was successfull
     */
    public boolean increaceVigor() {

        if ((vigorAttr.getDie().lessThan(Die.D12) && vigorAttrMax == 0)
                || vigorAttr.getBonus() < vigorAttrMax) {
            vigorAttr.increase();
            return true;
        }

        return false;

    }

    /**
     * Tries to add a edge to the character, currently it wil always be successfull
     * since requirements arent checked
     *
     * @param edge
     * @return indicates if the edge could be added, currently always true
     */
    public boolean addEdge(Edge edge) {

        if (true) { // check requirement
            edges.add(edge);
            return true;
        }

        return false;

    }

    /**
     * Sets a characters race, will only succeed of the character does not have a
     * race yet.
     *
     * @param race
     * @return indicates if the race could be set
     */
    public boolean setRace(Race race) {
        if (this.race == null) {
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
            return true;
        } else {
            return false;
        }

    }

    /**
     * Adds a skill which is neither one of the basic skills nor already added.
     *
     * @param name
     * @param skill
     * @return indicates if the skill was added
     */
    public boolean addSkill(String name, Skill skill) {
        if (Arrays.asList(new String[] { "athletics", "common_knowledge", "notice", "persuasion", "stealth" })
                .contains(name)
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

    public void setName(String name) {
        this.name = name;
        if (this.systemName == null)
            this.systemName = name.toLowerCase().replace(' ', '_');
    }

    public String getName() {
        return name;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setPace(int pace) {
        this.pace = pace;
    }

    public int getPace() {
        return pace;
    }

    public int getParry() {
        return parry;
    }

    public int getToughness() {
        return toughness;
    }

    public int getSize() {
        return size;
    }

    public Attribute getAgilityAttr() {
        return agilityAttr;
    }

    public Attribute getSmartsAttr() {
        return smartsAttr;
    }

    public Attribute getSpiritAttr() {
        return spiritAttr;
    }

    public Attribute getStrengthAttr() {
        return strengthAttr;
    }

    public Attribute getVigorAttr() {
        return vigorAttr;
    }

    public Skill getAthleticsSkill() {
        return athleticsSkill;
    }

    public Skill getCommonKnowledgeSkill() {
        return commonKnowledgeSkill;
    }

    public Skill getNoticeSkill() {
        return noticeSkill;
    }

    public Skill getPersuasionSkill() {
        return persuasionSkill;
    }

    public Skill getStealthSkill() {
        return stealthSkill;
    }

    private void update() {
        if (wounds > 3 || fatigue > 2)
            this.incapacitated = true;
    }

    /**
     * Converts a characters advances to a rank
     *
     * @return the characters rank
     */
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
