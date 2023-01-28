package com.blackdragon2447.codex.gui;

import com.blackdragon2447.codex.App;
import com.blackdragon2447.codex.database.Database;
import com.blackdragon2447.codex.util.Themes;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import javax.swing.*;

public class CodexGui extends JFrame
{

  static JMenuBar menuBar = new JMenuBar();

  BorderLayout layout = new BorderLayout();

  JPanel leftPanel = new JPanel();
  JPanel infoPanel = new JPanel();
  JLabel nameLabel = new JLabel("Name", SwingConstants.CENTER);
  JLabel benniesLabel = new JLabel("Bennies", SwingConstants.CENTER);
  JLabel raceLabel = new JLabel("Race", SwingConstants.CENTER);

  JButton powersButton = new JButton("Powers");
  JButton weaponsButton = new JButton("Weapons");
  JButton gearButton = new JButton("Gear");

  JPanel centerPanel = new JPanel();

  JTabbedPane rightPanel = new JTabbedPane();

  public static void start()
  {
    try {
      new CodexGui();
    } catch (Exception e) {
      App.DEBUG_LOGGER.log(Level.SEVERE, "", e);
    }
  }

  public static void setVisual(LookAndFeel lookAndFeel)
  {
    try {
      UIManager.setLookAndFeel(lookAndFeel);
    } catch (UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
    SwingUtilities.updateComponentTreeUI(menuBar);
  }

  static { setVisual(Themes.FlatDark.getLookAndFeel()); }

  public CodexGui()
  {

    setTitle("Codex");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setBounds(100, 100, 700, 500);

    // getContentPane().setBackground(Color.DARK_GRAY);

    setLayout(layout);

    setJMenuBar(menuBar);
    JMenu fileMenu = new JMenu("File");
    menuBar.add(fileMenu);
    JMenuItem settingsMenuItem = new JMenuItem("Settings");
    fileMenu.add(settingsMenuItem);
    settingsMenuItem.addActionListener((e) -> System.out.println("Clicked"));

    {
      leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
      {
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        nameLabel.setMaximumSize(
          new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD, 24.0F));
        infoPanel.add(nameLabel);

        benniesLabel.setMaximumSize(
          new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        benniesLabel.setFont(
          benniesLabel.getFont().deriveFont(Font.BOLD, 16.0F));
        infoPanel.add(benniesLabel);

        raceLabel.setMaximumSize(
          new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        raceLabel.setFont(raceLabel.getFont().deriveFont(Font.BOLD, 16.0F));
        infoPanel.add(raceLabel);
      }
      infoPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
      leftPanel.add(infoPanel);

      powersButton.setFont(powersButton.getFont().deriveFont(16.0F));
      powersButton.setMaximumSize(
        new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
      leftPanel.add(powersButton);

      weaponsButton.setFont(weaponsButton.getFont().deriveFont(16.0F));
      weaponsButton.setMaximumSize(
        new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
      leftPanel.add(weaponsButton);

      gearButton.setFont(gearButton.getFont().deriveFont(16.0F));
      gearButton.setMaximumSize(
        new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
      leftPanel.add(gearButton);
    }

    leftPanel.setBackground(new Color(255, 0, 0));
    leftPanel.setPreferredSize(new Dimension(300, 100));
    add(leftPanel, BorderLayout.LINE_START);

    centerPanel.setBackground(new Color(0, 255, 0));
    add(centerPanel, BorderLayout.CENTER);

    {
      rightPanel.setBackground(new Color(0, 0, 255));
      rightPanel.setPreferredSize(new Dimension(500, 100));
    }
    add(rightPanel, BorderLayout.LINE_END);

    addWindowListener(new WindowAdapter() {
      @Override public void windowClosing(WindowEvent e)
      {
        try {
          Database.save();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    });

    setVisible(true);
  }
}
