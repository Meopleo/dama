package edu.hazi.view;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MezoTest {
    @Test
    void testIsSzomszed_Igaz() {
        // Létrehozunk két mezőt
        Mezo kozepso = new Mezo(4, 4);
        Mezo balFent = new Mezo(3, 3);

        // Beállítjuk a kapcsolatot
        kozepso.setBalfent(balFent);

        // ELLENŐRZÉS: Felismeri-e szomszédnak?
        assertTrue(kozepso.isSzomszed(balFent), "A bal felsőnek szomszédnak kell lennie");
    }

    @Test
    void testIsSzomszed_Hamis() {
        Mezo kozepso = new Mezo(4, 4);
        Mezo tavoli = new Mezo(0, 0); // Nincs beállítva kapcsolat

        // ELLENŐRZÉS: Nem szabad szomszédnak lennie
        assertFalse(kozepso.isSzomszed(tavoli), "A távoli mező nem lehet szomszéd");
    }

    @Test
    void testUgrasJobbfent_Sikeres() {
        // Szimulálunk egy ugrást: Start -> Koztes -> Cel
        Mezo start = new Mezo(4, 4);
        Mezo koztes = new Mezo(3, 5);
        Mezo cel = new Mezo(2, 6);

        start.setJobbfent(koztes);
        koztes.setJobbfent(cel);

        // ELLENŐRZÉS: Visszaadja-e a köztes mezőt?
        assertEquals(koztes, start.ugrasJobbfent(cel));
    }
    
}
