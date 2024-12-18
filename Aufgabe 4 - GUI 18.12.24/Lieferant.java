import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Diese Klasse wird verwendet, um die Lieferzeiten des Lieferanten und die
 * Bestellung über diesen zu simulieren.
 *
 * @author Group 17
 * @version 15.12.2024
 */
public class Lieferant extends Thread {
    // Datenobjekt für die Materialien einer Lieferung
    private class Lieferung {
        public int holzEinheiten;
        public int schrauben;
        public int farbEinheiten;
        public int kartonEinheiten;
        public int glasEinheiten;

        public Lieferung(int holzEinheiten, int schrauben, int farbEinheiten, int kartonEinheiten, int glasEinheiten) {
            this.holzEinheiten = holzEinheiten;
            this.schrauben = schrauben;
            this.farbEinheiten = farbEinheiten;
            this.kartonEinheiten = kartonEinheiten;
            this.glasEinheiten = glasEinheiten;
        }
    }

    // Queue für die Lieferungen
    private Queue<Lieferung> lieferungen = new LinkedList<>();
    private Lager lager;

    /**
     * Konstruktor der Klasse Lieferant
     */
    public Lieferant(Lager lager) {
        this.lager = lager;
    }

    /**
     * Simuliert die Lieferung mit einer Verzögerung von 48 Sekunden
     * (2 Tage, wobei 1 Stunde = 1 Sekunde).
     */
    @Override
    public void run() {
        while (true) {
            Lieferung lieferung = lieferungen.poll();

            if (lieferung == null) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace(); // nicht schlimm falls dies passiert, sollte aber nicht passieren
                }
                continue;
            }
            try {
                System.out.println("Lieferung gestartet...");
                System.out.println("Lieferzeit: 2 Tage (48 Sekunden simuliert).");

                // Simuliert die Lieferverzögerung von 48 Sekunden
                Thread.sleep(48 * 1000);

                final int holzEinheiten = lieferung.holzEinheiten;
                final int schrauben = lieferung.schrauben;
                final int farbEinheiten = lieferung.farbEinheiten;
                final int kartonEinheiten = lieferung.kartonEinheiten;
                final int glasEinheiten = lieferung.glasEinheiten;

                System.out.println("Lieferung abgeschlossen: ");
                System.out.println("Gelieferte Artikel: ");
                System.out.println("Holz: " + holzEinheiten + ", Schrauben: " + schrauben + ", Farbe: " + farbEinheiten
                        + ", Karton: " + kartonEinheiten + ", Glas: " + glasEinheiten);
                lager.warenHinzufügen(holzEinheiten, schrauben, farbEinheiten, kartonEinheiten, glasEinheiten);
            } catch (InterruptedException e) {
                System.out.println("Lieferung wurde unterbrochen.");
            }
        }
    }

    /**
     * Mit dieser Methode wird die Bestellung bei dem Lieferanten simuliert
     * "True" wird zurückgegeben, wenn die Bestellung erfolgreich abgeschlossen wird
     * 
     * @param holzEinheiten   Anzahl bestellter Holzeinheiten
     * @param schrauben       Anzahl bestellter Schrauben
     * @param farbEinheiten   Anzahl bestellter Farbeinheiten
     * @param kartonEinheiten Anzahl bestellter Kartoneinheiten
     * @param glasEinheiten   Anzahl bestellter Glaseinheiten
     */
    public boolean wareBestellen(int holzEinheiten, int schrauben, int farbEinheiten, int kartonEinheiten,
            int glasEinheiten) {
        System.out.println("Folgende Bestellung erhalten: ");
        System.out.println(
                "Holz:" + holzEinheiten + ", Schrauben: " + schrauben + ", Farbe: " + farbEinheiten + ", Karton: "
                        + kartonEinheiten + ", Glas: " + glasEinheiten);
        Lieferung lieferung = new Lieferung(holzEinheiten, schrauben, farbEinheiten, kartonEinheiten, glasEinheiten);
        lieferungen.add(lieferung);
        return true;
    }
}