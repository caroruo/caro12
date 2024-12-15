

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.lang.reflect.Field;

/**
 * Die Test-Klasse Produktions_ManagerTest.
 *
 * @author  Gruppe 17
 * @version 15.12.24
 */
/**
 * Testklasse für die Methoden der Klasse Produktions_Manager.
 */
public class Produktions_ManagerTest {

    private Produktions_Manager manager;
    private Lager lager;
    private Fabrik fabrik;
    private Thread managerThread;

    /**
     * Setup: Initialisiere den Produktions_Manager, das Lager und die Fabrik.
     */
    @BeforeEach
    public void setUp() {
        lager = new Lager();
        fabrik = new Fabrik();
        manager = new Produktions_Manager(lager, fabrik);
    }

    /**
     * Cleanup: Unterbreche den Manager-Thread, falls er gestartet wurde.
     */
    @AfterEach
    public void tearDown() {
        if (managerThread != null && managerThread.isAlive()) {
            managerThread.interrupt();
        }
    }
    
    /**
     * Prüft, ob die Methode fuegeZuVerarbeitendeBestellungenHinzu eine Bestellung korrekt zur 
     * zuVerarbeitendeBestellungen hinzufügt und die Liste entsprechend anpasst.
     */
    @Test
    public void testFuegeBestellungHinzu() {
        Bestellung bestellung = new Bestellung(2, 3, 101);
        manager.fuegeZuVerarbeitendeBestellungenHinzu(bestellung);

        // Überprüfung der Liste mittels Reflection
        try {
            Field bestellungenField = Produktions_Manager.class.getDeclaredField("zuVerarbeitendeBestellungen");
            bestellungenField.setAccessible(true);
            LinkedList<?> bestellungen = (LinkedList<?>) bestellungenField.get(manager);

            assertEquals(1, bestellungen.size(), "Die Liste der zu verarbeitenden Bestellungen sollte eine Bestellung enthalten.");
            assertTrue(bestellungen.contains(bestellung), "Die hinzugefügte Bestellung sollte in der Liste sein.");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Reflection-Fehler: " + e.getMessage());
        }
    }
    
    /**
     * Testet, dass starteProduktion die Bestellung aus der Liste 
     * zuVerarbeitendeBestellungen entfernt und korrekt zu bestellungenInProduktion hinzufügt.
     */
    @Test
    public void testProduktionStarten() {
    Bestellung bestellung = new Bestellung(2, 3, 102);
    manager.fuegeZuVerarbeitendeBestellungenHinzu(bestellung);
    manager.starteProduktion(bestellung);

        // Überprüfung der Produktionsliste mittels Reflection
        try {
            Field produktionField = Produktions_Manager.class.getDeclaredField("bestellungenInProduktion");
            produktionField.setAccessible(true);
            LinkedList<?> produktion = (LinkedList<?>) produktionField.get(manager);

            assertEquals(1, produktion.size(), "Die Liste der Bestellungen in Produktion sollte eine Bestellung enthalten.");
            assertTrue(produktion.contains(bestellung), "Die Bestellung sollte in der Liste der Bestellungen in Produktion sein.");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Reflection-Fehler: " + e.getMessage());
        }
    }
    
    /**
     * Überprüft, ob der Produktions_Manager Bestellungen an den Holzbearbeitungs_Roboter weiterleitet und korrekt verarbeitet.
     */
    @Test
    public void testIntegrationMitHolzRoboter() throws InterruptedException {
    Bestellung bestellung = new Bestellung(2, 0, 103);
    manager.fuegeZuVerarbeitendeBestellungenHinzu(bestellung);
    managerThread = new Thread(manager);
    managerThread.start();

        // Simulierte Verarbeitung
        Thread.sleep(1000);

        // Überprüfung, ob die Bestellung verarbeitet wurde
        try {
            Field produktionField = Produktions_Manager.class.getDeclaredField("bestellungenInProduktion");
            produktionField.setAccessible(true);
            LinkedList<?> produktion = (LinkedList<?>) produktionField.get(manager);

            assertTrue(produktion.contains(bestellung), "Die Bestellung sollte sich in der Produktion befinden.");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Reflection-Fehler: " + e.getMessage());
        }

        managerThread.interrupt(); // Beende den Thread nach dem Test

    }
}
