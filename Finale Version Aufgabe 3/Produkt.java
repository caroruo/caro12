import java.util.LinkedList;

/**
 * Klasse Produkt ist eine Superklasse für die Klassen Premiumtuer und
 * Standardtuer
 * 
 * @author Gruppe 17 
 * @version 15.12.2024
 */
public class Produkt {
    /**
     * Variable zustand gibt den Zustand des bestellten Produktes an
     * Mögliche Zustände:
     * 0: Bestellt
     * 1: In Produktion
     * 2: Bereit für Auslieferung
     * 3: Ausgeliefert
     */
    private int zustand;
    private LinkedList<Roboter> produktionsAblauf;

    public final static int ZUSTAND_BESTELLT = 0;
    public final static int ZUSTAND_IN_PRODUKTION = 1;
    public final static int ZUSTAND_BEREIT_FUER_AUSLIEFERUNG = 2;
    public final static int ZUSTAND_AUSGELIEFERT = 3;

    /**
     * Konstruktor
     */
    public Produkt() {
        zustand = ZUSTAND_BESTELLT;
        produktionsAblauf = new LinkedList<Roboter>();
    }

    public boolean istProduktionFertig() {
        return zustand == ZUSTAND_BEREIT_FUER_AUSLIEFERUNG || zustand == ZUSTAND_AUSGELIEFERT;
    }

    /**
     * Zustand des Produkts wird geändert
     *
     * @param zustand : neuer Zustand des Produkt
     */
    public void zustandAendern(int zustand) {
        this.zustand = zustand;
    }

    /**
     * Aktueller Zustand des Produkts wird ausgegeben
     *
     * @return zustand Zustand des Produkt wird ausgegeben
     */
    public int aktuellerZustand() {
        return zustand;
    }

    /**
     * Ablauf der Produktion wird gesetzt
     *
     * @return zustand : neuer Zustand des Produkts
     */
    public void setzeProduktionsablauf(LinkedList<Roboter> produktionsAblauf) {
        this.produktionsAblauf = produktionsAblauf;
    }

    /**
     * Methode naechsteProduktionsStation
     * 
     * @return zustand : neuer Zustand des Produkts
     */
    public void naechsteProduktionsStation() {
        // mit .remove wird das erste Element der Liste entfernt und in die Methode
        // fuegeProduktHinzu in der Klasse Roboter zurückgegeben

        if (produktionsAblauf.isEmpty()) {
            if (zustand != ZUSTAND_AUSGELIEFERT) {
                zustand = ZUSTAND_BEREIT_FUER_AUSLIEFERUNG;
            }
        } else {
            Roboter nächsteStation = produktionsAblauf.remove();
            nächsteStation.fuegeProduktHinzu(this);
            this.zustand = ZUSTAND_IN_PRODUKTION;
        }
    }
}