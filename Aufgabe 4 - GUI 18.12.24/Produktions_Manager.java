import java.util.LinkedList;

/**
 * Die Klasse Produktions_Manager arbeitet die eingegangenen Bestellungen ab,
 * indem sie die Produkte den verschiedenen Robotern zuweist.
 * 
 * Sie verwaltet zwei Listen: zu verarbeitende Bestellungen und Bestellungen,
 * die sich in der Produktion befinden.
 * 
 * @author Gruppe 17 
 * @version 15.12.2024
 */
public class Produktions_Manager extends Thread {
    // Instanzen der verschiedenen Roboter
    private Holzbearbeitungs_Roboter holzRoboter;
    // private Montage_Roboter montageRoboter;
    // private Lackier_Roboter lackierRoboter;
    // private Verpackungs_Roboter verpackungsRoboter;

    // Referenzen zur Fabrik und zum Lager
    private Fabrik meineFabrik;
    private Lager meinLager;

    // Listen für die Verwaltung der Bestellungen
    private LinkedList<Bestellung> zuVerarbeitendeBestellungen;
    private LinkedList<Bestellung> bestellungenInProduktion;

    /**
     * Konstruktor für Objekte der Klasse Produktions_Manager.
     * Initialisiert die Roboter, Fabrik, Lager und Bestelllisten.
     * 
     * @param meinLager   Referenz auf das Lager, um Materialien zu überprüfen.
     * @param meineFabrik Referenz auf die Fabrik, die die Bestellungen verwaltet.
     */
    public Produktions_Manager(Lager meinLager, Fabrik meineFabrik) {
        this.meinLager = meinLager;
        this.meineFabrik = meineFabrik;

        // Initialisierung und Start der Roboter
        holzRoboter = new Holzbearbeitungs_Roboter();
        // montageRoboter = new Montage_Roboter();
        // lackierRoboter = new Lackier_Roboter();
        // verpackungsRoboter = new Verpackungs_Roboter();

        holzRoboter.start();
        // montageRoboter.start();
        // lackierRoboter.start();
        // verpackungsRoboter.start();

        // Initialisierung der Bestelllisten
        zuVerarbeitendeBestellungen = new LinkedList<>();
        bestellungenInProduktion = new LinkedList<>();
    }

    /**
     * Hauptmethode zur Bearbeitung der Bestellungen.
     * Überprüft kontinuierlich die Listen und startet die Produktion.
     */
    public void run() {
        while (true) {
            // Überprüfen, ob es zu verarbeitende Bestellungen gibt
            while (!zuVerarbeitendeBestellungen.isEmpty()) {
                Bestellung bestellung = zuVerarbeitendeBestellungen.remove();
                this.starteProduktion(bestellung);
                bestellungenInProduktion.add(bestellung);
                starteProduktion(bestellung);
            }

            // Überprüfen, ob Bestellungen fertiggestellt wurden
            for (int i = 0; i < bestellungenInProduktion.size(); i++) {
                Bestellung bestellung = bestellungenInProduktion.get(i);
                boolean allesFertig = true;

                // Prüfen, ob alle Produkte der Bestellung fertig produziert sind
                for (Produkt produkt : bestellung.liefereBestellteProdukte()) {
                    if (!produkt.istProduktionFertig()) {
                        allesFertig = false;
                        break;
                    }
                }

                if (allesFertig) {
                    // Bestellung als abgeschlossen markieren
                    bestellung.setzeBestellungFertig(true);
                    System.out.println("[Produktions_Manager] Bestellung " + bestellung.gibBestellungsNr()
                            + " fertig produziert!");

                    // Bestellung aus der Produktionsliste entfernen
                    bestellungenInProduktion.remove(i);
                    i--; // Index anpassen, da die Liste verkürzt wurde
                }
            }

            // Thread pausieren, um andere Prozesse nicht zu blockieren
            try {
                Thread.sleep(200);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    /**
     * Fügt eine neue Bestellung zur Warteschlange hinzu.
     * 
     * @param bestellung Die neue Bestellung, die verarbeitet werden soll.
     */
    public void fuegeZuVerarbeitendeBestellungenHinzu(Bestellung bestellung) {
        zuVerarbeitendeBestellungen.add(bestellung);
        System.out.println("Neue Bestellung hinzugefügt: " + bestellung.gibBestellungsNr());
    }

    /**
     * Startet die Produktion einer Bestellung, indem Produkte den Robotern
     * zugewiesen werden.
     * 
     * @param bestellung Die Bestellung, deren Produkte produziert werden sollen.
     */
    private void starteProduktion(Bestellung bestellung) {
        int anzahlHolz = 0;
        int anzahlSchrauben = 0;
        int anzahlFarbe = 0;
        int anzahlKarton = 0;
        int anzahlGlas = 0;

        for (Produkt produkt : bestellung.liefereBestellteProdukte()) {
            if (produkt instanceof Standardtuer) {
                LinkedList<Roboter> ablauf = new LinkedList<>();
                ablauf.add(holzRoboter);
                // ablauf.add(montageRoboter);
                // ablauf.add(verpackungsRoboter);
                produkt.setzeProduktionsablauf(ablauf);

                anzahlHolz += Standardtuer.gibHolzeinheiten();
                anzahlSchrauben += Standardtuer.gibSchrauben();
                anzahlFarbe += Standardtuer.gibFarbeinheiten();
                anzahlKarton += Standardtuer.gibKartoneinheiten();
            } else if (produkt instanceof Premiumtuer) {
                LinkedList<Roboter> ablauf = new LinkedList<>();
                ablauf.add(holzRoboter);
                // ablauf.add(montageRoboter);
                // ablauf.add(lackierRoboter);
                // ablauf.add(verpackungsRoboter);
                produkt.setzeProduktionsablauf(ablauf);

                anzahlHolz += Premiumtuer.gibHolzeinheiten();
                anzahlSchrauben += Premiumtuer.gibSchrauben();
                anzahlFarbe += Premiumtuer.gibFarbeinheiten();
                anzahlKarton += Premiumtuer.gibKartoneinheiten();
                anzahlGlas += Premiumtuer.gibGlaseinheiten();
            }
        }

        // Materialien reservieren und Produktion starten falls genug Material vorhanden
        try {
            meinLager.reserviereWaren(anzahlHolz, anzahlSchrauben, anzahlFarbe, anzahlKarton, anzahlGlas);
            for (Produkt produkt : bestellung.liefereBestellteProdukte()) {
                produkt.naechsteProduktionsStation();
            }
        } catch (IllegalArgumentException e) {
            meinLager.lagerAuffuellen();
        }
    }
}