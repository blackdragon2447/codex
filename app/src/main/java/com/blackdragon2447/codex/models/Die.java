package com.blackdragon2447.codex.models;

import java.util.Random;

public enum Die {

    D4,
    D6,
    D8,
    D10,
    D12,
    D20;

    public int roll() {

        Random random = new Random();

        return switch (this) {
            case D4 -> random.nextInt(3) + 1;
            case D6 -> random.nextInt(5) + 1;
            case D8 -> random.nextInt(7) + 1;
            case D10 -> random.nextInt(9) + 1;
            case D12 -> random.nextInt(11) + 1;
            case D20 -> random.nextInt(19) + 1;
        };
    }

}
