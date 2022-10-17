package com.blackdragon2447.codex.util;

import java.lang.Throwable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CodexLogger {
  private static final Logger DEBUG_LOGGER = Logger.getLogger("Codex");

  public static void log(String message, Level level) {
    DEBUG_LOGGER.log(level, message);
  }

  public static void log(Throwable thrown) {
    DEBUG_LOGGER.log(Level.WARNING, "An exception has occured", thrown);
  }
}
