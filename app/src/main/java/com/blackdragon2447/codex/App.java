package com.blackdragon2447.codex;

import com.blackdragon2447.codex.database.Database;
import com.blackdragon2447.codex.models.Character;
import com.blackdragon2447.codex.models.Dice;
import com.blackdragon2447.codex.models.Hindrance;
import com.blackdragon2447.codex.models.Race;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public static final Logger DEBUG_LOGGER = Logger.getLogger("Codex");

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

        DEBUG_LOGGER.log(Level.INFO, "Testing Characters");

        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

        var character = new Character(
                "Test Character",
                new Character.Attributes(
                        Dice.D8, 0,
                        Dice.D6, 0,
                        Dice.D6, 0,
                        Dice.D6, 0,
                        Dice.D6, 0
                ),
                new Race(
                        "Android",
                        "Lorem Ipsum",
                        new Race.Attribute("Pacifist", "Lorem Ipsum"),
                        new Race.Attribute("Construct", "Lorem Ipsum"),
                        new Race.Attribute("Outsider", "Lorem Ipsum"),
                        new Race.Attribute("Vow", "Lorem Ipsum")
                )
        )
                .addHindrance(
                        new Hindrance("Pacifist", "Lorem Ipsum", Hindrance.HindranceType.MAYOR)
                )
                .addHindrance(
                        new Hindrance("Can't Swim", "Lorem Ipsum", Hindrance.HindranceType.MINOR)
                )
                .addHindrance(
                        new Hindrance("Vow", "Lorem Ipsum", Hindrance.HindranceType.MINOR)
                );

        System.out.println(gson.toJson(character));


        DEBUG_LOGGER.log(Level.INFO, "Done testing Characters");

        // CodexGui.start();
    }

}
