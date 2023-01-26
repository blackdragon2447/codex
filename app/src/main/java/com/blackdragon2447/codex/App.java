package com.blackdragon2447.codex;

import com.blackdragon2447.codex.database.Database;
import com.blackdragon2447.codex.gui.CodexGui;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public static final Logger DEBUG_LOGGER = Logger.getLogger("Codex");

    public static void main(String[] args) throws IOException {
        DEBUG_LOGGER.log(Level.INFO, "Starting Codex");

        DEBUG_LOGGER.log(Level.INFO, "Initializing database");

        try {
            Database.load();
        } catch (Exception e) {
            DEBUG_LOGGER.log(Level.WARNING, "", e);
            System.exit(1);
        }

        DEBUG_LOGGER.log(Level.INFO, "Done initializing database");

        CodexGui.start();

    }

}
