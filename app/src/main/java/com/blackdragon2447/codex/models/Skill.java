package com.blackdragon2447.codex.models;

import java.io.Serializable;

public class Skill implements Serializable {

    private Attribute linkedAttribute;
    private Die die = Die.D4;
    private int bonus = 0;

    public Skill(Attribute linkedAttribute) {
        this.linkedAttribute = linkedAttribute;
    }

    public void increase() {

        switch (this.die) {
            case D4 -> this.die = Die.D6;
            case D6 -> this.die = Die.D8;
            case D8 -> this.die = Die.D10;
            case D10 -> this.die = Die.D12;
            case D12 -> this.die = Die.D20;
            case D20 -> this.bonus += 1;
        }
    }

    public void increase(int amount) {
        for (int i = 0; i < amount; i++) {
            this.increase();
        }
    }

}
