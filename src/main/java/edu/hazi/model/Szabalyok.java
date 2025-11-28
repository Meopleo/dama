package edu.hazi.model;

import edu.hazi.view.Mezo;;

public class Szabalyok {
    private Mezo[][] mezo;

    public Szabalyok(Mezo[][] mezo) {
        this.mezo = mezo;
    }
    

    /**
     * Ellenőrzi, hogy egy adott bábuval lehet-e ütni bármelyik irányba.
     * Dáma esetén mind a 4, gyalog esetén csak a haladási irányt nézi.
     * @param honnan A vizsgált mező (bábu)
     * @return true, ha van ütési lehetőség, egyébként false
     */
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

    /**
     * Megvizsgálja az egész táblát, hogy van-e érvényben ütéskényszer.
     * @param feherLep Az éppen soron lévő játékos színe (true=fehér)
     * @return true, ha legalább egy bábuval kötelező ütni
     */
    public boolean globUtesKenyszer(boolean feherLep){
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

    /**
     * Ellenőrzi, hogy a soron lévő játékos tud-e még lépni bármelyik bábujával.
     * Ha nem, akkor vége a játéknak.
     * @param feherLep A soron lévő játékos
     * @return true, ha van még érvényes lépés
     */
    public boolean vanMegLepes(boolean feherLep) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Mezo m = mezo[i][j];
                if (m.isFoglalt() && m.isFehere() == feherLep) {
                    if (leheteUtni(m) || leheteLepni(m)) {
                        return true; // Van még lehetőség
                    }
                }
            }
        }
        return false; // Nincs lehetőség -> Game Over
    }

    /**
     * Segédfüggvény, amely megvizsgálja, hogy egy adott irányban (szomszéd -> utána)
     * lehetséges-e az ütés végrehajtása.
     * Ellenőrzi, hogy léteznek-e a mezők, a szomszédon ellenség van-e, és a mögötte lévő hely üres-e.
     * @param en A lépni kívánó bábu (mező)
     * @param szomszed A közvetlen szomszédos mező az adott irányban (az "áldozat")
     * @param utana A szomszéd mögötti mező (az érkezési hely)
     * @return true, ha a feltételek teljesülnek és lehet ütni
     */
    private boolean ellenorizUgrasLehetoseg(Mezo en, Mezo szomszed, Mezo utana) {
    
        if (szomszed == null || utana == null) {
            return false;
        }

        boolean szomszedEllenseg = szomszed.isFoglalt() && (szomszed.isFehere() != en.isFehere());
            
        boolean utanaUres = !utana.isFoglalt();
        
        return szomszedEllenseg && utanaUres;
    }

    /**
     * Megkeresi, hogy két mező között van-e átugrott ellenséges bábu.
     * @param honnan A kiinduló mező
     * @param hova Az érkezési mező
     * @return A köztes (leütött) mező, vagy null, ha nem volt ugrás
     */
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
}
