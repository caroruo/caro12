
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows; //optional

/**
 * Klasse LieferantTest
 *
 * @author Group 17
 * @version 15.12.2024
 */
public class LieferantTest {
    String nameTestClasse = "LieferantTest"; // Name der Testklasse

    /**
     * Konstruktor von LieferantTest
     */
    public LieferantTest() {
    }

    /**
     * Anweisungen vor jedem Testlauf
     */
    @BeforeEach
    public void setUp() {
        System.out.println("Testlauf " + nameTestClasse + " Start");
        System.out.println();
    }

    /**
     * Anweisungen nach jedem Testlauf
     */
    @AfterEach
    public void tearDown() {
        System.out.println();
        System.out.println("Testlauf " + nameTestClasse + " Ende");
        System.out.println("------------------------");
    }

    @Test
    /**
     * Testet wareBestellen()
     */
    public void testeWareBestellen() {
        // Instanzierung eines Lieferanten
        Lieferant testLieferant = new Lieferant(new Lager());
        testLieferant.start();

        // Überprüfen, ob die Bestellung erfolgreich aufgegeben werden kann
        assertTrue(testLieferant.wareBestellen(2, 4, 50, 3, 2));
        System.out.println("Test Methode wareBestellen erfolgreich.");
    }

    
    @Test
    /**
     * Testet die Lieferung (run-Methode) des Lieferanten.
     * Der Test überprüft, ob der Lieferant fehlende Materialien erfolgreich
     * liefert und das Lager auffüllt, sodass es am Ende voll ist.
     */
    public void testeLieferantLieferung() {
        Lager lager = new Lager();
        Lieferant lieferant = new Lieferant(lager);
    
        // Starte den Lieferanten
        lieferant.start();
    
        // Bestelle Ware
        lieferant.wareBestellen(500, 500, 100, 50, 10);
    
        // Überprüfe, ob die Lieferung angekommen ist
        long startTime = System.currentTimeMillis();
        boolean lieferungErfolgt = false;
    
        while (System.currentTimeMillis() - startTime < 60000) { // Maximal 60 Sekunden warten 
            if (lager.istLagerVoll()) {
                lieferungErfolgt = true;
                break;
            }
            try {
                Thread.sleep(1000); // Jede Sekunde prüfen
            } catch (InterruptedException e) {
                fail("Der Test wurde unterbrochen.");
            }
        }
    
        assertTrue(lieferungErfolgt, "Die Lieferung wurde nicht erfolgreich abgeschlossen.");
    }
}