
/**
 * Die Klasse Lager implementiert die Funktionalität eines Lagers,
 * in dem die für die Produktion benötigten Materialien gelagert werden.
 *
 * @author Gruppe 17
 * @version 15.12.2024
 */
public class Lager {
    private static final int MAXHOLZEINHEITEN = 1000;
    private static final int MAXSCHRAUBEN = 5000;
    private static final int MAXFARBEEINHEITEN = 1000;
    private static final int MAXKARTONEINHEITEN = 1000;
    private static final int MAXGLASEINHEITEN = 100;

    private int vorhandeneHolzeinheiten;
    private int vorhandeneSchrauben;
    private int vorhandeneFarbeeinheiten;
    private int vorhandeneKartoneinheiten;
    private int vorhandeneGlaseinheiten;

    private Lieferant lieferant;
    private boolean wareBestellt;

    /**
     * Konstruktor der Klasse Lager
     */
    public Lager() {
        this.vorhandeneHolzeinheiten = MAXHOLZEINHEITEN;
        this.vorhandeneSchrauben = MAXSCHRAUBEN;
        this.vorhandeneFarbeeinheiten = MAXFARBEEINHEITEN;
        this.vorhandeneKartoneinheiten = MAXKARTONEINHEITEN;
        this.vorhandeneGlaseinheiten = MAXGLASEINHEITEN;

        lieferant = new Lieferant(this);
        wareBestellt = false;
    }

    /**
     * Methode gibBeschaffungsZeit liefert die Beschaffungszeit in Tagen:
     * 0 Tage, wenn alle Materialien vorhanden sind und
     * 2 Tage, wenn diese nachbestellt werden müssen
     *
     * @param kundenBestellung: eine Kundenbestellung mit allen bestellten Produkten im Array of Produkt
     * @return: Beschaffungszeit in Tagen
     */
    public synchronized int gibBeschaffungsZeit(Bestellung kundenBestellung) {
        int benoetigteHolzeinheiten = 0;
        int benoetigteSchrauben = 0;
        int benoetigteFarbeeinheiten = 0;
        int benoetigteKartoneinheiten = 0;
        int benoetigteGlaseinheiten = 0;

        // Berechnung der benötigten Materialien
        for (Produkt produkt : kundenBestellung.gibBestellteProdukte()) {
            if (produkt instanceof Standardtuer) {
                benoetigteHolzeinheiten += Standardtuer.gibHolzeinheiten();
                benoetigteSchrauben += Standardtuer.gibSchrauben();
                benoetigteFarbeeinheiten += Standardtuer.gibFarbeinheiten();
                benoetigteKartoneinheiten += Standardtuer.gibKartoneinheiten();
            } else if (produkt instanceof Premiumtuer) {
                benoetigteHolzeinheiten += Premiumtuer.gibHolzeinheiten();
                benoetigteSchrauben += Premiumtuer.gibSchrauben();
                benoetigteFarbeeinheiten += Premiumtuer.gibFarbeinheiten();
                benoetigteKartoneinheiten += Premiumtuer.gibKartoneinheiten();
                benoetigteGlaseinheiten += Premiumtuer.gibGlaseinheiten();
            }
        }

        // Wenn die benötigten Materialien für die Bestellung alle vorhanden sind...
        if (benoetigteHolzeinheiten <= vorhandeneHolzeinheiten &&
            benoetigteSchrauben <= vorhandeneSchrauben &&
            benoetigteFarbeeinheiten <= vorhandeneFarbeeinheiten &&
            benoetigteKartoneinheiten <= vorhandeneKartoneinheiten &&
            benoetigteGlaseinheiten <= vorhandeneGlaseinheiten) {
            return 0; // ...keine zusätzliche Zeit notwendig
        }

        return 2; // ...sonst 2 Tage für Nachbestellung der Materialien beim Lieferanten
    }

    /**
     * Methode lagerAuffuellen bestellt alle Materialien nach, so dass
     * das Lager wieder voll ist.
     */
    public synchronized void lagerAuffuellen() {
        if (wareBestellt) {
            return;
        }

        int zuBestellendeHolzeinheiten = MAXHOLZEINHEITEN - vorhandeneHolzeinheiten;
        int zuBestellendeSchrauben = MAXSCHRAUBEN - vorhandeneSchrauben;
        int zuBestellendeFarbeeinheiten = MAXFARBEEINHEITEN - vorhandeneFarbeeinheiten;
        int zuBestellendeKartoneinheiten = MAXKARTONEINHEITEN - vorhandeneKartoneinheiten;
        int zuBestellendeGlaseinheiten = MAXGLASEINHEITEN - vorhandeneGlaseinheiten;

        // Bestellung der Materialien beim Lieferanten und sicherstellen, dass die Bestellung geklappt hat
        if (lieferant.wareBestellen(zuBestellendeHolzeinheiten, zuBestellendeSchrauben, zuBestellendeFarbeeinheiten,
                zuBestellendeKartoneinheiten, zuBestellendeGlaseinheiten)) {
            System.out.println("Ware bestellt!");
            wareBestellt = true;
            notifyAll(); // Benachrichtige wartende Threads, dass die Bestellung erfolgt ist
        } else {
            System.out.println("Ware konnte nicht nachbestellt werden!");
        }
    }

    /**
     * Methode warenHinzufügen fügt die Materialien dem Lager hinzu
     *
     * @param holzEinheiten:   Anzahl der Holzeinheiten
     * @param schrauben:       Anzahl der Schrauben
     * @param farbEinheiten:   Anzahl der Farbeinheiten
     * @param kartonEinheiten: Anzahl der Kartoneinheiten
     * @param glasEinheiten:   Anzahl der Glaseinheiten
     */
    public synchronized void warenHinzufügen(int holzEinheiten, int schrauben, int farbEinheiten, int kartonEinheiten,
            int glasEinheiten) {
        if (holzEinheiten < 0 || schrauben < 0 || farbEinheiten < 0 || kartonEinheiten < 0 || glasEinheiten < 0) {
            System.out.println("Fehler: Die Anzahl der Materialien kann nicht negativ sein!");
            return;
        }

        vorhandeneHolzeinheiten = Math.min(vorhandeneHolzeinheiten + holzEinheiten, MAXHOLZEINHEITEN);
        vorhandeneSchrauben = Math.min(vorhandeneSchrauben + schrauben, MAXSCHRAUBEN);
        vorhandeneFarbeeinheiten = Math.min(vorhandeneFarbeeinheiten + farbEinheiten, MAXFARBEEINHEITEN);
        vorhandeneKartoneinheiten = Math.min(vorhandeneKartoneinheiten + kartonEinheiten, MAXKARTONEINHEITEN);
        vorhandeneGlaseinheiten = Math.min(vorhandeneGlaseinheiten + glasEinheiten, MAXGLASEINHEITEN);

        wareBestellt = false;
        notifyAll(); // Benachrichtige wartende Threads, dass Materialien hinzugefügt wurden
    }

    /**
     * Methode reserviereWaren reserviert die Materialien für die Produktion
     *
     * @param holzEinheiten:   Anzahl der Holzeinheiten
     * @param schrauben:       Anzahl der Schrauben
     * @param farbEinheiten:   Anzahl der Farbeinheiten
     * @param kartonEinheiten: Anzahl der Kartoneinheiten
     * @param glasEinheiten:   Anzahl der Glaseinheiten
     */
    public synchronized void reserviereWaren(int holzEinheiten, int schrauben, int farbEinheiten, int kartonEinheiten,
            int glasEinheiten) throws IllegalArgumentException {
        if (holzEinheiten < 0 || schrauben < 0 || farbEinheiten < 0 || kartonEinheiten < 0 || glasEinheiten < 0) {
            throw new IllegalArgumentException("Die Anzahl der Materialien kann nicht negativ sein!");
        }

        if (vorhandeneHolzeinheiten < holzEinheiten || vorhandeneSchrauben < schrauben ||
            vorhandeneFarbeeinheiten < farbEinheiten || vorhandeneKartoneinheiten < kartonEinheiten ||
            vorhandeneGlaseinheiten < glasEinheiten) {
            throw new IllegalArgumentException("Nicht genügend Materialien auf Lager!");
        }

        vorhandeneHolzeinheiten -= holzEinheiten;
        vorhandeneSchrauben -= schrauben;
        vorhandeneFarbeeinheiten -= farbEinheiten;
        vorhandeneKartoneinheiten -= kartonEinheiten;
        vorhandeneGlaseinheiten -= glasEinheiten;
    }

    /**
     * Methode gibt den Zustand der Materialien auf Lager an
     */
    public synchronized void lagerBestandAusgeben() {
        System.out.println("Lagerbestand:");
        System.out.println("Holz: " + vorhandeneHolzeinheiten + "/" + MAXHOLZEINHEITEN);
        System.out.println("Schrauben: " + vorhandeneSchrauben + "/" + MAXSCHRAUBEN);
        System.out.println("Farbe: " + vorhandeneFarbeeinheiten + "/" + MAXFARBEEINHEITEN);
        System.out.println("Karton: " + vorhandeneKartoneinheiten + "/" + MAXKARTONEINHEITEN);
        System.out.println("Glas: " + vorhandeneGlaseinheiten + "/" + MAXGLASEINHEITEN);
    }

    /**
     * Methode istLagerVoll gibt an, ob das Lager voll ist
     *
     * @return true, wenn das Lager voll ist, sonst false
     */
    public synchronized boolean istLagerVoll() {
        return vorhandeneHolzeinheiten == MAXHOLZEINHEITEN &&
               vorhandeneSchrauben == MAXSCHRAUBEN &&
               vorhandeneFarbeeinheiten == MAXFARBEEINHEITEN &&
               vorhandeneKartoneinheiten == MAXKARTONEINHEITEN &&
               vorhandeneGlaseinheiten == MAXGLASEINHEITEN;
    }
}
