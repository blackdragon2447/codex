package com.blackdragon2447.codex.models;

import java.io.Serializable;

public abstract sealed class RaceAttribute implements
        Serializable permits RaceAttribute.TextRaceAttribure, RaceAttribute.DerivedRaceAttribute, RaceAttribute.SkillRaceAttribute, RaceAttribute.AttributeRaceAttribute {

    private String name;
    private String description;

    public static final class TextRaceAttribure extends RaceAttribute {
    }

    public static final class DerivedRaceAttribute extends RaceAttribute {

        private String skill; // must be one of pace, parry, size or toughness
        private int modifier;

        public DerivedRaceAttribute(String skill, int modifier) {
            this.skill = skill;
            this.modifier = modifier;
        }

        public String getSkill() {
            return skill;
        }

        public int getModifier() {
            return modifier;
        }
    }

    public static final class SkillRaceAttribute extends RaceAttribute {
        private String skill;
        private int modifier;

        public String getSkill() {
            return skill;
        }

        public int getModifier() {
            return modifier;
        }
    }

    public static final class AttributeRaceAttribute extends RaceAttribute {

        private String attribute;
        private int modifier;

        public String getAttribute() {
            return attribute;
        }

        public int getModifier() {
            return modifier;
        }

    }

}
