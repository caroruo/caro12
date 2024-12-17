
/**
 * Diese Klasse AekiSoftware dient dazu, den kombinierten, fertigen GUI auszuweisen.
 * 
 * @author (Aeki Gruppe 17) 
 * @version (17.12.2024)
 */
public class AekiSoftware
{
    //Hier werden die benötigten Klassen initialisiert
    private static GUI_Blackbox BLACKBOX = new GUI_Blackbox();
    private static GUI_Header HEADER = new GUI_Header();
    private static GUI_Footer FOOTER = new GUI_Footer();
    private static Fabrik FABRIK = new Fabrik();

    /**
    *Konstruktor der Klasse AekiSoftware
    */
    public AekiSoftware()
    {
        // Instanzvariable initialisieren
    }
    
    //Diese Methode erstellt eine neue Website mit den oben definierten Klassen
    public static void main(String[] args) {
        System.out.println("Aeki Smart Door Manufacturing wird gestartet");
        WebsiteErsteller website = new WebsiteErsteller(BLACKBOX, HEADER, FOOTER, FABRIK);
        HEADER.setzteWebseite(website);
    }
}