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

/**
 * The {@link Database} class acts as a static interface
 * between the runtime database contained within it
 * and the rest of the app.
 */
public class Database {

    private static boolean initialized = false;

    private static HashMap<String, Character> characters;
    private static HashMap<String, Edge> edges;
    private static HashMap<String, Race> races;

    /**
     * Loads the database in case it has not been loaded,
     * if it has already been loaded it simply prints an
     * errot message and returns.
     * 
     * @throws IOException            when one of the database files does not exist
     *                                and cannot be created.
     * @throws ClassNotFoundException if the class of the serialized database is not
     *                                found, this should never happen
     */
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

    /**
     * Writes the internal database out to disk
     *
     * @throws IOException if one or more of the files could not be created or
     *                     accessed.
     */
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

    /**
     * @param id the id of the requested
     *           {@link com.blackdragon2447.codex.models.Character}
     * @return the requested {@link com.blackdragon2447.codex.models.Character} or
     *         null if there is no character for the given id.
     */
    public static Character getCharacter(String id) {
        return characters.get(id);
    }

    /**
     * @param id the id of the requested
     *           {@link com.blackdragon2447.codex.models.Edge}
     * @return the requested {@link com.blackdragon2447.codex.models.Edge} or
     *         null if there is no edge for the given id.
     */
    public static Edge getEdge(String id) {
        return edges.get(id);
    }

    /**
     * @param id the id of the requested
     *           {@link com.blackdragon2447.codex.models.Race}
     * @return the requested {@link com.blackdragon2447.codex.models.Race} or
     *         null if there is no race for the given id.
     */
    public static Race getRace(String id) {
        return races.get(id);
    }

    /**
     * @param id        the id of the
     *                  {@link com.blackdragon2447.codex.models.Character} to put in
     *                  the database
     * @param character the {@link com.blackdragon2447.codex.models.Character} to
     *                  put in the database
     */
    public static void putCharacter(String id, Character character) {
        characters.put(id, character);
    }

    /**
     * @param id        the id of the
     *                  {@link com.blackdragon2447.codex.models.Edge} to put in
     *                  the database
     * @param character the {@link com.blackdragon2447.codex.models.Edge} to
     *                  put in the database
     */
    public static void putEdge(String id, Edge edge) {
        edges.put(id, edge);
    }

    /**
     * @param id        the id of the
     *                  {@link com.blackdragon2447.codex.models.Race} to put in
     *                  the database
     * @param character the {@link com.blackdragon2447.codex.models.Race} to
     *                  put in the database
     */
    public static void putRace(String id, Race race) {
        races.put(id, race);
    }

}
