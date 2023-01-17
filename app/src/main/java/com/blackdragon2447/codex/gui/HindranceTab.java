package com.blackdragon2447.codex.gui;

import com.blackdragon2447.codex.App;
import com.blackdragon2447.codex.database.Database;
import com.blackdragon2447.codex.database.HibernateUtil;
import com.blackdragon2447.codex.database.Query;
import com.blackdragon2447.codex.models.Hindrance;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.Session;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

public class HindranceTab extends EditorPanel {

    java.util.List<Hindrance> hindranceList;


    JTextField nameField = new JTextField();
    JTextArea descriptionField = new JTextArea();
    String[] typeList = {"Mayor", "Minor"};
    JComboBox<String> typeField = new JComboBox<String>(new DefaultComboBoxModel<>(typeList));
    JButton saveButton = new JButton("Save");

    HindranceTab() {
        super();

        addListListener((e) -> {

            Hindrance hindrance = hindranceList.get(list.getSelectedIndex());

            nameField.setText(hindrance.name());
            descriptionField.setText(hindrance.description());
            typeField.setSelectedIndex(switch (hindrance.type()) {
                case MAYOR -> 0;
                case MINOR -> 1;
            });
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

        descriptionField.setMaximumSize(new Dimension(0, 150));
        c.weightx = 0.7;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        addEditorItem(descriptionField);


        JLabel typeLabel = new JLabel("Type");
        c.weightx = 0.3;
        c.weighty = 0.5;
        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.fill = GridBagConstraints.NONE;
        addEditorItem(typeLabel);

        c.weightx = 0.7;
        c.weighty = 0.0;
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        addEditorItem(typeField);

        c.weightx = 0.7;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        addEditorItem(saveButton);

        saveButton.addActionListener((e) -> {

            Hindrance hindrance = new Hindrance(nameField.getText(), descriptionField.getText(), switch (typeField.getSelectedIndex()) {
                case 0 -> Hindrance.HindranceType.MAYOR;
                case 1 -> Hindrance.HindranceType.MINOR;
                default -> throw new IllegalStateException("Unexpected value: " + typeField.getSelectedIndex());
            });

            hindrance.setId(hindranceList.get(list.getSelectedIndex()).getId());

            App.databaseSession.beginTransaction();
            App.databaseSession.merge(hindrance);
            App.databaseSession.getTransaction().commit();

            updateList();

        });

        nameField.setText(hindranceList.get(0).name());
        descriptionField.setText(hindranceList.get(0).description());
        typeField.setSelectedIndex(switch (hindranceList.get(0).type()) {
            case MAYOR -> 0;
            case MINOR -> 1;
        });

    }

    @Override
    protected DefaultListModel<String> fetchData() {

        DefaultListModel<String> listModel = new DefaultListModel<String>();

        App.databaseSession.beginTransaction();
        hindranceList = HibernateUtil.loadAllData(Hindrance.class, App.databaseSession);
        App.databaseSession.getTransaction().commit();

        for (var r : hindranceList) {
            listModel.addElement(r.name());
        }

        return listModel;
    }
}
