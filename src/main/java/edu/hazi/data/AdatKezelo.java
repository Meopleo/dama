package edu.hazi.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;


public class AdatKezelo {

    private String fajlNev = "jatekosok.json";
    
    private Map<String, Integer> jatekosAdatok;

    public AdatKezelo() {
        this.jatekosAdatok = new HashMap<>();
        betoltes();
    }

    public AdatKezelo(String tesztfajlNev) {
        this.fajlNev = tesztfajlNev;
        this.jatekosAdatok = new HashMap<>();
        betoltes();
    }

    // Visszaadja a játékosok listáját
    public Set<String> getJatekosNevek() {
        return jatekosAdatok.keySet();
    }

    // Visszaadja egy adott játékos pontszámát
    public int getPontszam(String nev) {
        return jatekosAdatok.getOrDefault(nev, 0);
    }

    public Map<String, Integer> getStatisztikak() {
        return jatekosAdatok;
    }


    /**
     * Kezeli a játék végét: frissíti a pontszámokat és ment.
     * @param nyertes A győztes játékos neve (+10 pont)
     * @param vesztes A vesztes játékos neve (-2 pont)
     */
    public void jatekVegKez(String nyertes, String vesztes) {
        if (nyertes.equals("Vendég") || vesztes.equals("Vendég")) {
            return; // Ha vendég játszott, nem mentünk adatot
        }
        
        int nyertesPontszam = jatekosAdatok.getOrDefault(nyertes, 0);
        jatekosAdatok.put(nyertes, nyertesPontszam + 10);

        int vesztesPontszam = jatekosAdatok.getOrDefault(vesztes, 0);
        int ujVesztesPontszam = vesztesPontszam - 2;
        if (ujVesztesPontszam < 0) {
            ujVesztesPontszam = 0; // Nem lehet negatív pontszám
        }
        jatekosAdatok.put(vesztes, ujVesztesPontszam);
        mentes();
    }

    /**
     * Menteni a játékos adatokat a JSON fájlba.
     * A Gson könyvtárat használja a JSON kezelésére.
     */
    private void mentes() {
        // setPrettyPrinting() -> Hogy szépen tördelve mentse el, ne egy sorba
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Writer writer = new FileWriter(fajlNev)) {
            // Egy sorban kiírja a Map-et JSON-be
            gson.toJson(jatekosAdatok, writer);
            System.out.println("Adatok mentve (Gson).");
        } catch (IOException e) {
            System.err.println("Hiba mentéskor: " + e.getMessage());
        }
    }

    /**
     * Betölti a játékos adatokat a JSON fájlból.
     * A Gson könyvtárat használja a JSON kezelésére.
     */
    private void betoltes() {
        File file = new File(fajlNev);
        if (!file.exists()) return;

        Gson gson = new Gson();

        try (Reader reader = new FileReader(fajlNev)) {
            Type type = new TypeToken<HashMap<String, Integer>>(){}.getType();

            jatekosAdatok = gson.fromJson(reader, type);

            if (jatekosAdatok == null) {
                jatekosAdatok = new HashMap<>();
            }

            System.out.println("Adatok betöltve (Gson).");
        } catch (IOException e) {
            System.err.println("Hiba betöltéskor: " + e.getMessage());
        }
    }
    
    
}
        
