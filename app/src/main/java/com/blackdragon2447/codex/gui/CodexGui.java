package com.blackdragon2447.codex.gui;

import com.blackdragon2447.codex.util.CodexLogger;
import com.blackdragon2447.codex.util.Themes;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class CodexGui extends JFrame {

  static JMenuBar menuBar = new JMenuBar();
  static GridBagLayout layout = new GridBagLayout();

  public static void start() {

    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          new CodexGui();
        } catch (Exception e) {
          CodexLogger.log(e);
        }
      }
    });
  }

  public static void setVisual(LookAndFeel lookAndFeel) {
    try {
      UIManager.setLookAndFeel(lookAndFeel);
    } catch (UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }

  public CodexGui() {

    setTitle("Codex");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setBounds(100, 100, 700, 500);

    setVisual(Themes.FlatDark.getLookAndFeel());
    getContentPane().setBackground(Color.DARK_GRAY);

    setLayout(layout);
    GridBagConstraints c = new GridBagConstraints();

    setJMenuBar(menuBar);
    JMenu fileMenu = new JMenu("File");
    menuBar.add(fileMenu);
    JMenuItem settingsMenuItem = new JMenuItem("Settings");
    fileMenu.add(settingsMenuItem);
    settingsMenuItem.addActionListener((e) -> System.out.println("Clicked"));

    JButton fillerButton = new JButton("A Filler Button");
    c.gridx = 1;
    c.gridy = 2;

    add(fillerButton, c);

    setVisible(true);
  }
}
