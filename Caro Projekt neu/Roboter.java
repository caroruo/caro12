import java.util.LinkedList;

/**
 * Die Klasse Roboter verwaltet die Warteliste mit den aufgegebenen Bestellungen und ordnet deren Produktion an, sobald die Kapazität
 * dafür besteht.
 * 
 * @author Gruppe 17 
 * @version 08.12.2024
 */
public class Roboter extends Thread
{
    /** Variablen der Klasse Roboter werden instanziert.
     * Produktionszeiten werden bewusst in der Basisklasse definiert 
     * (generelles Konzept, das so von abgeleiteten Klassen vererbt werden kann).
     */ 
    private LinkedList <Produkt> warteschlange;
    protected String name;
    private int produktionsZeitStandard = 600000; // 10 Minuten
    private int produktionsZeitPremium = 1800000; // 30 Minuten
    
    /**
     * Konstruktor für Objekte der Klasse Roboter
     */
    public Roboter()
    {
        warteschlange = new LinkedList <Produkt>();
    }

    /**
     * Die Methode run überprüft laufend, ob neue Bestellungen eingetroffen sind. Ist ein Produkt in der Wartschlaufe, wofür 
     * Produktionskapazitäten bestehen, wird diese zur Produktion aufgegeben.
     */
    public void run()
    {
        while(true){
            //Ist eine neue Bestellung eingetroffen, dann
            //hole die nächste Bestellung und starte die Produktion
            if (!warteschlange.isEmpty())
            {
                Produkt produkt = warteschlange.remove();
                this.produziereProdukt(produkt);
            }
            //dann lass den Thread eine kurze Weile schlafen 
            try{
                Thread.sleep(200); 
            }
            catch (InterruptedException ie){
                ie.printStackTrace();
            }
        } 
    }
    
    /**
     * Die Methode fuegeProduktHinzu fügt der Warteliste die zur Produktion aufgegebenen Produkte hinzu.
     */
    public void fuegeProduktHinzu(Produkt produkt)
    {
        warteschlange.add(produkt);
    }
    
    /**
     * Die Methoden setzeProduktionsZeitXX setzt die Zeit der Produktion.
     * wir benutzen synchronized, damit jewils nur ein Thread die Methode verwendet.
     */
    public synchronized void setzeProduktionsZeitStandard(int zeit) {
        this.produktionsZeitStandard = zeit;
    }

    public synchronized void setzeProduktionsZeitPremium(int zeit) {
        this.produktionsZeitPremium = zeit;
    }
    
    /**
     * Die Methode gibNamen retourniert als String den Produktenamen
     * 
     * @ Produktname
     */
    public String gibNamen()
    {
        return name;
    }
    
    /**
     * Die Methode produziereProdukt nutzt einen Thread, um das Produkt aus der Bestellung zu produzieren.
     */
    public void produziereProdukt(Produkt produkt)
    {
        try
        {
            System.out.println("Roboter " + name + " hat mit der Produktion gestartet");
            if (produkt instanceof Standardtuer) {
                Thread.sleep(produktionsZeitStandard);
            } else if (produkt instanceof Premiumtuer) {
                Thread.sleep(produktionsZeitPremium);
            }    
            System.out.println("Roboter " + name + " hat die Produktion abgeschlossen");
            // Methode naechsteProduktionsStation() wird aufgerufen um nächsten Schritt zu beginnen
            produkt.naechsteProduktionsStation();
        }
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }
}
        