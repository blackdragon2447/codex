package com.blackdragon2447.codex.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class EditorPanel extends JSplitPane {

    protected JList<String> list;
    protected GridBagConstraints c = new GridBagConstraints();

    protected EditorPanel() {
        super(JSplitPane.HORIZONTAL_SPLIT, new JPanel(), new JPanel());
        addList();

        getLeftComponent().setMinimumSize(new Dimension(100, 50));
        getRightComponent().setMinimumSize(new Dimension(100, 50));

        getRightComponent().setBackground(Color.DARK_GRAY);

        getRightComponent().setLayout(new GridBagLayout());

    }

    protected void addEditorItem(Component component) {
        getRightComponent().add(component, c);
    }

    protected void addListListener(ListSelectionListener a){
        list.addListSelectionListener(a);
    }

    private void addList() {
        this.list = new JList<>(fetchData());
        getLeftComponent().setLayout(new BorderLayout());
        getLeftComponent().add(list, BorderLayout.CENTER);
    }

    protected void updateList() {
        this.list.setModel(fetchData());
    }

    @Override
    public JPanel getLeftComponent() {
        return (JPanel) super.getLeftComponent();
    }

    @Override
    public JPanel getRightComponent() {
        return (JPanel) super.getRightComponent();
    }

    protected abstract DefaultListModel<String> fetchData();


}
