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

        java.util.Set<String> nevek = mainFrame.adatKezelo.getJatekosNevek();

        JComboBox<String> player1ComboBox = new JComboBox<>(nevek.toArray(new String[0]));
        JComboBox<String> player2ComboBox = new JComboBox<>(nevek.toArray(new String[0]));
        

        player1ComboBox.insertItemAt("Vendég", 0);
        player2ComboBox.insertItemAt("Vendég", 0);
        
        
        player1ComboBox.setSelectedItem("Vendég");
        player2ComboBox.setSelectedItem("Vendég");

        player1ComboBox.setEditable(true);
        player2ComboBox.setEditable(true);

        
        player1ComboBox.setBackground(java.awt.Color.WHITE);
        player1ComboBox.setForeground(java.awt.Color.BLACK);
        player2ComboBox.setBackground(java.awt.Color.BLACK);
        player2ComboBox.setForeground(java.awt.Color.WHITE);

        add(player1ComboBox, BorderLayout.WEST);
        add(player2ComboBox, BorderLayout.EAST);


        JButton startGameButton = new JButton("Játék Indítása");
        

        startGameButton.addActionListener(e -> {
            String p1 = (String) player1ComboBox.getEditor().getItem();
            String p2 = (String) player2ComboBox.getEditor().getItem();

            if (p1 == null || p1.trim().isEmpty() || p2 == null || p2.trim().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "A név nem lehet üres!", "Hiba", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            } 
            if (p1.length()>15 || p2.length()>15) {
                javax.swing.JOptionPane.showMessageDialog(this, "A név maximum 15 karakter hosszú!", "Hiba", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (p1.equals(p2) && !p1.equals("Vendég")) {
                javax.swing.JOptionPane.showMessageDialog(this, "Két különböző játékost válassz!", "Hiba", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            mainFrame.panelCsere(new JatekTablaPanel(mainFrame, p1, p2));
            
            
            System.out.println("Játék indítása: " + p1 + " vs " + p2);
        });

        add(startGameButton, BorderLayout.CENTER);


        JButton visszaButton = new JButton("Vissza a Főmenübe");

        visszaButton.addActionListener(e -> {
            mainFrame.panelCsere(new FomenuPanel(mainFrame));
        });

        add(visszaButton, BorderLayout.SOUTH);
    }
}
