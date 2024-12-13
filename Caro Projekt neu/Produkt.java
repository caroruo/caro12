
/**
 * Klasse Produkt ist eine Superklasse für die Klassen Premiumtuer und
 * Standardtuer
 *
 * @author Group 17
 * @version 04.12.2024
 */
public class Produkt 
{
 /**
     * Variable zustand gibt den Zustand des bestellten Produktes an
     * Mögliche Zustände:
     * 0: Bestellt
     * 1: In Produktion
     * 2: Bereit für Auslieferung
     * 3: Ausgeliefert
     */
    private int zustand;
    private LinkedList <Roboter> produktionsAblauf;
    public final static int zustandBestellt = 1;
    public final static int zustandInProduktion = 2;
    public final static int zustandFertigProduziert = 3;

    /**
     * Konstruktor
     */
    public Produkt()
    {
        zustand = 1;
        produktionsAblauf = new LinkedList <Roboter> ();
    }

    /**
     * Zustand des Produkts wird geändert
     *
     * @param  zustand : neuer Zustand des Produkt
     */
    public void zustandAendern(int zustand)
    {
        this.zustand = zustand;
    }
    
     /**
     * Aktueller Zustand des Produkts wird ausgegeben
     *
     * @return zustand Zustand des Produkt wird ausgegeben
     */   
    public int aktuellerZustand()
    {
        return zustand;
    }
    
    /**
     * Ablauf der Produktion wird gesetzt
     *
     * @return zustand : neuer Zustand des Produkts
     */
    public void setzeProduktionsablauf(LinkedList <Roboter> produktionsAblauf)
    {
        this.produktionsAblauf = produktionsAblauf;
    }
    
    /**
     * Methode naechsteProduktionsStation
     * 
     * @return zustand : neuer Zustand des Produkts
     */
    public void naechsteProduktionsStation()
    {
        //mit .remove wird das erste Element der Liste entfernt und in die Methode fuegeProduktHinzu in der Klasse Roboter zurückgegeben

        if (produktionsAblauf.isEmpty())
        {
            this.zustand = zustandFertigProduziert;
        }
        else
        {
            Roboter nächsteStation = produktionsAblauf.remove();
            nächsteStation.fuegeProduktHinzu(this);
            this.zustand = zustandInProduktion;
        }
    }
}