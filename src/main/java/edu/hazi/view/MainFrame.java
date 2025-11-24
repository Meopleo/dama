package edu.hazi.view;

import javax.swing.JFrame;



import javax.swing.*;

public class MainFrame extends JFrame {
    
    public MainFrame() {
        setTitle("Dáma Játék");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        panelCsere(new FomenuPanel(this));
        
        
        setVisible(true);
    }

    public void panelCsere(JPanel ujPanel) {
        getContentPane().removeAll();
        add(ujPanel);
        revalidate();
        repaint();
        pack();
        setLocationRelativeTo(null);


    }
}
