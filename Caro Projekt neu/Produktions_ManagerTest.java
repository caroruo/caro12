
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testklasse für die Klasse Produktions_Manager.
 * Überprüft den Produktionsablauf für Bestellungen mit Standard- und Premiumtüren.
 * 
 * @author Gruppe 17
 * @version 07.12.2024
 */
public class Produktions_ManagerTest {
    private Produktions_Manager produktionsManager;
    private Lager lager;
    private Fabrik fabrik;

    /**
     * Set-Up-Methode: Initialisiert den Produktions_Manager, das Lager und die Fabrik.
     */
    @BeforeEach
    public void setUp() {
        lager = new Lager();
        fabrik = new Fabrik();
        produktionsManager = new Produktions_Manager(lager, fabrik);
    }
    
    /**
     * Testet die Verarbeitung einer Bestellung mit Standardtüren.
     */
    @Test
    public void testProduktionStandardtuer() {
        Bestellung bestellung = new Bestellung(3, 0, 1);
        produktionsManager.fuegeZuVerarbeitendeBestellungenHinzu(bestellung);

        produktionsManager.start();

        try {
            Thread.sleep(3000); //Bearbeitungszeit
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Produkt produkt : bestellung.liefereBestellteProdukte()) {
            assertEquals(Produkt.zustandProduktionFertig, produkt.aktuellerZustand(), "Standardtür sollte fertig produziert sein.");
        }
    }

    /**
     * Testet die Verarbeitung einer Bestellung mit Premiumtüren.
     */
    @Test
    public void testProduktionPremiumtuer() {
        Bestellung bestellung = new Bestellung(0, 2, 2);
        produktionsManager.fuegeZuVerarbeitendeBestellungenHinzu(bestellung);

        produktionsManager.start();

        try {
            Thread.sleep(5000); //Bearbeitungszeit
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Produkt produkt : bestellung.liefereBestellteProdukte()) {
            assertEquals(Produkt.zustandProduktionFertig, produkt.aktuellerZustand(), "Premiumtür sollte fertig produziert sein.");
        }
    }

    /**
     * Testet die Verarbeitung einer gemischten Bestellung (Standard- und Premiumtüren).
     */
    @Test
    public void testProduktionGemischteBestellung() {
        Bestellung bestellung = new Bestellung(2, 1, 3);
        produktionsManager.fuegeZuVerarbeitendeBestellungenHinzu(bestellung);

        produktionsManager.start();

        try {
            Thread.sleep(5000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Produkt produkt : bestellung.liefereBestellteProdukte()) {
            assertEquals(Produkt.zustandProduktionFertig, produkt.aktuellerZustand(), "Produkte der gemischten Bestellung sollten fertig produziert sein.");
        }
    }
}