package com.blackdragon2447.codex.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.blackdragon2447.codex.gui.CodexGui;

import java.lang.Throwable;

public class CodexLogger {
  private static final Logger DEBUG_LOGGER = Logger.getLogger("Codex");

  public static void log(String message, Level level) {
    DEBUG_LOGGER.log(level, message); 
   
    CodexGui.start();
  }
  
  public static void log(Throwable thrown) {
   DEBUG_LOGGER.log(Level.WARNING, "An expeption has occured", thrown); 
  }

}
