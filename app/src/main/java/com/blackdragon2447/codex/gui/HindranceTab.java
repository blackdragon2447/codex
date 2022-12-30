package com.blackdragon2447.codex.gui;

import com.blackdragon2447.codex.App;
import com.blackdragon2447.codex.database.Database;
import com.blackdragon2447.codex.database.Query;
import com.blackdragon2447.codex.models.Hindrance;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

public class HindranceTab extends JSplitPane {

    static JPanel hindrancesList = new JPanel();
    static JPanel hindrancesEditor = new JPanel();

    JTextField nameField = new JTextField();
    JTextArea descriptionField = new JTextArea();
    String[] typeList = {"Mayor", "Minor"};
    JComboBox<String> typeField = new JComboBox<String>(new DefaultComboBoxModel<>(typeList));
    JButton saveButton = new JButton("Save");

    java.util.List<Hindrance> hindranceList;

    public HindranceTab() {

        super(JSplitPane.HORIZONTAL_SPLIT, hindrancesList, hindrancesEditor);

        DefaultListModel<String> listModel = new DefaultListModel<String>();


        try {
            hindranceList = Database.query(new Query.Select("*", "HINDRANCES").assemble(), Hindrance.class);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            for (var r : hindranceList) {
                listModel.addElement(r.name());
            }

        } catch (SQLException | IOException e) {
            App.DEBUG_LOGGER.log(Level.WARNING, "", e);
            System.exit(1);
        }

        JList<String> hindranceListModel = new JList<>(listModel);

        hindranceListModel.addListSelectionListener((e) -> {
            System.out.println(hindranceListModel.getSelectedIndex());

            Hindrance hindrance = hindranceList.get(hindranceListModel.getSelectedIndex());

            nameField.setText(hindrance.name());
            descriptionField.setText(hindrance.description());
            typeField.setSelectedIndex(switch (hindrance.type()) {
                case MAYOR -> 0;
                case MINOR -> 1;
            });
        });

        saveButton.addActionListener((e) -> {
            System.out.println("Saved!");
            Hindrance oldHindrance = hindranceList.get(hindranceListModel.getSelectedIndex());
            Hindrance hindrance = new Hindrance(
                    nameField.getText(),
                    descriptionField.getText(),
                    switch (typeField.getSelectedIndex()) {
                        case 0 -> Hindrance.HindranceType.MAYOR;
                        case 1 -> Hindrance.HindranceType.MINOR;
                        default -> throw new IllegalStateException("Unexpected value: " + typeField.getSelectedIndex());
                    });


        });

        hindrancesList.setMinimumSize(new Dimension(100, 50));
        hindrancesEditor.setMinimumSize(new Dimension(100, 50));

        hindrancesList.setLayout(new BorderLayout());
        hindrancesList.add(hindranceListModel, BorderLayout.CENTER);

        hindrancesEditor.setBackground(Color.DARK_GRAY);

        //-----------------------------------------------

        hindrancesEditor.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("Name");
        c.weightx = 0.3;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        //c.gridheight = 10;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.fill = GridBagConstraints.NONE;
        hindrancesEditor.add(nameLabel, c);

        c.weightx = 0.7;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        //c.gridheight = 10;
        c.anchor = GridBagConstraints.PAGE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        hindrancesEditor.add(nameField, c);


        JLabel descriptionLabel = new JLabel("Description");
        c.weightx = 0.3;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        //c.gridheight = 200;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.fill = GridBagConstraints.NONE;
        hindrancesEditor.add(descriptionLabel, c);

        descriptionField.setMaximumSize(new Dimension(0, 150));
        c.weightx = 0.7;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        hindrancesEditor.add(descriptionField, c);


        JLabel typeLabel = new JLabel("Type");
        c.weightx = 0.3;
        c.weighty = 0.5;
        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.fill = GridBagConstraints.NONE;
        hindrancesEditor.add(typeLabel, c);

        c.weightx = 0.7;
        c.weighty = 0.0;
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        hindrancesEditor.add(typeField, c);

        c.weightx = 0.7;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        hindrancesEditor.add(saveButton, c);

        nameField.setText(hindranceList.get(0).name());
        descriptionField.setText(hindranceList.get(0).description());
        typeField.setSelectedIndex(switch (hindranceList.get(0).type()) {
            case MAYOR -> 0;
            case MINOR -> 1;
        });

        //---------------------------------------------
    }

}
