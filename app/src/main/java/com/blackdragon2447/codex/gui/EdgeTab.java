package com.blackdragon2447.codex.gui;

import com.blackdragon2447.codex.App;
import com.blackdragon2447.codex.database.Database;
import com.blackdragon2447.codex.database.Query;
import com.blackdragon2447.codex.models.Edge;
import com.blackdragon2447.codex.models.Hindrance;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

public class EdgeTab extends EditorPanel {

    java.util.List<Edge> edgeList;

    JTextField nameField = new JTextField();

    JTextField requirementsField = new JTextField();

    JTextArea descriptionField = new JTextArea();

    JButton saveButton = new JButton("Save");

    String oldName;

    EdgeTab() {
        super();

        addListListener((e) -> {

            Edge edge = edgeList.get(list.getSelectedIndex());

            nameField.setText(edge.name());
            oldName = edge.name();
            requirementsField.setText(edge.requirements());
            descriptionField.setText(edge.description());
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


        JLabel typeLabel = new JLabel("Requirements");
        c.weightx = 0.3;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.fill = GridBagConstraints.NONE;
        addEditorItem(typeLabel);

        c.weightx = 0.7;
        c.weighty = 0.0;
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        addEditorItem(requirementsField);


        JLabel descriptionLabel = new JLabel("Description");
        c.weightx = 0.3;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.fill = GridBagConstraints.NONE;
        addEditorItem(descriptionLabel);

        descriptionField.setMaximumSize(new Dimension(0, 150));
        c.weightx = 0.7;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        addEditorItem(descriptionField);


        c.weightx = 0.7;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        addEditorItem(saveButton);

        saveButton.addActionListener((e) -> {

            try {
                Database.execute(new Query.Delete("EDGES").whereEqual("NAME", oldName).assemble());
            } catch (SQLException ex) {
                App.DEBUG_LOGGER.log(Level.WARNING, "", ex);
                System.exit(1);
            }

            try {
                Database.execute(new Query.InsertInto("EDGES").addData(nameField.getText(), requirementsField.getText(), descriptionField.getText()).assemble());
            } catch (SQLException ex) {
                App.DEBUG_LOGGER.log(Level.WARNING, "", ex);
                System.exit(1);
            }

            updateList();

        });

        try {
            nameField.setText(edgeList.get(0).name());
            oldName = edgeList.get(0).name();
            requirementsField.setText(edgeList.get(0).requirements());
            descriptionField.setText(edgeList.get(0).description());
        } catch (IndexOutOfBoundsException ignored) {

        }

    }

    @Override
    protected DefaultListModel<String> fetchData() {
        DefaultListModel<String> listModel = new DefaultListModel<String>();

        try {
            edgeList = Database.query(new Query.Select("*", "EDGES").assemble(), Edge.class);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            for (var e : edgeList) {
                listModel.addElement(e.name());
            }

        } catch (SQLException | IOException e) {
            App.DEBUG_LOGGER.log(Level.WARNING, "", e);
            System.exit(1);
        }

        return listModel;
    }
}
