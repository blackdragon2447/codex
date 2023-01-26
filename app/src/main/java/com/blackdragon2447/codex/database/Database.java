package com.blackdragon2447.codex.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.logging.Level;

import com.blackdragon2447.codex.App;
import com.blackdragon2447.codex.models.Edge;
import com.blackdragon2447.codex.models.Race;
import net.harawata.appdirs.AppDirsFactory;

public class Database {

    private static boolean initialized = false;

    private static HashMap<String, Character> characters;
    private static HashMap<String, Edge> edges;
    private static HashMap<String, Race> races;

    public static void load() throws IOException, ClassNotFoundException {
        if (!initialized) {

            String path = AppDirsFactory.getInstance().getUserDataDir("codex", "", "");

            if (!new File(path + "characters.db").exists()) {
                characters = new HashMap<>();
            } else {
                ObjectInputStream input = new ObjectInputStream(new FileInputStream(path + "characters.db"));
                characters = (HashMap<String, Character>) input.readObject();
                input.close();
            }

            if (!new File(path + "edges.db").exists()) {
                characters = new HashMap<>();
            } else {
                ObjectInputStream input = new ObjectInputStream(new FileInputStream(path + "edges.db"));
                edges = (HashMap<String, Edge>) input.readObject();
                input.close();
            }

            if (!new File(path + "races.db").exists()) {
                characters = new HashMap<>();
            } else {
                ObjectInputStream input = new ObjectInputStream(new FileInputStream(path + "races.db"));
                races = (HashMap<String, Race>) input.readObject();
                input.close();
            }

            new Thread() {
                @Override
                public void run() {
                    try {
                        Database.save();
                        sleep(60_000);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }.start();

        } else {
            App.DEBUG_LOGGER.log(Level.WARNING, "The database has already been initialized");
        }
    }

    public static void save() throws IOException {

        if (initialized) {

            String path = AppDirsFactory.getInstance().getUserDataDir("codex", "", "");

            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path + "characters.db"));
            output.writeObject(characters);
            output.close();

            output = new ObjectOutputStream(new FileOutputStream(path + "edges.db"));
            output.writeObject(edges);
            output.close();

            output = new ObjectOutputStream(new FileOutputStream(path + "races.db"));
            output.writeObject(races);
            output.close();

        } else {
            App.DEBUG_LOGGER.log(Level.WARNING, "The database has not yet been initialized");
            throw new RuntimeException("The database has not yet been initialized");
        }
    }

    public static Character getCharacter(String id) {
        return characters.get(id);
    }

    public static Edge getEdge(String id) {
        return edges.get(id);
    }

    public static Race getRace(String id) {
        return races.get(id);
    }
}
