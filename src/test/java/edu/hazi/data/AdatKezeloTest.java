package edu.hazi.data;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AdatKezeloTest {

    @Test
    void testPontszamKezeles() {
        AdatKezelo adat = new AdatKezelo();
        
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
        AdatKezelo adat = new AdatKezelo();
        
        // Adunk neki előre pontot (trükkösen kétszer nyer)
        adat.jatekVegKez("Bajnok", "Senki"); // 10 pont
        adat.jatekVegKez("Bajnok", "Senki"); // +10 pont

        // ELLENŐRZÉS: 20 pontja van?
        assertEquals(20, adat.getPontszam("Bajnok"));
    }
    
}
