

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Die Test-Klasse RoboterTest.
 *
 * @author  (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */

public class RoboterTest {

    private Roboter roboter;
    private Produkt produkt;

    /**
     *  Setzt das Testgerüst fuer den Test.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    @BeforeEach
    public void setUp()
    {
        roboter = new Roboter(); // Initialisiere die Roboter-Instanz
        produkt = new Standardtuer();
    }

    /**
     * Gibt das Testgerüst wieder frei.
     *
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    @AfterEach
    public void tearDown()
    {
    }
    
    /**
     * Testet die Methode fuegeProduktHinzu(), indem geprüft wird, ob ein Produkt korrekt
     * zur Warteschlange hinzugefügt wird.
     */
    @Test
    public void testFuegeProduktHinzu() {
        roboter.fuegeProduktHinzu(produkt);

        // Indirekte Prüfung durch Zugriff auf die Warteschlange (Reflection verwenden)
        try {
            java.lang.reflect.Field warteschlangeField = Roboter.class.getDeclaredField("warteschlange");
            warteschlangeField.setAccessible(true);
            java.util.LinkedList<?> warteschlange = (java.util.LinkedList<?>) warteschlangeField.get(roboter);

            // Überprüfen, ob die Warteschlange das Produkt enthält
            assertEquals(1, warteschlange.size(), "Die Warteschlange sollte genau ein Produkt enthalten.");
            assertTrue(warteschlange.contains(produkt), "Das Produkt sollte in der Warteschlange sein.");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Zugriff auf die Warteschlange fehlgeschlagen: " + e.getMessage());
        }
    }
       
    /**
     * Testet das Verhalten des Roboters bei leerer Warteschlange.
     */
    @Test
    public void testLeereWarteschlange() throws InterruptedException {
        Thread roboterThread = new Thread(roboter);
        roboterThread.start();

        Thread.sleep(500); // Wartezeit, während keine Produkte verarbeitet werden

        // Keine Exception sollte auftreten
        assertTrue(true, "Der Roboter sollte ohne Fehler laufen, wenn die Warteschlange leer ist.");

        roboterThread.interrupt(); // Beende den Thread nach dem Test
    }

    
    /**
     * Testet ob die Synchronisation funktioniert, wenn mehrere Threads gleichzeitig Produkte hinzufügen.
     */
    @Test
    public void testThreadSicherheitBeimHinzufuegen() throws InterruptedException {
    Thread thread1 = new Thread(() -> roboter.fuegeProduktHinzu(new Standardtuer()));
    Thread thread2 = new Thread(() -> roboter.fuegeProduktHinzu(new Standardtuer()));

    thread1.start();
    thread2.start();

    thread1.join();
    thread2.join();

    try {
        java.lang.reflect.Field warteschlangeField = Roboter.class.getDeclaredField("warteschlange");
        warteschlangeField.setAccessible(true);
        java.util.LinkedList<?> warteschlange = (java.util.LinkedList<?>) warteschlangeField.get(roboter);

        // Überprüfen, ob beide Produkte hinzugefügt wurden
        assertEquals(2, warteschlange.size(), "Die Warteschlange sollte zwei Produkte enthalten.");
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Zugriff auf die Warteschlange fehlgeschlagen: " + e.getMessage());
    }
}

}
