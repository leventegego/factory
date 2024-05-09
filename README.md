# Automatizált gyár-irányító rendszer

A feladat egy intelligensen vezérelt összeszerelő gyártósor ágensrendszerének a szimulálása. Egy futószalag körül elhelyezkedő szerelő robotokat szállító robotok szolgálnak ki alkatrészekkel egy raktárhelységből. A szállító robotok karbantartását emberi munkások végzik. A gyárterületen való közlekedés során az emberek biztonsága a fő szempont, ezért minden esetben nekik van elsőbbségük.

## Ágensek

- Szerelő robot: Az alkatrészek beszereléséért felelős ágens. A különböző alkatrészek különböző ütemben fogynak, ezért a megszakítás mentes gyártás érdekében a kritikusan fogyóban lévő alkatrészek szállítása nagyobb prioritású.

- Szállító robot: Feladata az alkatrészek szállítása a raktárból a gyártósor mellett elhelyezkedő szerelő robotokhoz. A szállítás során meghibásodhatnak, ekkor egy munkásnak kell őket megjavítania.

- Munkás: A munkás feladata a szállító robotok karbantartása. A meghibásodott robotok hálózaton keresztül jeleznek a munkásoknak, akik a nekik kijelölt helyen várakoznak a feladatra. (A szerelés után ide visszatérnek.)


## Megjelenítés

A szimuláció egy rács világban történik, ami egy grafikus interfészen keresztül jelenik meg. Itt láthatóak továbbá a szimuláció eseményei (az ágensek állapotváltása, mozgása).

A Jason felületen a gyárterületet láthatjuk. A gyártósor összeszerelő robotjai alapesetben szürke színnel jelennek meg, a rajtuk lévő szám a náluk lévő alkatrészeket mutatja. A szállító robotok sárgával vannak jelölve, rajtuk a szállított alkatrészek számát látjuk. A kékkel jelölt ágensek a munkások. A gyárterületen található egy P-vel jelölt mező, ez a raktár, innen kell az alkatrészeket az összeszerelő robotokhoz vinni.

## Részletes viselkedés

A szerelő robotok véletlenszerű (de konstans) ütemben használják fel az alkatrészeket, és véletlen számú van náluk kezdetben. Amikor egy szerelő robotnál 50-nél kevesebb alkatrész van, akkor pirosra vált, és jelzi a szállítóknak az igényét. Ha van szabad szállító, akkor az visszajelez, és elindul a raktár felé. A raktár mezőre rá lehet lépni, de a szomszédos mezőről is fel lehet venni alkatrészeket. A raktárból a szállító a szerelőhöz viszi az alkatrészeket, majd visszatér a helyére. A szállító a hazatérés során is el tud vállalni egy rendelést, ekkor egyből a raktár felé veszi az irányt.

Mozgás közben a szállítók bármikor meghibásodhatnak, ekkor az elvállalt rendelést leadják, azt másik robot veheti fel. A hibás robot elveszti az alkatrészeket, narancssárgára vált majd jelez a munkásoknak, akik közül egy odamegy megjavítani. A javítás után a munkás és a robot hazatér.

A biztonság érdekében a robotok elsőbbséget adnak a munkásoknak. Ha egy munkás közel van egy robothoz, akkor a robot csak csökkentett sebességgel mozoghat.




