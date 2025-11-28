package edu.hazi.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.io.*;


public class AdatKezelo {

    private static final String fajlNev = "jatekosok.json";
    
    private Map<String, Integer> jatekosAdatok;

    public AdatKezelo() {
        this.jatekosAdatok = new HashMap<>();
        betoltes();
    }

    // Visszaadja a játékosok listáját (a legördülő menünek kell majd)
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
     * Elmenti a memóriában lévő adatokat a JSON fájlba.
     * Kézi string-összefűzést használ a formátumhoz.
     */
    private void mentes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fajlNev))) {
            writer.write("{");
            writer.newLine();
            int szamolo = 0;
            int meret = jatekosAdatok.size();

            for (Map.Entry<String, Integer> bejegyzes : jatekosAdatok.entrySet()) {
                szamolo++;
                String sor = "\"" + bejegyzes.getKey() + "\":" + bejegyzes.getValue();

                if (szamolo < meret) {
                    sor += ","; // Csak akkor írunk vesszőt, ha nem az utolsó elemről van szó
                } 
                writer.write(sor);
                writer.newLine();
            }

            writer.write("}");
            System.out.println("Adatok mentve.");
        } catch (IOException e) {
            System.err.println("Hiba az adatok mentésekor: " + e.getMessage());
        }
    }

    /**
     * Betölti a játékos adatokat a JSON fájlból a memóriába.
     * Egyszerű string feldolgozást használ a formátumhoz.
     */
    private void betoltes() {
        File fajl = new File(fajlNev);
        if (!fajl.exists()) {
            System.out.println("Nincs mentett adat.");
            return; // Nincs fájl, nincs mit betölteni
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fajlNev))) {
            String sor;
            
            while ((sor = reader.readLine()) != null) {
                sor = sor.trim();

                if (sor.equals("{") || sor.equals("}")) {
                    continue; // Az első és utolsó sort kihagyjuk
                }
                if (sor.isEmpty()) {
                    continue; // Üres sorokat kihagyjuk
                }
                sor = sor.replaceAll("\"", "").replace(",", "").replace("}","").replace("{","");

                String[] darabok = sor.split(":");

                if (darabok.length == 2) {
                    String nev = darabok[0].trim();
                    try {
                        int pontszam = Integer.parseInt(darabok[1].trim());
                        jatekosAdatok.put(nev, pontszam);
                    } catch (NumberFormatException e) {
                        System.err.println("Hibás pontszám formátum: " + darabok[1].trim());
                    }
                }
            }
            System.out.println("Adatok betöltve.");
        } catch (IOException e) {
            System.err.println("Hiba az adatok betöltésekor: " + e.getMessage());

        }
    }
    
    
}
        
