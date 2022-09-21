package com.blackdragon2447.codex.gui;

import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import java.awt.EventQueue;

import com.blackdragon2447.codex.util.CodexLogger;
import com.blackdragon2447.codex.util.Themes;

public class CodexGui extends JFrame {

  public static void start() {
   
    EventQueue.invokeLater(
      new Runnable() {
        @Override
        public void run() {
          try {
            new CodexGui();
          } catch (Exception e) {
            CodexLogger.log(e);
          }
        }
      }
    );
  }

  public CodexGui() {

    setTitle("Codex");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);

    setVisual(Themes.FlatDark.getLookAndFeel());
    setVisible(true);
  }

  public static void setVisual(LookAndFeel lookAndFeel) {
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

	}

}
