package edu.hazi.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StatisztikaPanel extends JPanel {
    private MainFrame mainFrame;

    public StatisztikaPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());

        JButton visszaButton = new JButton("Vissza a Főmenübe");

        visszaButton.addActionListener(e -> {
            mainFrame.panelCsere(new FomenuPanel(mainFrame));
        });

        add(visszaButton, BorderLayout.SOUTH);
    }
}
