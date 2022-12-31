package com.blackdragon2447.codex.database;

import com.blackdragon2447.codex.App;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import net.harawata.appdirs.AppDirsFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Database {

    private static boolean initialized = false;
    private static Connection connection = null;

    public static void initialize() throws SQLException {

        if (!initialized) {

            String path =
                    AppDirsFactory.getInstance().getUserDataDir("codex", "", "");
            String databaseUrl = "jdbc:h2:" + path + "codex";

            connection = DriverManager.getConnection(databaseUrl);

            initialized = true;

        } else {
            App.DEBUG_LOGGER.log(Level.WARNING, "The database has already been initialized");
        }
    }

    public static boolean execute(String query) throws SQLException {
        Statement statement = connection.createStatement();
         return statement.execute(query);
    }

    public static <T> List<T> query(String query, Class<T> type) throws SQLException, IOException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        List<T> list = new ArrayList<>();

        while (resultSet.next()) {
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = new JsonWriter(new BufferedWriter(stringWriter));

            writer.beginObject();
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                writer.name(resultSetMetaData.getColumnLabel(i).toLowerCase());
                writer.value(resultSet.getString(i));
            }
            writer.endObject();

            writer.close();

            Gson gson = new GsonBuilder().create();
            list.add(gson.fromJson(stringWriter.toString(), type));
        }

        return list;

    }

}
