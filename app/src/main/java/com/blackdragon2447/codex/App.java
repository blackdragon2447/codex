package com.blackdragon2447.codex;

import com.blackdragon2447.codex.database.Database;
import com.blackdragon2447.codex.gui.CodexGui;
import com.blackdragon2447.codex.models.Race;
import com.blackdragon2447.codex.models.Hinderance;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

  private static final Logger DEBUG_LOGGER = Logger.getLogger("Codex");

  public static void main(String[] args) {
    DEBUG_LOGGER.log(Level.INFO, "Starting Codex");

    try {
      Database.initialize();
    } catch (Exception e) {
      DEBUG_LOGGER.log(Level.WARNING, "", e);
      System.exit(1);
    }

    DEBUG_LOGGER.log(Level.INFO, "Done initializing database");

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
  
    DEBUG_LOGGER.log(Level.INFO, "Testing races");
  
    System.out.println(new Race("Android", new Race.Attribute(Race.Attribute.AttributeType.MAJOR, "Pacifist", "Lorem Ipsem", new Hinderance("Pacifist"))));

    DEBUG_LOGGER.log(Level.INFO, "Done testing races");

    // CodexGui.start();
  }

}
