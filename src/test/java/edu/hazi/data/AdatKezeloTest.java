package edu.hazi.data;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdatKezeloTest {
    private static final String TESZT_FAJL = "teszt_jatekosok.json";

    @BeforeEach
    void tisztitas() {
        // Minden egyes teszt előtt letöröljük a fájlt, ha létezik.
        // Így mindig 0-ról indul a teszt, nem maradnak benne régi adatok.
        File file = new File(TESZT_FAJL);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testPontszamKezeles() {
        AdatKezelo adat = new AdatKezelo(TESZT_FAJL);
        
        // 1. Új játékos hozzáadása
        // "TesztElek" nyert a "VesztesBela" ellen
        adat.jatekVegKez("TesztElek", "VesztesBela");

        // ELLENŐRZÉS: Kapott 10 pontot?
        assertEquals(10, adat.getPontszam("TesztElek"));
        
        // ELLENŐRZÉS: A vesztes 0-n maradt (nem ment mínuszba)?
        assertEquals(0, adat.getPontszam("VesztesBela"));
    }

    @Test
    void testPontszamNovekedes() {
        AdatKezelo adat = new AdatKezelo(TESZT_FAJL);
        
        // Adunk neki előre pontot (trükkösen kétszer nyer)
        adat.jatekVegKez("Bajnok", "Senki"); // 10 pont
        adat.jatekVegKez("Bajnok", "Senki"); // +10 pont

        // ELLENŐRZÉS: 20 pontja van?
        assertEquals(20, adat.getPontszam("Bajnok"));
    }
    
}
