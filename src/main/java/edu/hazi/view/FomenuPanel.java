package edu.hazi.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class FomenuPanel extends JPanel {
    private MainFrame mainFrame;

    public FomenuPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Dáma Játék");
        titleLabel.setFont(new Font("Arial",Font.BOLD,32));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(new EmptyBorder(0,0, 20, 0));
        
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1,10,10));
        add(buttonPanel, BorderLayout.CENTER);

        
        JButton startButton = new JButton("Játék Indítása");
        
        startButton.addActionListener(e -> {
            mainFrame.panelCsere(new JatekosValsztoPanel(mainFrame));
        });
        
        buttonPanel.add(startButton);

        JButton staticsButton = new JButton("Statisztikák");
        staticsButton.addActionListener(e -> {
            mainFrame.panelCsere(new StatisztikaPanel(mainFrame));
        });

        buttonPanel.add(staticsButton);

        JButton exitButton = new JButton("Kilépés");

        exitButton.addActionListener(e -> {
            System.exit(0);
        });


        buttonPanel.add(exitButton );
    }
}