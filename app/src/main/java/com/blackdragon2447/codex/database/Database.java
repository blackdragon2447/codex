package com.blackdragon2447.codex.database;

import com.blackdragon2447.codex.App;
import com.blackdragon2447.codex.util.Pair;
import net.harawata.appdirs.AppDirsFactory;

import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;

public class Database {

    private static boolean initialized = false;

    private static HashMap<String, Pair<Class<?>, Object>> storage;

    public static void load() throws IOException, ClassNotFoundException {
        if (!initialized) {

            String path =
                    AppDirsFactory.getInstance().getUserDataDir("codex", "", "") + "codex.db";

            if(!new File(path).exists()) {
                storage = new HashMap<>();
                initialized = true;
                save();
            } else {
                ObjectInputStream input = new ObjectInputStream(new FileInputStream(path));
                storage = (HashMap<String, Pair<Class<?>, Object>>) input.readObject();
                input.close();
                initialized = true;
            }


        } else {
            App.DEBUG_LOGGER.log(Level.WARNING, "The database has already been initialized");
        }
    }

    public static void save() throws IOException {

        if(initialized) {

            String path =
                    AppDirsFactory.getInstance().getUserDataDir("codex", "", "") + "codex.db";

            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path));
            output.writeObject(storage);
            output.close();
        } else {
            App.DEBUG_LOGGER.log(Level.WARNING, "The database has not yet been initialized");
            throw new RuntimeException("The database has not yet been initialized");
        }
    }

    public static <T> T getObject(String id, Class<T> klass) {
        if(initialized) {
            if (storage.get(id).getA() == klass) {
                return (T) storage.get(id).getB();
            } else {
                throw new RuntimeException("The item with key `" + id + "` is not of type " + klass);
            }
        } else {
            App.DEBUG_LOGGER.log(Level.WARNING, "The database has not yet been initialized");
            throw new RuntimeException("The database has not yet been initialized");
        }
    }

    public static <T> void putObject(String id, T object) {
        if(initialized) {
            storage.put(id, new Pair<>(object.getClass(), object));
        } else {
            App.DEBUG_LOGGER.log(Level.WARNING, "The database has not yet been initialized");
            throw new RuntimeException("The database has not yet been initialized");
        }
    }


}
