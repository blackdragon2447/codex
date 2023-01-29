package com.blackdragon2447.codex;

import com.blackdragon2447.codex.database.Database;
import com.blackdragon2447.codex.gui.CodexGui;
import com.blackdragon2447.codex.models.Character;
import com.blackdragon2447.codex.models.Race;
import com.blackdragon2447.codex.models.RaceAttribute;

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

        // We're just building a test character here
        // ----------------------------------------------------------------------------

        Character character = new Character();
        character.setName("Test Character");

        Race race = new Race("Test Race",
                "Lorem ipsum dolor sit amet, qui minim labore adipisicing minim sint cillum sint consectetur cupidatat.");

        race.addAttribute("Test Attribute", new RaceAttribute.DerivedRaceAttribute("size", 1));

        character.setRace(race);

        character.increaceSmarts();
        character.increaceSmarts();

        character.increaceVigor();

        Database.putRace(race.getSystemName(), race);
        Database.putCharacter(character.getSystemName(), character);

        Database.save();

        // ----------------------------------------------------------------------------

        DEBUG_LOGGER.log(Level.INFO, "Starting Gui");
        CodexGui.start();
    }
}
