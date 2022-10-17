package com.blackdragon2447.codex;

import com.blackdragon2447.codex.database.Database;
import com.blackdragon2447.codex.database.Query;
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

    // CodexLogger.log("Gonnan do some Query testing", Level.INFO);

    // System.out.println(
    //     new Query.Select("*", "Employee")
    //         .whereValueIn("location", "Seattle", "Austin", "New York")
    //         .assemble());
    //
    // System.out.println(new Query.Select("*", "Employee")
    //                        .whereGreaterThan("employee_id", "110")
    //                        .whereGreaterThanOrEqual("salary", "10000")
    //                        .assemble());
    //
    // CodexLogger.log("Done testing", Level.INFO);

    CodexLogger.log("Starting gui", Level.INFO);

    CodexGui.start();
  }
}
