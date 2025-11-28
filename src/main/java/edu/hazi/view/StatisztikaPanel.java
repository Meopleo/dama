package edu.hazi.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.util.Map;

public class StatisztikaPanel extends JPanel {
    private MainFrame mainFrame;

    public StatisztikaPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());

        Map<String, Integer> statisztikak = mainFrame.adatKezelo.getStatisztikak();

        String[] oszlopok = {"Játékos Név", "Győzelmi Pontok"};
        Object[][] adatok = new Object[statisztikak.size()][2];
        int index = 0;
        for (Map.Entry<String, Integer> entry : statisztikak.entrySet()) {
            adatok[index][0] = entry.getKey();
            adatok[index][1] = entry.getValue();
            index++;
        }

        

        JTable tabla = new JTable(adatok, oszlopok);
        tabla.setEnabled(false); // Ne lehessen szerkeszteni

        add(new javax.swing.JScrollPane(tabla), BorderLayout.CENTER);


        JButton visszaButton = new JButton("Vissza a Főmenübe");

        visszaButton.addActionListener(e -> {
            mainFrame.panelCsere(new FomenuPanel(mainFrame));
        });

        add(visszaButton, BorderLayout.SOUTH);
    }
}
