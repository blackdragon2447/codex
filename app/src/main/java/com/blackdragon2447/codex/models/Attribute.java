package com.blackdragon2447.codex.models;

import java.io.Serializable;

public class Attribute implements Serializable {

    private Die die = Die.D4;
    private int bonus = 0;

    /**
     * Increases the dietype of this
     * {@link com.blackdragon2447.codex.models.Attribute} up until D20, any more
     * will increase the bonus
     */
    public void increase() {

        switch (this.die) {
            case D4 -> this.die = Die.D6;
            case D6 -> this.die = Die.D8;
            case D8 -> this.die = Die.D10;
            case D10 -> this.die = Die.D12;
            case D12 -> this.bonus += 1;
            default -> throw new IllegalArgumentException("Unexpected value: " + this.die);
        }
    }

    /**
     * Increases the dietype of this
     * {@link com.blackdragon2447.codex.models.Attribute} up until D20, any more
     * will increase the bonus.
     * 
     * @param amount the amount to incrase by
     */
    public void increase(int amount) {
        for (int i = 0; i < amount; i++) {
            this.increase();
        }
    }

    public Die getDie() {
        return die;
    }

    public int getBonus() {
        return bonus;
    }

    public void setDie(Die die) {
        this.die = die;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

}
