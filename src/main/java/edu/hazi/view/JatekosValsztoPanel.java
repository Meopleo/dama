package edu.hazi.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JComboBox;

import java.awt.BorderLayout;

public class JatekosValsztoPanel extends JPanel {
    private MainFrame mainFrame;

    public JatekosValsztoPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(20, 20, 20, 20));


        
        JLabel titleLabel = new JLabel("Játékos Választó");
        titleLabel.setFont(new Font("Arial",Font.BOLD,32));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(new EmptyBorder(0,0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);


        JComboBox<String> player1ComboBox = new JComboBox<>(new String[]{"Játékos 1", "Játékos 2"});
        player1ComboBox.setEditable(true);
        add(player1ComboBox, BorderLayout.WEST);

        JComboBox<String> player2ComboBox = new JComboBox<>(new String[]{"Játékos 2", "Játékos 1"});
        player2ComboBox.setEditable(true);
        add(player2ComboBox, BorderLayout.EAST);


        JButton startGameButton = new JButton("Játék Indítása");
        startGameButton.addActionListener(e -> {
            mainFrame.panelCsere(new JatekTablaPanel(mainFrame,
                    (String) player1ComboBox.getSelectedItem(),
                    (String) player2ComboBox.getSelectedItem()));
            // Itt indíthatod el a játékot a kiválasztott játékosokkal
            String player1 = (String) player1ComboBox.getSelectedItem();
            String player2 = (String) player2ComboBox.getSelectedItem();
            System.out.println("Játék indítása: " + player1 + " vs " + player2);
            // További kód a játék indításához...
        });

        add(startGameButton, BorderLayout.CENTER);


        JButton visszaButton = new JButton("Vissza a Főmenübe");

        visszaButton.addActionListener(e -> {
            mainFrame.panelCsere(new FomenuPanel(mainFrame));
        });

        add(visszaButton, BorderLayout.SOUTH);
    }
}
