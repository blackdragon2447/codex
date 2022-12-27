package com.blackdragon2447.codex.gui;

import com.blackdragon2447.codex.util.CodexLogger;
import com.blackdragon2447.codex.util.Themes;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class CodexGui extends JFrame {

  static JMenuBar menuBar = new JMenuBar();
  // static GridBagLayout layout = new GridBagLayout();
  static JTabbedPane pane = new JTabbedPane();

  static JPanel panel1;
  static JPanel panel2;
  static JPanel panel3;

  public static void start() {

    try {
      new CodexGui();
    } catch (Exception e) {
      CodexLogger.log(e);
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

  static { setVisual(Themes.FlatDark.getLookAndFeel()); }

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

    panel1 = new JPanel();
    pane.addTab("Tab1", panel1);

    panel2 = new JPanel();
    pane.addTab("Tab2", panel2);

    panel3 = new JPanel();
    pane.addTab("Tab3", panel3);

    add(pane);

    // JButton fillerButton = new JButton("A Filler Button");
    // c.gridx = 1;
    // c.gridy = 2;
    //
    // add(fillerButton, c);
    //
    setVisible(true);
  }
}
