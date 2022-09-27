package com.blackdragon2447.codex;

import com.blackdragon2447.codex.database.Database;
import com.blackdragon2447.codex.gui.CodexGui;
import com.blackdragon2447.codex.util.CodexLogger;
import java.util.logging.Level;

public class App {

  public static void main(String[] args) {
    CodexLogger.log("Starting Codex", Level.INFO);

    try {
      Database.initialize();
    } catch (Exception e) {
      CodexLogger.log(e);
      System.exit(1);
    }

    CodexLogger.log("Done initializing database", Level.INFO);

    CodexGui.start();
  }
}
