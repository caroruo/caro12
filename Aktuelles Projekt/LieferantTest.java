

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows; //optional

/**
 * Klasse LieferantTest
 *
 * @author Group 17
 * @version 06.12.2024
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

        // Überprüfen, ob die Bestellung erfolgreich aufgegeben werden kann
        assertTrue(testLieferant.wareBestellen(2, 4, 50, 3, 2));
        System.out.println("Test Methode wareBestellen erfolgreich.");
    }

    @Test
    /**
     * Testet die Lieferung (run-Methode)
     */
    public void testeLieferung() {
        // Instanzierung eines Lieferanten
        Lieferant testLieferant = new Lieferant(new Lager());

        // Starte die Lieferung als Thread
        testLieferant.start();

        // Warte, bis die Lieferung abgeschlossen ist
        try {
            testLieferant.join(); // Wartet, bis der Thread abgeschlossen ist
        } catch (InterruptedException e) {
            fail("Der Lieferant-Thread wurde unerwartet unterbrochen.");
        }

        System.out.println("Test Lieferung erfolgreich abgeschlossen.");
    }
}