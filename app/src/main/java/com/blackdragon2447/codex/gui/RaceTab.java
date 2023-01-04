package com.blackdragon2447.codex.gui;

import com.blackdragon2447.codex.App;
import com.blackdragon2447.codex.database.Database;
import com.blackdragon2447.codex.database.Query;
import com.blackdragon2447.codex.models.Edge;
import com.blackdragon2447.codex.models.Race;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

public class RaceTab extends EditorPanel {

    java.util.List<Race> raceList = new ArrayList<>();

    JTextField nameField = new JTextField();

    JTextArea descriptionField = new JTextArea();

    JTextArea attributeField = new JTextArea();

    JButton saveButton = new JButton("Save");

    String oldName;

    RaceTab() {
        super();

        addListListener((e) -> {

            Race race = raceList.get(list.getSelectedIndex());

            nameField.setText(race.name());
            oldName = race.name();
            descriptionField.setText(race.description());
            attributeField.setText(race.atributes());
        });

        c.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("Name");
        c.weightx = 0.3;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.fill = GridBagConstraints.NONE;
        addEditorItem(nameLabel);

        c.weightx = 0.7;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        addEditorItem(nameField);


        JLabel descriptionLabel = new JLabel("Description");
        c.weightx = 0.3;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.fill = GridBagConstraints.NONE;
        addEditorItem(descriptionLabel);

        descriptionField.setMinimumSize(new Dimension(0, 500));
        c.weightx = 0.7;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        addEditorItem(descriptionField);


        JLabel attributesLabel = new JLabel("Attributes");
        c.weightx = 0.3;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.fill = GridBagConstraints.NONE;
        addEditorItem(attributesLabel);

        c.weightx = 0.7;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        addEditorItem(attributeField);


        c.weightx = 0.7;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        addEditorItem(saveButton);

        saveButton.addActionListener((e) -> {

            try {
                Database.execute(new Query.Delete("RACES").whereEqual("NAME", oldName).assemble());
            } catch (SQLException ex) {
                App.DEBUG_LOGGER.log(Level.WARNING, "", ex);
                System.exit(1);
            }

            try {
                Database.execute(new Query.InsertInto("RACES").addData(nameField.getText(), descriptionField.getText(), attributeField.getText()).assemble());
            } catch (SQLException ex) {
                App.DEBUG_LOGGER.log(Level.WARNING, "", ex);
                System.exit(1);
            }

            updateList();

        });

        try {
            nameField.setText(raceList.get(0).name());
            oldName = raceList.get(0).name();
            descriptionField.setText(raceList.get(0).description());
            attributeField.setText(raceList.get(0).atributes());
        } catch (IndexOutOfBoundsException ignored) {

        }

    }
    @Override
    protected DefaultListModel<String> fetchData() {
        DefaultListModel<String> listModel = new DefaultListModel<String>();

        try {
            raceList = Database.query(new Query.Select("*", "RACES").assemble(), Race.class);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            for (var r : raceList) {
                listModel.addElement(r.name());
            }

        } catch (SQLException | IOException e) {
            App.DEBUG_LOGGER.log(Level.WARNING, "", e);
            System.exit(1);
        }

        return listModel;
    }
}
