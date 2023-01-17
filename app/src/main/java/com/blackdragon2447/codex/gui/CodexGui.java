package com.blackdragon2447.codex.gui;

import com.blackdragon2447.codex.App;
import com.blackdragon2447.codex.database.Database;
import com.blackdragon2447.codex.database.HibernateUtil;
import com.blackdragon2447.codex.database.Query;
import com.blackdragon2447.codex.models.Hindrance;
import com.blackdragon2447.codex.util.Themes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

public class CodexGui extends JFrame {

    static JMenuBar menuBar = new JMenuBar();
    static GridBagLayout hindrancesPanelLayout = new GridBagLayout();
    static JTabbedPane pane = new JTabbedPane();

    static RaceTab racePane;
    static HindranceTab hindrancesPane;

    static EdgeTab edgesPane;

    public static void start() {

        try {
            new CodexGui();
        } catch (Exception e) {
            App.DEBUG_LOGGER.log(Level.SEVERE, "", e);
        }
    }

    public static void setVisual(LookAndFeel lookAndFeel) {
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(pane);
        SwingUtilities.updateComponentTreeUI(menuBar);
    }

    static {
        setVisual(Themes.FlatDark.getLookAndFeel());
    }

    public CodexGui() {

        setTitle("Codex");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);

        // setVisual(Themes.FlatDark.getLookAndFeel());
        getContentPane().setBackground(Color.DARK_GRAY);

        // setLayout(layout);
        // GridBagConstraints c = new GridBagConstraints();

        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem settingsMenuItem = new JMenuItem("Settings");
        fileMenu.add(settingsMenuItem);
        settingsMenuItem.addActionListener((e) -> System.out.println("Clicked"));

        racePane = new RaceTab();
        pane.addTab("Races", racePane);

        hindrancesPane = new HindranceTab();

        pane.addTab("Hindrances", hindrancesPane);


        edgesPane = new EdgeTab();
        pane.addTab("Edges", edgesPane);

        add(pane);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                HibernateUtil.getSessionJavaConfigFactory().close();
            }
        });

        setVisible(true);
    }

}
