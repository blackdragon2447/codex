package com.blackdragon2447.codex;

import com.blackdragon2447.codex.database.Database;
import com.blackdragon2447.codex.database.HibernateUtil;
import com.blackdragon2447.codex.gui.CodexGui;
import com.blackdragon2447.codex.models.Dice;
import com.blackdragon2447.codex.models.Edge;
import com.blackdragon2447.codex.models.Hindrance;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public static final Logger DEBUG_LOGGER = Logger.getLogger("Codex");

    public static Session databaseSession;

    public static void main(String[] args) {
        DEBUG_LOGGER.log(Level.INFO, "Starting Codex");

        DEBUG_LOGGER.log(Level.INFO, "Initializing database");

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

        // DEBUG_LOGGER.log(Level.INFO, "Testing Characters");
        //
        // Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        //
        // var character = new Character(
        //         "Test Character",
        //         new Character.Attributes(
        //                 Dice.D8, 0,
        //                 Dice.D6, 0,
        //                 Dice.D6, 0,
        //                 Dice.D6, 0,
        //                 Dice.D6, 0
        //         ),
        //         new Race(
        //                 "Android",
        //                 "Lorem Ipsum",
        //                 new Race.Attribute("Pacifist", "Lorem Ipsum"),
        //                 new Race.Attribute("Construct", "Lorem Ipsum"),
        //                 new Race.Attribute("Outsider", "Lorem Ipsum"),
        //                 new Race.Attribute("Vow", "Lorem Ipsum")
        //         )
        // )
        //         .addHindrance(
        //                 new Hindrance("Pacifist", "Lorem Ipsum", Hindrance.HindranceType.MAYOR)
        //         )
        //         .addHindrance(
        //                 new Hindrance("Can't Swim", "Lorem Ipsum", Hindrance.HindranceType.MINOR)
        //         )
        //         .addHindrance(
        //                 new Hindrance("Vow", "Lorem Ipsum", Hindrance.HindranceType.MINOR)
        //         );
        //
        // System.out.println(gson.toJson(character));
        //
        //
        // DEBUG_LOGGER.log(Level.INFO, "Done testing Characters");

        databaseSession = HibernateUtil.getSessionJavaConfigFactory().openSession();

        CodexGui.start();

    }

}
