Dáma ♟️

Klasszikus kétszemélyes dámajáték asztali grafikus felülettel, Java nyelven. A BME VIK Programozás alapjai 3 tárgy házi feladataként készült (2025 ősz).

<!-- Ide tegyél be egy képernyőképet a játékról, pl.: ![Játéktábla](docs/screenshot.png) -->
Funkciók
Teljes szabálykezelés: 8×8-as tábla, 12-12 bábu, átlós lépés a sötét mezőkön
Dámává alakulás: a túlsó szélre érő gyalogból dáma lesz, amely hátrafelé is léphet és üthet
Ütéskényszer: ha a soron lévő játékos üthet, kötelező ütnie
Láncütés: ha egy ütés után ugyanazzal a bábuval újabb ütés lehetséges, azt is végre kell hajtani, a lépésjog csak utána száll át
Játékosprofilok: profil kiválasztása vagy új profil létrehozása játék előtt, illetve vendég mód
Pontozás és statisztika: a győztes +10, a vesztes −2 pontot kap; az eredmények a főmenüben láthatók
Mentés: a profilok és pontszámok JSON fájlban tárolódnak, és a játék végén automatikusan frissülnek
Technológiák
	
Nyelv	Java 17
Grafikus felület	Swing
Build	Maven
JSON kezelés	Gson
Tesztelés	JUnit 5
Futtatás

Követelmény: JDK 17 vagy újabb és Maven.

bash
git clone https://github.com/Meopleo/dama.git
cd dama
mvn compile exec:java

Tesztek futtatása:

bash
mvn test
Projektstruktúra
src/main/java/edu/hazi/
├── Main.java            belépési pont
├── model/
│   └── Szabalyok.java   lépés-, ütés- és láncütés-szabályok
├── data/
│   └── AdatKezelo.java  játékosprofilok és pontszámok JSON mentése
└── view/                Swing felület: főmenü, játékosválasztó, tábla, statisztika
src/test/java/           JUnit tesztek a szabályokhoz, a mezőkhöz és az adatkezeléshez
Dokumentáció
Specifikáció
Dokumentáció
Szerző

Tullner Levente, BME VIK mérnökinformatikus hallgató
