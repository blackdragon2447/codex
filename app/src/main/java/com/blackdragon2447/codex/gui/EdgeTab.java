package com.blackdragon2447.codex.gui;

import com.blackdragon2447.codex.App;
import com.blackdragon2447.codex.database.Database;
import com.blackdragon2447.codex.database.Query;
import com.blackdragon2447.codex.models.Edge;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class EdgeTab extends EditorPanel {

    java.util.List<Edge> edgeList;

    JTextField nameField = new JTextField();

    JTextField requirementsField = new JTextField();

    JTextArea descriptionField = new JTextArea();

    JButton saveButton = new JButton("Save");


    EdgeTab() {
        super();

        addListListener((e) -> {

            Edge edge = edgeList.get(list.getSelectedIndex());

            nameField.setText(edge.name());
            requirementsField.setText(String.join(", ", edge.requirements()));
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
        c.weighty = 0.0;
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

            Edge edge = new Edge(nameField.getText(), Arrays.stream(requirementsField.getText().split(", ")).toList(), descriptionField.getText());

            edge.setId(edgeList.get(list.getSelectedIndex()).getId());

            App.databaseSession.beginTransaction();
            App.databaseSession.merge(edge);
            App.databaseSession.getTransaction().commit();

            updateList();

        });

        try {
            nameField.setText(edgeList.get(0).name());
            requirementsField.setText(String.join(", ", edgeList.get(0).requirements()));
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

    private Vector<Vector<String>> strArray2Vector(String[] str) {
        Vector<Vector<String>> vector = new Vector<>();
        for (String s : str) {
            Vector<String> v = new Vector<>();
            v.addElement(s);
            vector.addElement(v);
        }
        return vector;
    }
}
