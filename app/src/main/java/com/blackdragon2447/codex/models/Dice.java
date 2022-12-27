package com.blackdragon2447.codex.models;

enum Dice {
  D4,
  D6,
  D8,
  D10,
  D12;

  public int roll() {
    return switch (this) {
      case D4 -> Math.round(Math.round(Math.random() * 4));
      case D6 -> Math.round(Math.round(Math.random() * 6));
      case D8 -> Math.round(Math.round(Math.random() * 8));
      case D10 -> Math.round(Math.round(Math.random() * 10));
      case D12 -> Math.round(Math.round(Math.random() * 12));
      default -> 0;
    };
  }

}
