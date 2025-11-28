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

    private javax.swing.ImageIcon feherDama;
    private javax.swing.ImageIcon feketeDama;

    


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

        feherDama = null;
        feketeDama = null;
        try {
            java.net.URL feherDamaUrl = getClass().getResource("/edu/hazi/view/feherdama.png");
            java.net.URL feketeDamaUrl = getClass().getResource("/edu/hazi/view/feketedama.png");

            if (feherDamaUrl == null || feketeDamaUrl == null) {
                System.err.println("HIBA: Nem találom a dámaképeket! Ellenőrizd a fájlneveket és a mappát.");
                feherDama = new javax.swing.ImageIcon(); 
                feketeDama = new javax.swing.ImageIcon();
            } else {
                javax.swing.ImageIcon eredetiFeherDama = new javax.swing.ImageIcon(feherDamaUrl);
                javax.swing.ImageIcon eredetiFeketeDama = new javax.swing.ImageIcon(feketeDamaUrl);

                java.awt.Image scaledFeherDama = eredetiFeherDama.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
                java.awt.Image scaledFeketeDama = eredetiFeketeDama.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);

                feherDama = new javax.swing.ImageIcon(scaledFeherDama);
                feketeDama = new javax.swing.ImageIcon(scaledFeketeDama);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
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
    

    public void kattintasTortent(Mezo mezo) {
        if (mezo.isFoglalt() && mezo.isFehere() == feherLep) { // Csak a megfelelő színű bábu választható ki
            boolean kenyszerUtes = globUtesKenyszer();
            if (kenyszerUtes && !leheteUtni(mezo)) {
                System.out.println("Ütés kötelező! Válassz egy ütésre képes bábut.");
                return;
            }
            if (kivalasztottMezo != null) {
                kivalasztottMezo.setBackground(java.awt.Color.DARK_GRAY);
            }
            kivalasztottMezo = mezo;
            kivalasztottMezo.setBackground(java.awt.Color.CYAN);

            System.out.println("Kiválasztott mező: (" + mezo.getSor() + ", " + mezo.getOszlop() + ")");
        }
        
        else if (kivalasztottMezo != null && !mezo.isFoglalt()) {// Van kiválasztott mező, próbálunk lépni            
            
            // Ugras
            Mezo atugrottMezo = keresAtugrottMezok(kivalasztottMezo, mezo);
            
            if (atugrottMezo != null) {
                
                if (mezo.isFoglalt() == false && atugrottMezo != null && atugrottMezo.isFoglalt() && atugrottMezo.isFehere() != feherLep) {
                    // Ugrás logika
                    System.out.println("Ugrás lépés");
                    lepestVegrehajt(kivalasztottMezo, mezo);
                    atugrottMezo.setIcon(null);
                    atugrottMezo.setFoglalt(false);
                    atugrottMezo.setFehere(false);
                    atugrottMezo.setDama(false);
                    
                    
                    System.out.println("Érvényes ugrás!");
                    
                    
                    
                    if (leheteUtni(mezo)){
                        System.out.println("További ütés lehetséges!");
                        takaritas();
                        kivalasztottMezo = mezo;
                        kivalasztottMezo.setBackground(java.awt.Color.CYAN);
                    }
                    else{
                        kivalasztottMezo = null; // Lépés után töröljük a kiválasztást
                        korVegeEsEllenorzes();
                        ellenorizGyozelem();
                    }
                    return;
                }
                
            }
            
            // Simas lépés
            if (globUtesKenyszer()) {
                System.out.println("HIBA: Kötelező ütni! Nem léphetsz simát.");
                return; // Megállítjuk a folyamatot, nem engedjük a lépést
            }
            
            
            boolean lepesErvenyes = false;
            // Egyszerű lépés ellenőrzése

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
            if(lepesErvenyes){
                kivalasztottMezo.setBackground(java.awt.Color.DARK_GRAY);
                lepestVegrehajt(kivalasztottMezo, mezo);

                kivalasztottMezo = null; // Lépés után töröljük a kiválasztást
                korVegeEsEllenorzes();
                ellenorizGyozelem();
                System.out.println("Érvényes lépés!");
            }
            else{
                System.out.println("Érvénytelen lépés!");
            }
            
        }
    }


    public void lepestVegrehajt(Mezo honnan, Mezo hova) {
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
            hova.setIcon(feherDama);
            System.out.println("Fehér bábu dámává válik!");
        } else if (!hova.isFehere() && hova.getSor() == 7) {
            hova.setDama(true);
            hova.setIcon(feketeDama);
            System.out.println("Fekete bábu dámává válik!");
        }   

    }

    public Mezo keresAtugrottMezok(Mezo honnan, Mezo hova) {
        // Dáma esetén mind a négy irányt ellenőrizzük

        if (honnan.isDama()){
            if (honnan.ugrasJobbfent(hova) != null) {
                return honnan.ugrasJobbfent(hova);
            }
            if (honnan.ugrasBalfent(hova) != null) {
                return honnan.ugrasBalfent(hova);
            }
            if (honnan.ugrasJobblent(hova) != null) {
                return honnan.ugrasJobblent(hova);
            }
            if (honnan.ugrasBallent(hova) != null) {
                return honnan.ugrasBallent(hova);
            }
        }
        // Fehér vagy fekete bábu esetén csak a megfelelő irányokat ellenőrizzük
        else if (honnan.isFehere()) {
            
            if (honnan.ugrasJobbfent(hova) != null) {
                return honnan.ugrasJobbfent(hova);
            }
            if (honnan.ugrasBalfent(hova) != null) {
                return honnan.ugrasBalfent(hova);
            }
        }
        else {
            if (honnan.ugrasJobblent(hova) != null) {
                return honnan.ugrasJobblent(hova);
            }
            if (honnan.ugrasBallent(hova) != null) {
                return honnan.ugrasBallent(hova);
            }
        }
        return null;
    }

    public boolean leheteUtni(Mezo honnan) {
        if (honnan.isDama()){
            // Dáma esetén mind a négy irányt ellenőrizzük
            
            if (ellenorizUgrasLehetoseg(honnan, honnan.getJobbfent(), honnan.getJobbfent() != null ? honnan.getJobbfent().getJobbfent() : null)) {
                return true;
            }
            if (ellenorizUgrasLehetoseg(honnan, honnan.getBalfent(), honnan.getBalfent() != null ? honnan.getBalfent().getBalfent() : null)) {
                return true;
            }
            if (ellenorizUgrasLehetoseg(honnan, honnan.getJobblent(), honnan.getJobblent() != null ? honnan.getJobblent().getJobblent() : null)) {
                return true;
            }
            if (ellenorizUgrasLehetoseg(honnan, honnan.getBallent(), honnan.getBallent() != null ? honnan.getBallent().getBallent() : null)) {
                return true;
            }
        }
        else if (honnan.isFehere()) {
            // Fehér bábu esetén csak felfelé néző irányokat ellenőrizzük
            if (ellenorizUgrasLehetoseg(honnan, honnan.getJobbfent(), honnan.getJobbfent() != null ? honnan.getJobbfent().getJobbfent() : null)) {
                return true;
            }
            if (ellenorizUgrasLehetoseg(honnan, honnan.getBalfent(), honnan.getBalfent() != null ? honnan.getBalfent().getBalfent() : null)) {
                return true;
            }
        } else {
            // Fekete bábu esetén csak lefelé néző irányokat ellenőrizzük
            if (ellenorizUgrasLehetoseg(honnan, honnan.getJobblent(), honnan.getJobblent() != null ? honnan.getJobblent().getJobblent() : null)) {
                return true;
            }
            if (ellenorizUgrasLehetoseg(honnan, honnan.getBallent(), honnan.getBallent() != null ? honnan.getBallent().getBallent() : null)) {
                return true;
            }
        
        }
        return false;
    }

    private boolean ellenorizUgrasLehetoseg(Mezo en, Mezo szomszed, Mezo utana) {
    
        if (szomszed == null || utana == null) {
            return false;
        }

        boolean szomszedEllenseg = szomszed.isFoglalt() && (szomszed.isFehere() != en.isFehere());
            
        boolean utanaUres = !utana.isFoglalt();
        
        return szomszedEllenseg && utanaUres;
    }

    public boolean globUtesKenyszer(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (mezo[i][j].isFoglalt() && mezo[i][j].isFehere() == feherLep) {
                    if (leheteUtni(mezo[i][j])){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    
    private void korVegeEsEllenorzes() {
        System.out.println("--- Kör vége ---");
        
        feherLep = !feherLep;

        System.out.println("Mostantól " + (feherLep ? "FEHÉR" : "FEKETE") + " jön.");

        // 1. Alaphelyzet visszaállítása: Minden mező sötét színre
        takaritas();

        // 2. Ütéskényszer ellenőrzése és jelzése
        if (globUtesKenyszer()) {
            System.out.println("FIGYELEM: Ütéskényszer van!");
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Mezo m = mezo[i][j];
                    // Ha a bábu a mostani játékosé ÉS tud ütni -> SZÍNEZZÜK narancsara
                    if (m.isFoglalt() && m.isFehere() == feherLep && leheteUtni(m)) {
                        m.setBackground(java.awt.Color.ORANGE); // Vagy PINK, ami jobban tetszik
                    }
                }
            }
        }
    }
    

    public void takaritas(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (mezo[i][j].isSotetMezo()) {
                    mezo[i][j].setBackground(java.awt.Color.DARK_GRAY);
                }
                
            }
        }
    }

    public boolean leheteLepni(Mezo m){
        if (m.isDama()){
            return m.jobbFentUres() || m.balFentUres() || m.jobbLentUres() || m.balLentUres();
        }
        else if (m.isFehere()){
            return m.balFentUres() || m.jobbFentUres();
        }
        else{
            return m.balLentUres() || m.jobbLentUres();
        }
    }

    private void ellenorizGyozelem() {
    boolean vanLehetoseg = false;
    
    // Végignézzük az összes mezőt
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            Mezo m = mezo[i][j];
            // Ha a bábu a mostani játékosé (aki épp jön)
            if (m.isFoglalt() && m.isFehere() == feherLep) {
                // Tud ütni VAGY tud simát lépni?
                if (leheteUtni(m) || leheteLepni(m)) {
                    vanLehetoseg = true;
                    break; // Találtunk egy lépést, tehát nincs vége, mehet tovább
                }
            }
        }
        if (vanLehetoseg) break;
    }
    
    // Ha végignéztük az összeset, és NEM találtunk lehetőséget -> GAME OVER
    if (!vanLehetoseg) {
        String nyertes = feherLep ? "Fekete" : "Fehér"; // Ha Fehér jönne, de nem tud, akkor Fekete nyert
        
        // Kiírjuk az üzenetet és visszalépünk a menübe
        javax.swing.JOptionPane.showMessageDialog(this, 
            "A játéknak vége! Nincs több lépés.\nA győztes: " + nyertes);
            
        mainFrame.panelCsere(new FomenuPanel(mainFrame));
    }
}
}
