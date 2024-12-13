
/**
 * Die Klasse Lager implementiert die Funktionalität eines Lagers,
 * in dem die für die Produktion benötigten Materialien gelagert werden.
 *
 * @author Gruppe 17
 * @version 08.12.2024
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

    /**
     * Konstruktor der Klasse Lager
     */
    public Lager() {

        this.vorhandeneHolzeinheiten = MAXHOLZEINHEITEN;
        this.vorhandeneSchrauben = MAXSCHRAUBEN;
        this.vorhandeneFarbeeinheiten = MAXFARBEEINHEITEN;
        this.vorhandeneKartoneinheiten = MAXKARTONEINHEITEN;
        this.vorhandeneGlaseinheiten = MAXGLASEINHEITEN;

        lieferant = new Lieferant();
    }

    /**
     * Methode gibBeschaffungsZeit liefert die Beschaffungszeit in Tagen:
     * 0 Tage, wenn alle Materialien vorhanden sind und
     * 2 Tage, wenn diese nachbestellt werden müssen
     *
     * @param Bestellung: eine Kundenbestellung mit allen bestellten Produkten im
     *                    Array of Produkt
     * @return: Beschaffungszeit in Tagen
     */
    public int gibBeschaffungsZeit(Bestellung kundenBestellung) {

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

        // Wenn die benötigten Matierialien für die Bestellung alle vorhanden sind...
        if (benoetigteHolzeinheiten <= this.vorhandeneHolzeinheiten &&
                benoetigteSchrauben <= this.vorhandeneSchrauben &&
                benoetigteFarbeeinheiten <= this.vorhandeneFarbeeinheiten &&
                benoetigteKartoneinheiten <= this.vorhandeneKartoneinheiten &&
                benoetigteGlaseinheiten <= this.vorhandeneGlaseinheiten) {
            return 0; // .. keine 2 Tage zusätzlich
        }

        return 2; // .. sonst 2 Tage für Nachbestellung der Materialien beim Lieferanten
    }

    /**
     * Methode lagerAuffuellen bestellt alle Materialien nach, so dass
     * das Lager wieder voll ist.
     * 
     */
    public void lagerAuffuellen() {

        int zuBestellendeHolzeinheiten = MAXHOLZEINHEITEN - vorhandeneHolzeinheiten;
        int zuBestellendeSchrauben = MAXSCHRAUBEN - vorhandeneSchrauben;
        int zuBestellendeFarbeeinheiten = MAXFARBEEINHEITEN - vorhandeneFarbeeinheiten;
        int zuBestellendeKartoneinheiten = MAXKARTONEINHEITEN - vorhandeneKartoneinheiten;
        int zuBestellendeGlaseinheiten = MAXGLASEINHEITEN - vorhandeneGlaseinheiten;

        // Bestellung der Materialien beim Lieferanten und gleichzeitig sicherstellen,
        // dass die Bestellung auch geklappt hat (wenn die Methode des Lieferanten true
        // zurückgibt)
        if (lieferant.wareBestellen(zuBestellendeHolzeinheiten, zuBestellendeSchrauben, zuBestellendeFarbeeinheiten,
                zuBestellendeKartoneinheiten, zuBestellendeGlaseinheiten)) {
            vorhandeneHolzeinheiten = MAXHOLZEINHEITEN;
            vorhandeneSchrauben = MAXSCHRAUBEN;
            vorhandeneFarbeeinheiten = MAXFARBEEINHEITEN;
            vorhandeneKartoneinheiten = MAXKARTONEINHEITEN;
            vorhandeneGlaseinheiten = MAXGLASEINHEITEN;
            System.out.println("Lager wurde wieder aufgefüllt!");
        }

        else {
            System.out.println("Ware konnte nicht nachbestellt werden!");
        }
    }

    /**
     * Methode gibt den Zustand der Materialien auf Lager an
     */
    public void lagerBestandAusgeben() {
        System.out.println("Lagerbestand:");
        System.out.println("Vorhandene Holzeinheiten: " + vorhandeneHolzeinheiten +
                " Vorhandene Schrauben: " + vorhandeneSchrauben +
                " Vorhandene Farbeeinheiten: " + vorhandeneFarbeeinheiten +
                " Vorhandene Kartoneinheiten: " + vorhandeneKartoneinheiten +
                " Vorhandene Glaseinheiten: " + vorhandeneGlaseinheiten + "\n\n");
    }

    /**
     * Methode wareLiefern:
     * Startet den Lieferanten-Thread und aktualisiert die Lagerbestände auf maximale Werte.
     */
    public void wareLiefern() {
        // Startet den Lieferant-Thread
        lieferant.start();

        // Aktualisiert die Lagerbestände
        vorhandeneHolzeinheiten = MAXHOLZEINHEITEN;
        vorhandeneSchrauben = MAXSCHRAUBEN;
        vorhandeneFarbeeinheiten = MAXFARBEEINHEITEN;
        vorhandeneKartoneinheiten = MAXKARTONEINHEITEN;
        vorhandeneGlaseinheiten = MAXGLASEINHEITEN;

        // Gibt eine Statusmeldung aus
        System.out.println();
        System.out.println("Meldung Lager: Die Rohstoffe sind im Lager eingetroffen und erfasst worden.");
        System.out.println();

        // Gibt den aktualisierten Lagerbestand aus
        lagerBestandAusgeben();
        System.out.println();
    }
}