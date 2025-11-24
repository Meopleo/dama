package edu.hazi.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;

import javax.swing.DebugGraphics;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class JatekTablaPanel extends JPanel {
    private MainFrame mainFrame;
    private String nev1;
    private String nev2;
    JButton[][] mezo;

    public JatekTablaPanel(MainFrame mainFrame, String nev1, String nev2) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JLabel EgyesJatekosPanel = new JLabel(nev1);
        EgyesJatekosPanel.setFont(new Font("Arial",Font.BOLD,32));
        EgyesJatekosPanel.setHorizontalAlignment(JLabel.CENTER);
        add(EgyesJatekosPanel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(8, 8));
        
        JButton[][] mezo = new JButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mezo[i][j] = new JButton();
                mezo[i][j].setPreferredSize(new Dimension(50,50));
                if ((i + j) % 2 == 0) {
                    mezo[i][j].setBackground(java.awt.Color.WHITE);
                } else {
                    mezo[i][j].setBackground(java.awt.Color.DARK_GRAY);
                }
                boardPanel.add(mezo[i][j]);
            }
        }

            
        
        
        add(boardPanel, BorderLayout.CENTER);
    }
    
}
