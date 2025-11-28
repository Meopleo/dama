package edu.hazi.model;

import edu.hazi.view.Mezo;;

public class Szabalyok {
    private Mezo[][] mezo;

    public Szabalyok(Mezo[][] mezo) {
        this.mezo = mezo;
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

    private boolean ellenorizUgrasLehetoseg(Mezo en, Mezo szomszed, Mezo utana) {
    
        if (szomszed == null || utana == null) {
            return false;
        }

        boolean szomszedEllenseg = szomszed.isFoglalt() && (szomszed.isFehere() != en.isFehere());
            
        boolean utanaUres = !utana.isFoglalt();
        
        return szomszedEllenseg && utanaUres;
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
}
