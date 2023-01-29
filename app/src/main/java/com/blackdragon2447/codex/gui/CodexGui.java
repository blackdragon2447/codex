package com.blackdragon2447.codex.gui;

import com.blackdragon2447.codex.App;
import com.blackdragon2447.codex.database.Database;
import com.blackdragon2447.codex.models.Character;
import com.blackdragon2447.codex.models.Die;
import com.blackdragon2447.codex.models.Skill;
import com.blackdragon2447.codex.util.Themes;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class CodexGui extends JFrame {

    static JMenuBar menuBar = new JMenuBar();

    BorderLayout layout = new BorderLayout();

    JPanel leftPanel = new JPanel();
    JPanel infoPanel = new JPanel();
    JLabel nameLabel = new JLabel("Name", SwingConstants.CENTER);
    JLabel benniesLabel = new JLabel("Bennies", SwingConstants.CENTER);
    JLabel raceLabel = new JLabel("Race", SwingConstants.CENTER);
    JLabel sizeLabel = new JLabel("Size", SwingConstants.CENTER);

    JButton powersButton = new JButton("Powers");
    JButton weaponsButton = new JButton("Weapons");
    JButton gearButton = new JButton("Gear");

    JPanel centerPanel = new JPanel();
    JPanel innerCenterPanel = new JPanel();

    JLabel paceLabel = new JLabel("Pace");
    JSpinner paceSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
    JPanel pacePanel = createLabeledSpinner(paceLabel, paceSpinner);

    JLabel parryLabel = new JLabel("Parry");
    JSpinner parrySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
    JPanel parryPanel = createLabeledSpinner(parryLabel, parrySpinner);

    JLabel toughnessLabel = new JLabel("Toughness");
    JSpinner toughnessSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
    JPanel toughnessPanel = createLabeledSpinner(toughnessLabel, toughnessSpinner);

    JLabel agilityLabel = new JLabel("Agility");
    JComboBox<Die> agilityComboBox = new JComboBox<>(Die.values());
    JSpinner agilityBonusSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
    JPanel agilityPanel = createDieCombobox(agilityLabel, agilityComboBox, agilityBonusSpinner);

    JLabel smartsLabel = new JLabel("Smarts");
    JComboBox<Die> smartsComboBox = new JComboBox<>(Die.values());
    JSpinner smartsBonusSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
    JPanel smartsPanel = createDieCombobox(smartsLabel, smartsComboBox, smartsBonusSpinner);

    JLabel spiritLabel = new JLabel("Spirit");
    JComboBox<Die> spiritComboBox = new JComboBox<>(Die.values());
    JSpinner spiritBonusSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
    JPanel spiritPanel = createDieCombobox(spiritLabel, spiritComboBox, spiritBonusSpinner);

    JLabel strengthLabel = new JLabel("Strength");
    JComboBox<Die> strengthComboBox = new JComboBox<>(Die.values());
    JSpinner strengthBonusSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
    JPanel strengthPanel = createDieCombobox(strengthLabel, strengthComboBox, strengthBonusSpinner);

    JLabel vigorLabel = new JLabel("Vigor");
    JComboBox<Die> vigorComboBox = new JComboBox<>(Die.values());
    JSpinner vigorBonusSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
    JPanel vigorPanel = createDieCombobox(vigorLabel, vigorComboBox, vigorBonusSpinner);

    JTabbedPane rightPanel = new JTabbedPane();
    JPanel skillPanel = new JPanel();

    JPanel hindrancePanel = new JPanel();
    JPanel egdePanel = new JPanel();

    Character character;

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
        SwingUtilities.updateComponentTreeUI(menuBar);
    }

    static {
        setVisual(Themes.FlatDark.getLookAndFeel());
    }

    public CodexGui() {

        setTitle("Codex");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);

        // getContentPane().setBackground(Color.DARK_GRAY);

        setLayout(layout);

        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("Character");
        menuBar.add(fileMenu);
        JMenu openMenu = new JMenu("Open");
        fileMenu.add(openMenu);

        for (Map.Entry<String, Character> c : Database.getCharacters().entrySet()) {
            JMenuItem characterItem = new JMenuItem(c.getValue().getName());
            System.out.println(c.getKey());
            characterItem.addActionListener((e) -> {
                this.character = c.getValue();
                this.populate();
            });
            openMenu.add(characterItem);
        }

        {
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
            Border border = BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK);
            leftPanel.setBorder(border);
            {
                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

                nameLabel.setMaximumSize(
                        new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
                nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD, 16.0F));
                infoPanel.add(nameLabel);

                benniesLabel.setMaximumSize(
                        new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
                benniesLabel.setFont(
                        benniesLabel.getFont().deriveFont(Font.BOLD, 12.0F));
                infoPanel.add(benniesLabel);

                raceLabel.setMaximumSize(
                        new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
                raceLabel.setFont(raceLabel.getFont().deriveFont(Font.BOLD, 12.0F));
                infoPanel.add(raceLabel);

                sizeLabel.setMaximumSize(
                        new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
                sizeLabel.setFont(raceLabel.getFont().deriveFont(Font.BOLD, 12.0F));
                infoPanel.add(sizeLabel);

            }
            infoPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
            leftPanel.add(infoPanel);

            powersButton.setFont(powersButton.getFont().deriveFont(12.0F));
            powersButton.setMaximumSize(
                    new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
            powersButton.addActionListener((e) -> notYetImplemented());
            leftPanel.add(powersButton);

            weaponsButton.setFont(weaponsButton.getFont().deriveFont(12.0F));
            weaponsButton.setMaximumSize(
                    new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
            weaponsButton.addActionListener((e) -> notYetImplemented());
            leftPanel.add(weaponsButton);

            gearButton.setFont(gearButton.getFont().deriveFont(12.0F));
            gearButton.setMaximumSize(
                    new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
            gearButton.addActionListener((e) -> notYetImplemented());
            leftPanel.add(gearButton);
        }

        leftPanel.setPreferredSize(new Dimension(200, 100));
        add(leftPanel, BorderLayout.LINE_START);

        {
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
            {
                GroupLayout layout = new GroupLayout(innerCenterPanel);
                innerCenterPanel.setLayout(layout);
                layout.setAutoCreateGaps(true);
                layout.setAutoCreateContainerGaps(true);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(pacePanel)
                                        .addComponent(parryPanel)
                                        .addComponent(toughnessPanel))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(agilityPanel)
                                        .addComponent(smartsPanel)
                                        .addComponent(spiritPanel))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(strengthPanel)
                                        .addComponent(vigorPanel)));

                layout.setVerticalGroup(
                        layout.createSequentialGroup()
                                .addGroup(
                                        layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                                .addComponent(pacePanel)
                                                .addComponent(parryPanel)
                                                .addComponent(toughnessPanel))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(agilityPanel)
                                        .addComponent(smartsPanel)
                                        .addComponent(spiritPanel))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(strengthPanel)
                                        .addComponent(vigorPanel)));

            }
            Dimension minSize = new Dimension(5, 0);
            Dimension prefSize = new Dimension(5, 100);
            Dimension maxSize = new Dimension(100, Short.MAX_VALUE);

            paceSpinner.addChangeListener((c) -> {
                CodexGui.this.character.setPace((int) paceSpinner.getValue());
                System.out.println((int) paceSpinner.getValue());
                CodexGui.this.saveCharacter();
            });

            centerPanel.add(new Box.Filler(minSize, prefSize, maxSize));
            centerPanel.add(innerCenterPanel);
            centerPanel.add(new Box.Filler(minSize, prefSize, maxSize));

        }
        // centerPanel.setBackground(new Color(0, 255, 0));
        add(centerPanel, BorderLayout.CENTER);

        {
            Border border = BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK);
            rightPanel.setBorder(border);

            rightPanel.addTab("Skills", skillPanel);
            skillPanel.setLayout(new BoxLayout(skillPanel, BoxLayout.Y_AXIS));

            rightPanel.addTab("hindrances", hindrancePanel);
            rightPanel.addTab("Edges and Advances", egdePanel);
        }
        rightPanel.setPreferredSize(new Dimension(310, 100));
        add(rightPanel, BorderLayout.LINE_END);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Database.save();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private void populate() {
        this.nameLabel.setText(this.character.getName());

        this.raceLabel.setText("Race: " + this.character.getRace().getName());
        this.sizeLabel.setText("Size: " + this.character.getSize());

        this.paceSpinner.getModel().setValue(this.character.getPace());
        this.parrySpinner.getModel().setValue(this.character.getParry());
        this.toughnessSpinner.getModel().setValue(this.character.getToughness());

        this.agilityComboBox.setSelectedItem(this.character.getAgilityAttr().getDie());
        this.agilityBonusSpinner.getModel().setValue(this.character.getAgilityAttr().getBonus());

        this.smartsComboBox.setSelectedItem(this.character.getSmartsAttr().getDie());
        this.smartsBonusSpinner.getModel().setValue(this.character.getSmartsAttr().getBonus());

        this.spiritComboBox.setSelectedItem(this.character.getSpiritAttr().getDie());
        this.spiritBonusSpinner.getModel().setValue(this.character.getSpiritAttr().getBonus());

        this.strengthComboBox.setSelectedItem(this.character.getStrengthAttr().getDie());
        this.strengthBonusSpinner.getModel().setValue(this.character.getStrengthAttr().getBonus());

        this.vigorComboBox.setSelectedItem(this.character.getVigorAttr().getDie());
        this.vigorBonusSpinner.getModel().setValue(this.character.getVigorAttr().getBonus());

        this.skillPanel.removeAll();

        this.skillPanel.add(createSkillPanel("Athletics", this.character.getAthleticsSkill()));
        this.skillPanel.add(createSkillPanel("CommonKnowledge", this.character.getCommonKnowledgeSkill()));
        this.skillPanel.add(createSkillPanel("Notice", this.character.getNoticeSkill()));
        this.skillPanel.add(createSkillPanel("Persuasion", this.character.getPersuasionSkill()));
        this.skillPanel.add(createSkillPanel("Stealth", this.character.getStealthSkill()));

        this.getContentPane().revalidate();
        this.getContentPane().repaint();

    }

    private void notYetImplemented() {
        JOptionPane.showMessageDialog(this, "Not yet implemented");
    }

    private void saveCharacter() {
        Database.putCharacter(this.character.getSystemName(), character);
        try {
            Database.save();
        } catch (IOException e) {
        }
    }

    private JPanel createSkillPanel(String name, Skill skill) {
        JPanel panel = new JPanel();

        // panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(new JLabel(name), BorderLayout.LINE_START);

        JComboBox<Die> dieCombobox = new JComboBox<>(Die.values());
        panel.add(dieCombobox, BorderLayout.CENTER);

        JSpinner bonusSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        panel.add(bonusSpinner, BorderLayout.LINE_END);

        panel.setMaximumSize(new Dimension(Short.MAX_VALUE, 30));

        return panel;

    }

    private JPanel createDieCombobox(JLabel nameLabel, JComboBox comboBox, JSpinner bonusSpinner) {
        JPanel panel = new JPanel();
        JPanel innerPanel = new JPanel();

        comboBox.setMaximumSize(new Dimension(75, 50));
        bonusSpinner.setMaximumSize(new Dimension(75, 50));
        innerPanel.setMinimumSize(new Dimension(200, 20));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(nameLabel);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.X_AXIS));
        innerPanel.add(comboBox);
        innerPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        innerPanel.add(bonusSpinner);

        panel.add(innerPanel);

        Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        panel.setBorder(border);

        return panel;

    }

    private JPanel createLabeledSpinner(JLabel nameLabel, JSpinner spinner) {
        JPanel panel = new JPanel();

        spinner.setMaximumSize(new Dimension(75, 50));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(nameLabel);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(spinner);
        spinner.setAlignmentX(Component.CENTER_ALIGNMENT);

        Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        panel.setBorder(border);

        return panel;

    }
}
