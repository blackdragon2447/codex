package com.blackdragon2447.codex.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import com.blackdragon2447.codex.App;
import net.harawata.appdirs.AppDirsFactory;

public class Database {

  private static boolean initialized = false;
  private static Connection connection = null;

  public static void initialize() throws SQLException {

    if (!initialized) {

      String path =
          AppDirsFactory.getInstance().getUserDataDir("codex", "", "");
      String databaseUrl = "jdbc:h2:" + path + "codex";

      connection = DriverManager.getConnection(databaseUrl);

      initialized = true;

    } else {
        App.DEBUG_LOGGER.log(Level.WARNING, "The database has already been initialized");
    }
  }
}
