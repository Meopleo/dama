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
    private Mezo[][] mezo;
    private boolean feherLep;
    private Mezo kivalasztottMezo;


    


    public JatekTablaPanel(MainFrame mainFrame, String nev1, String nev2) {
        this.mainFrame = mainFrame;
        this.feherLep = true;
        this.kivalasztottMezo = null;

        
        setLayout(new BorderLayout());

        JLabel EgyesJatekosPanel = new JLabel(nev2);
        EgyesJatekosPanel.setFont(new Font("Arial",Font.BOLD,32));
        EgyesJatekosPanel.setHorizontalAlignment(JLabel.CENTER);
        add(EgyesJatekosPanel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(8, 8));
        
        // --- BIZTONSÁGOS KÉPBETÖLTÉS ---
        javax.swing.ImageIcon feketeBabu = null;
        javax.swing.ImageIcon feherBabu = null;

        try {
            // Először ellenőrizzük, megvan-e a fájl
            java.net.URL feketeUrl = getClass().getResource("/edu/hazi/view/fekete.png");
            java.net.URL feherUrl = getClass().getResource("/edu/hazi/view/feher.png");

            if (feketeUrl == null || feherUrl == null) {
                System.err.println("HIBA: Nem találom a képeket! Ellenőrizd a fájlneveket és a mappát.");
                // Ha nincs kép, üres ikonokat használunk, hogy ne omoljon össze
                feketeBabu = new javax.swing.ImageIcon(); 
                feherBabu = new javax.swing.ImageIcon();
            } else {
                // Ha megvannak, betöltjük és méretezzük
                javax.swing.ImageIcon eredetiFekete = new javax.swing.ImageIcon(feketeUrl);
                javax.swing.ImageIcon eredetiFeher = new javax.swing.ImageIcon(feherUrl);

                java.awt.Image scaledFekete = eredetiFekete.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
                java.awt.Image scaledFeher = eredetiFeher.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);

                feketeBabu = new javax.swing.ImageIcon(scaledFekete);
                feherBabu = new javax.swing.ImageIcon(scaledFeher);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mezo = new Mezo[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mezo[i][j] = new Mezo(i, j);
                mezo[i][j].setPreferredSize(new Dimension(50,50));
                if ((i + j) % 2 == 0) {
                    mezo[i][j].setBackground(java.awt.Color.WHITE);
                    mezo[i][j].setSotetMezo(false);
                } else {
                    mezo[i][j].setBackground(java.awt.Color.DARK_GRAY);
                    mezo[i][j].setSotetMezo(true);
                    mezo[i][j].addActionListener(e ->{
                        kattintasTortent((Mezo) e.getSource());
                    });
                    if (i < 3) {
                        mezo[i][j].setFoglalt(true);
                        mezo[i][j].setFehere(false);
                        mezo[i][j].setIcon(feketeBabu);
                    } else if (i > 4) {
                        mezo[i][j].setFoglalt(true);
                        mezo[i][j].setFehere(true);
                        mezo[i][j].setIcon(feherBabu);
                    }
                }
                boardPanel.add(mezo[i][j]);
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i > 0 && j < 7) {
                    mezo[i][j].setJobbfent(mezo[i - 1][j + 1]);
                }
                if (i > 0 && j > 0) {
                    mezo[i][j].setBalfent(mezo[i - 1][j - 1]);
                }
                if (i < 7 && j < 7) {
                    mezo[i][j].setJobblent(mezo[i + 1][j + 1]);
                }
                if (i < 7 && j > 0) {
                    mezo[i][j].setBallent(mezo[i + 1][j - 1]);
                }
            }
        }

                   
        
        add(boardPanel, BorderLayout.CENTER);


        JPanel alsoresz = new JPanel(new GridLayout(2,1));
        JLabel KettesJatekosPanel = new JLabel(nev1);
        KettesJatekosPanel.setFont(new Font("Arial",Font.BOLD,32));
        KettesJatekosPanel.setHorizontalAlignment(JLabel.CENTER);
        JButton visszaButton = new JButton("Vissza a Főmenübe");
        visszaButton.addActionListener(e -> {
            mainFrame.panelCsere(new FomenuPanel(mainFrame));
        });
        alsoresz.add(KettesJatekosPanel);
        alsoresz.add(visszaButton);
        add(alsoresz, BorderLayout.SOUTH);
    }
    

    private void kattintasTortent(Mezo mezo) {
        if (mezo.isFoglalt() && mezo.isFehere() == feherLep) {
            kivalasztottMezo = mezo;
           System.out.println("Kiválasztott mező: (" + mezo.getSor() + ", " + mezo.getOszlop() + ")");
        } else if (kivalasztottMezo != null) {
            System.out.println("Mozgatás próbálkozás: (" + kivalasztottMezo.getSor() + ", " + kivalasztottMezo.getOszlop() + ") -> (" + mezo.getSor() + ", " + mezo.getOszlop() + ")");
            // Itt jönne a lépés logikája

            boolean lepesErvenyes = false;
            // Egyszerű lépés ellenőrzése

            if (!mezo.isFoglalt()){
                if(kivalasztottMezo.isDama() && kivalasztottMezo.isSzomszed(mezo)) {
                    System.out.println("Dáma lépés");
                    lepesErvenyes = true;
                }
                else{
                    if(feherLep==true){
                        if(mezo==kivalasztottMezo.getBalfent() || mezo==kivalasztottMezo.getJobbfent()){
                            System.out.println("Sima lépés");
                            lepesErvenyes = true;
                        }
                    }
                    else{
                        if(mezo==kivalasztottMezo.getBallent() || mezo==kivalasztottMezo.getJobblent()){
                            System.out.println("Sima lépés");
                            lepesErvenyes = true;
                        }
                    }
                }
            }
            if(lepesErvenyes){
                lepestVegrehajt(kivalasztottMezo, mezo);
                feherLep = !feherLep; // Visszaállítjuk az előző játékosra
                System.out.println("Érvényes lépés!");
            }else{
                System.out.println("Érvénytelen lépés!");
            }
            
            kivalasztottMezo = null; // Lépés után töröljük a kiválasztást
        }
    }


    private void lepestVegrehajt(Mezo honnan, Mezo hova) {
        hova.setIcon(honnan.getIcon());
        hova.setFoglalt(true);
        hova.setFehere(honnan.isFehere());
        hova.setDama(honnan.isDama());

        honnan.setIcon(null);
        honnan.setFoglalt(false);
        honnan.setFehere(false);
        honnan.setDama(false);
        if (hova.isFehere() && hova.getSor() == 0) {
            hova.setDama(true);
            System.out.println("Fehér bábu dámává válik!");
        } else if (!hova.isFehere() && hova.getSor() == 7) {
            hova.setDama(true);
            System.out.println("Fekete bábu dámává válik!");
    }

    }
}
