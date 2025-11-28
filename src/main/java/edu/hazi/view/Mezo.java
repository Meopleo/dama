package edu.hazi.view;

import javax.swing.JButton;

public class Mezo extends JButton {
    private int sor;
    private int oszlop;
    private boolean foglalt;
    private boolean fehere;
    private boolean dama;
    private Mezo jobbfent;
    private Mezo balfent;
    private Mezo jobblent;
    private Mezo ballent;
    private boolean sotetMezo;
    

    public Mezo(int sor, int oszlop) {
        this.sor = sor;
        this.oszlop = oszlop;
    }

    public boolean isSzomszed(Mezo masik) {
        if (masik == jobbfent || masik == balfent || masik == jobblent || masik == ballent) {
            return true;
        }
        return false;
    }

    public Mezo ugrasJobbfent(Mezo cel){
        if (this.getJobbfent() != null && this.getJobbfent().getJobbfent() == cel) {
            return this.getJobbfent();
        }
        return null;
    }

    public Mezo ugrasBalfent(Mezo cel){
        if (this.getBalfent() != null && this.getBalfent().getBalfent() == cel) {
            return this.getBalfent();
        }
        return null;
    }

    public Mezo ugrasJobblent(Mezo cel){
        if (this.getJobblent() != null && this.getJobblent().getJobblent() == cel) {
            return this.getJobblent();
        }
        return null;
    }

    public Mezo ugrasBallent(Mezo cel){
        if (this.getBallent() != null && this.getBallent().getBallent() == cel) {
            return this.getBallent();
        }
        return null;
    }

    public boolean jobbFentUres(){
        return this.getJobbfent() != null && !this.getJobbfent().isFoglalt();
    }
    public boolean balFentUres(){
        return this.getBalfent() != null && !this.getBalfent().isFoglalt();
    }
    public boolean jobbLentUres(){
        return this.getJobblent() != null && !this.getJobblent().isFoglalt();
    }
    public boolean balLentUres(){
        return this.getBallent() != null && !this.getBallent().isFoglalt();
    }
    

    public int getSor() {
        return sor;
    }

    public int getOszlop() {
        return oszlop;
    }

    public boolean isFoglalt() {
        return foglalt;
    }

    public void setFoglalt(boolean foglalt) {
        this.foglalt = foglalt;
    }

    public boolean isFehere() {
        return fehere;
    }

    public void setFehere(boolean fehere) {
        this.fehere = fehere;
    }

    public boolean isDama() {
        return dama;
    }

    public void setDama(boolean dama) {
        this.dama = dama;
    }
    
    public Mezo getJobbfent() {
        return jobbfent;
    }

    public void setJobbfent(Mezo jobbfent) {
        this.jobbfent = jobbfent;
    }

    public Mezo getBalfent() {
        return balfent;
    }

    public void setBalfent(Mezo balfent) {
        this.balfent = balfent;
    }

    public Mezo getJobblent() {
        return jobblent;
    }

    public void setJobblent(Mezo jobblent) {
        this.jobblent = jobblent;
    }

    public Mezo getBallent() {
        return ballent;
    }

    public void setBallent(Mezo ballent) {
        this.ballent = ballent;
    }
    public boolean isSotetMezo() {
        return sotetMezo;
    }
    public void setSotetMezo(boolean sotetMezo) {
        this.sotetMezo = sotetMezo;
    }


}