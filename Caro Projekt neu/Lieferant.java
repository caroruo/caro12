/**
 * Diese Klasse wird verwendet, um die Lieferzeiten des Lieferanten und die
 * Bestellung über diesen zu simulieren.
 *
 * @author Group 17
 * @version 06.12.2024
 */
public class Lieferant extends Thread {
    public int holzEinheiten;
    public int schrauben;
    public int farbEinheiten;
    public int kartonEinheiten;
    public int glasEinheiten;
    
    /**
     * Konstruktor der Klasse Lieferant
     */
    public Lieferant(int holzEinheiten, int schrauben,int farbEinheiten, int kartonEinheiten,int glasEinheiten) {
        this.holzEinheiten = holzEinheiten;
        this.schrauben = schrauben;
        this.farbEinheiten = farbEinheiten;
        this.kartonEinheiten = kartonEinheiten;
        this.glasEinheiten = glasEinheiten;
    }
    
     /**
     * Simuliert die Lieferung mit einer Verzögerung von 48 Sekunden
     * (2 Tage, wobei 1 Stunde = 1 Sekunde).
     */
    @Override
    public void run() {
        try {
            System.out.println("Lieferung gestartet...");
            System.out.println("Lieferzeit: 2 Tage (48 Sekunden simuliert).");

            // Simuliert die Lieferverzögerung von 48 Sekunden
            Thread.sleep(48 * 1000);

            System.out.println("Lieferung abgeschlossen: ");
            System.out.println("Gelieferte Artikel: ");
            System.out.println("Holz: " + holzEinheiten + ", Schrauben: " + schrauben +", Farbe: " + farbEinheiten + ", Karton: " 
                + kartonEinheiten + ", Glas: " + glasEinheiten);
        } catch (InterruptedException e) {
            System.out.println("Lieferung wurde unterbrochen.");
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
    public boolean wareBestellen(int holzEinheiten, int schrauben, int farbEinheiten, int kartonEinheiten,int glasEinheiten) {
        System.out.println("Folgende Bestellung erhalten: ");
        System.out.println("Holz:" + holzEinheiten + ", Schrauben: " + schrauben + ", Farbe: " + farbEinheiten + ", Karton: " 
            + kartonEinheiten + ", Glas: " + glasEinheiten);
        return true; 
    }
}