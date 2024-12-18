# AEKI Fabrikverwaltungssystem

**DATUM:** 15.12.24  
**AUTOREN:** PROGRAMMIERGRUPPE 17  

---

## Inhaltsverzeichnis

- [Einführung](#einführung)
- [Funktionen](#funktionen)
- [Benutzung](#benutzung)
- [Klassenstruktur](#klassenstruktur)
- [Tests](#tests)

---

## Einführung

Das Ziel dieses Projekts ist es, ein Fabriksystem zu entwickeln, das dir die Verwaltung von Bestellungen, 
Lagerbeständen und Produktionsprozessen erleichtert.  

---

## Funktionen

- **Bestellung aufgeben:** Gib die Anzahl von Standard- und Premiumtüren ein, die du bestellen möchtest.
- **Bestellungen ausgeben:** Lass dir alle Bestellungen in der Konsole anzeigen.
- **Lagerverwaltung:** Überwache den Lagerbestand und fülle ihn bei Bedarf auf.
- **Produktionssteuerung:** Steuere die Produktion mithilfe von Robotern und Lieferanten.

---

## Benutzung

1. Starte das Programm in deiner IDE oder über die Konsole: java Fabrik
2. Folge den Anweisungen zur Eingabe der Bestellungen.
3. Die Bestellungen werden auf der Konsole ausgegeben.

---

## Klassenstruktur

Fabrik: Die Hauptklasse, die Bestellungen verwaltet und Produktionsprozesse steuert.
Bestellung: Speichert Informationen zu Bestellungen wie Bestellnummer, Türanzahl und Produktionszeit.
Produkt: Die Basisklasse für Standard- und Premiumtüren.
Standardtür: Klasse für Standardtüren.
Premiumtür: Klasse für Premiumtüren.
Lager: Verwaltet den Materialbestand und Lagerauffüllungen.
Roboter: Steuert verschiedene Produktionsroboter, z. B. für die Holzbearbeitung.
Lieferant: Kümmert sich um die Lieferung von Materialien.

---

## Tests

Eine separate Testklasse ist enthalten, um die Funktionalität aller Klassen zu überprüfen. 
Führe die Tests in deiner IDE aus, um sicherzustellen, dass alle Funktionen korrekt arbeiten.
