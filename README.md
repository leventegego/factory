# Automatizált gyár-irányító rendszer

A feladat egy intelligensen vezérelt összeszerelő gyártósor ágensrendszerének a szimulálása. Egy futószalag körül elhelyezkedő szerelő robotokat szállító robotok szolgálnak ki alkatrészekkel egy raktárhelységből. A szállító robotok karbantartását emberi munkások végzik. A gyárterületen való közlekedés során az emberek biztonsága a fő szempont, ezért minden esetben nekik van elsőbbségük.

## Ágensek:

- Szerelő robot: Az alkatrészek beszereléséért felelős ágens. A különböző alkatrészek különböző ütemben fogynak, ezért a megszakítás mentes gyártás érdekében a kritikusan fogyóban lévő alkatrészek szállítása nagyobb prioritású.

- Szállító robot: Feladata az alkatrészek szállítása a raktárból a gyártósor mellett elhelyezkedő szerelő robotokhoz. A szállítók egymással kommunikálva szervezik a közlekedést. A szállítás során meghibásodhatnak, ekkor egy munkásnak kell őket megjavítania.

- Munkás: A munkás feladata a szállító robotok karbantartása. A meghibásodott robotok hálózaton keresztül jeleznek a munkásoknak, akik a nekik kijelölt helyen várakoznak a feladatra. (A szerelés után ide visszatérnek.)

A szimuláció egy rács világban történik, ami egy grafikus interfészen keresztül jelenik meg. Itt láthatóak továbbá a szimuláció eseményei (az ágensek állapotváltása, mozgása).
