package com.blackdragon2447.codex.database;

import com.blackdragon2447.codex.util.CodexLogger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
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

    } else {
      CodexLogger.log("The database has already been initialized",
                      Level.WARNING);
    }
  }
}
