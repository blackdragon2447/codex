package com.blackdragon2447.codex.models;

import java.util.Optional;

public class HindranceHolder {

    private int points;

    private Hindrance minorSlot1;
    private Hindrance minorSlot2;
    private Hindrance minorSlot3;
    private Hindrance minorSlot4;

    private Hindrance majorSlot1;
    private Hindrance majorSlot2;

    public void addHindrance(Hindrance hindrance) throws OutOfPointsException {
        switch (hindrance.type()) {
            case MAYOR -> {
                if (points > 2) throw new OutOfPointsException();
                points += 2;
                if (this.majorSlot1 == null)
                    this.majorSlot1 = hindrance;
                else
                    this.majorSlot2 = hindrance;
            }
            case MINOR -> {
                if (points > 3) throw new OutOfPointsException();
                points++;
                if (this.minorSlot1 == null)
                    minorSlot1 = hindrance;
                else if (this.minorSlot2 == null)
                    minorSlot2 = hindrance;
                else if (this.minorSlot3 == null)
                    minorSlot3 = hindrance;
                else
                    minorSlot4 = hindrance;

            }
        }
    }

    public static class OutOfPointsException extends Exception {

    }
}
