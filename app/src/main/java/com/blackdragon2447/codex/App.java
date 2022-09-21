package com.blackdragon2447.codex;

import com.blackdragon2447.codex.util.CodexLogger;
import java.util.logging.Level;

public class App {
    public String getGreeting() {
        return "no World!";
    }

  public static void main(String[] args) {
    CodexLogger.log("Starting Codex", Level.INFO);
  }
}
