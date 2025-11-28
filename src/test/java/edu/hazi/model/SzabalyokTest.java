package edu.hazi.model;

import edu.hazi.view.Mezo;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SzabalyokTest {
    private Mezo[][] tabla;
    private Szabalyok szabalyok;

    @BeforeEach
    void setUp() {
        // Minden teszt előtt csinálunk egy üres 8x8-as táblát
        tabla = new Mezo[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tabla[i][j] = new Mezo(i, j);
            }
        }
        // Összekötjük a szomszédokat (egyszerűsítve, csak amire szükségünk van)
        // Pl. (4,4) jobb-fenti szomszédja (3,5), annak a jobb-fenti (2,6)
        tabla[4][4].setJobbfent(tabla[3][5]);
        tabla[3][5].setJobbfent(tabla[2][6]);

        szabalyok = new Szabalyok(tabla);
    }

    @Test
    void testLeheteUtni_Igen() {
        // SZITUÁCIÓ:
        // (4,4)-en vagyok én (FEHÉR)
        // (3,5)-ön van az ELLENSÉG (FEKETE)
        // (2,6) ÜRES
        
        Mezo en = tabla[4][4];
        en.setFoglalt(true);
        en.setFehere(true); // Fehér vagyok

        Mezo ellenseg = tabla[3][5];
        ellenseg.setFoglalt(true);
        ellenseg.setFehere(false); // Fekete

        Mezo cel = tabla[2][6];
        cel.setFoglalt(false); // Üres

        // ELLENŐRZÉS: A szabálynak igent kell mondania
        assertTrue(szabalyok.leheteUtni(en));
    }

    @Test
    void testLeheteUtni_Nem_Mert_Ures() {
        // Nincs ellenség, üres a szomszéd
        Mezo en = tabla[4][4];
        en.setFoglalt(true);
        en.setFehere(true);

        assertFalse(szabalyok.leheteUtni(en));
    }

    @Test
    void testLeheteUtni_Nem_Mert_Sajat() {
        // A szomszéd is FEHÉR (saját)
        Mezo en = tabla[4][4];
        en.setFoglalt(true);
        en.setFehere(true);

        Mezo barat = tabla[3][5];
        barat.setFoglalt(true);
        barat.setFehere(true); // Saját!

        assertFalse(szabalyok.leheteUtni(en));
    }
    
    @Test
    void testGlobUtesKenyszer() {
        // Ugyanaz a helyzet, mint az első tesztnél
        Mezo en = tabla[4][4];
        en.setFoglalt(true);
        en.setFehere(true);
        
        Mezo ellenseg = tabla[3][5];
        ellenseg.setFoglalt(true);
        ellenseg.setFehere(false);

        // ELLENŐRZÉS: A globális kényszernek igaznak kell lennie a Fehérre
        assertTrue(szabalyok.globUtesKenyszer(true));
    }
    
}
