package com.blackdragon2447.codex.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.stream.Collectors;

public class TableAsEditableList extends JTable {
    private DefaultCellEditor cellEditor;
    public final DefaultTableModel model;
    public JTextField textField;

    public TableAsEditableList() {
        super(1, 1);
        this.model = (DefaultTableModel) getModel();
        init();
    }

    private void init() {
        initTable();
        initEditorComponent();
        initSelectionListener();
        initKeyListeners();
    }

    private void initTable() {
        setTableHeader(null);
    }

    private void initEditorComponent() {
        TableColumn column = getColumnModel().getColumn(0);
        textField = new JTextField();
        this.cellEditor = new DefaultCellEditor(textField);
        cellEditor.setClickCountToStart(1);
        column.setCellEditor(cellEditor);
        textField.setBorder(null);
        textField.setForeground(new Color(0, 100, 250));
    }

    private void initSelectionListener() {
        getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = getSelectedRow();
            if (selectedRow == -1) {
                return;
            }
            startEditingAtRow(selectedRow);
        });
    }

    private void initKeyListeners() {
        //enter key will insert a new row at next row index
        Action insertRowAfterCurrentRowAction = createEnterKeyAction();
        bindKeyAction(
                insertRowAfterCurrentRowAction,
                KeyEvent.VK_ENTER,
                0, this, textField);
        //enter key will insert a new row a prev row index
        Action insertRowBeforeCurrentRowAction = createAltEnterKeyAction();
        bindKeyAction(insertRowBeforeCurrentRowAction,
                KeyEvent.VK_ENTER,
                InputEvent.ALT_DOWN_MASK,
                this, textField);
        //del and backspace keys will remove the empty rows
        textField.addKeyListener(createDelKeyListener());
    }

    private void bindKeyAction(Action action, int keyCode, int acceleratorKey, JComponent... components) {
        for (JComponent component : components) {
            KeyStroke keyStroke = KeyStroke.getKeyStroke(keyCode, acceleratorKey);
            component.registerKeyboardAction(action, keyStroke, JComponent.WHEN_FOCUSED);
        }
    }

    private Action createEnterKeyAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int selectedRow = getSelectedRow();
                if (selectedRow == -1) {
                    return;
                }
                insertNewRow(selectedRow + 1);
                selectRow(selectedRow + 1);
            }
        };
    }

    private Action createAltEnterKeyAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = getSelectedRow();
                if (selectedRow == -1) {
                    return;
                }
                cellEditor.stopCellEditing();
                removeEditor();
                insertNewRow(selectedRow);
            }
        };
    }

    private KeyListener createDelKeyListener() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int selectedRow = getSelectedRow();
                if (selectedRow == -1 || getRowCount() == 1) {
                    return;
                }
                boolean emptyField = textField.getText().trim().length() == 0;
                boolean del = e.getKeyCode() == KeyEvent.VK_DELETE;
                boolean bsp = e.getKeyCode() == KeyEvent.VK_BACK_SPACE;
                if ((del || bsp) && emptyField) {
                    removeRow(selectedRow);
                    int newSel = del ? selectedRow : selectedRow - 1;
                    if (newSel == -1) {
                        newSel = 0;
                        del = true;
                    }
                    if (newSel > getRowCount() - 1) {
                        newSel = getRowCount() - 1;
                        del = false;
                    }
                    selectRow(newSel);
                    e.consume();
                    if (del) {
                        textField.setCaretPosition(0);
                    } else {
                        textField.setCaretPosition(textField.getDocument().getLength());
                    }
                }
            }
        };
    }

    private void selectRow(int selectedRow) {
        setRowSelectionInterval(selectedRow, selectedRow);
    }

    private void startEditingAtRow(int row) {
        if (isCellEditable(row, 0)) {
            editCellAt(row, 0);
            changeSelection(row, 0, false, false);
            textField.requestFocusInWindow();
        }
    }

    private void insertNewRow(int newRowIndex) {
        model.insertRow(newRowIndex, new String[]{""});
    }

    private void removeRow(int selectedRow) {
        cellEditor.stopCellEditing();
        removeEditor();
        model.removeRow(selectedRow);
    }

    public List<String> getDataAsStringList() {
        Vector<Vector> vectors = model.getDataVector();
        return vectors.stream()
                .filter(Objects::nonNull)
                .map(vector -> vector.stream()
                        .findAny()
                        .orElse(null))
                .filter(Objects::nonNull)
                .map(Objects::toString)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}
