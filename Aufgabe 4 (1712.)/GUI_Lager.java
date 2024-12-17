import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;

/**
 * Die Klasse GUI_Lager bestimmt, was im Registerblatt "Lager" zu sehen ist, welche an die Klasse "Webseite" weitergegeben wird
 *
 * @author Gruppe 17
 * @version 16.12.2024
 */
public class GUI_Lager extends Webseite {
    private String maxHolzeinheiten;
    private String maxSchrauben;
    private String maxFarbeinheiten;
    private String maxKartoneinheiten;
    private String maxGlaseinheiten;
    private ArrayList<Integer> bestand;
    private Lager lager;

    // Konstruktor der Klasse GUI_Lager
    public GUI_Lager(Lager lager) {
        this.lager = lager;

        // Lagerkapazitäten initialisieren
        maxHolzeinheiten = Integer.toString(lager.MAXHOLZEINHEITEN);
        maxSchrauben = Integer.toString(lager.MAXSCHRAUBEN);
        maxFarbeinheiten = Integer.toString(lager.MAXFARBEEINHEITEN);
        maxKartoneinheiten = Integer.toString(lager.MAXKARTONEINHEITEN);
        maxGlaseinheiten = Integer.toString(lager.MAXGLASEINHEITEN);

        // Lagerbestand abrufen
        bestand = lager.lagerBestandAusgeben();

        // Inhalt wird mit "contentErzeugen" erzeugt
        contentErzeugen();
    }

    // Methode um den Content für das GUI_Lager zu erstellen und zu bestimmen
    private void contentErzeugen() {
        JPanel unteresPanel = new JPanel(new GridLayout(2, 2));
        JPanel buttonsPanel = new JPanel(new BorderLayout());
        JPanel kapazitätPanel = new JPanel(new GridLayout(6, 2));
        JPanel bestandPanel = new JPanel(new GridLayout(6, 2));
        JPanel bestellenPanel = new JPanel(new GridLayout(6, 2));

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel lagerTitel = new JLabel("Lager");
        lagerTitel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel kapazitätTitel = new JLabel("Kapazität");
        JLabel kapazitätHolz = new JLabel("Holz:");
        JLabel kapazitätHolzAnzahl = new JLabel(maxHolzeinheiten);
        JLabel kapazitätSchrauben = new JLabel("Schrauben:");
        JLabel kapazitätSchraubenAnzahl = new JLabel(maxSchrauben);
        JLabel kapazitätGlas = new JLabel("Glas:");
        JLabel kapazitätGlasAnzahl = new JLabel(maxGlaseinheiten);
        JLabel kapazitätFarbe = new JLabel("Farbe:");
        JLabel kapazitätFarbeAnzahl = new JLabel(maxFarbeinheiten);
        JLabel kapazitätKarton = new JLabel("Karton:");
        JLabel kapazitätKartonAnzahl = new JLabel(maxKartoneinheiten);

        JLabel bestandTitel = new JLabel("Bestand");
        JLabel bestandHolz = new JLabel("Holz:");
        JLabel bestandHolzAnzahl = new JLabel(bestand.size() > 0 ? bestand.get(0).toString() : "0");
        JLabel bestandSchrauben = new JLabel("Schrauben:");
        JLabel bestandSchraubenAnzahl = new JLabel(bestand.size() > 1 ? bestand.get(1).toString() : "0");
        JLabel bestandGlas = new JLabel("Glas:");
        JLabel bestandGlasAnzahl = new JLabel(bestand.size() > 4 ? bestand.get(4).toString() : "0");
        JLabel bestandFarbe = new JLabel("Farbe:");
        JLabel bestandFarbeAnzahl = new JLabel(bestand.size() > 2 ? bestand.get(2).toString() : "0");
        JLabel bestandKarton = new JLabel("Karton:");
        JLabel bestandKartonAnzahl = new JLabel(bestand.size() > 3 ? bestand.get(3).toString() : "0");

        kapazitätPanel.add(kapazitätTitel);
        kapazitätPanel.add(new JLabel());
        kapazitätPanel.add(kapazitätHolz);
        kapazitätPanel.add(kapazitätHolzAnzahl);
        kapazitätPanel.add(kapazitätSchrauben);
        kapazitätPanel.add(kapazitätSchraubenAnzahl);
        kapazitätPanel.add(kapazitätGlas);
        kapazitätPanel.add(kapazitätGlasAnzahl);
        kapazitätPanel.add(kapazitätFarbe);
        kapazitätPanel.add(kapazitätFarbeAnzahl);
        kapazitätPanel.add(kapazitätKarton);
        kapazitätPanel.add(kapazitätKartonAnzahl);

        bestandPanel.add(bestandTitel);
        bestandPanel.add(new JLabel());
        bestandPanel.add(bestandHolz);
        bestandPanel.add(bestandHolzAnzahl);
        bestandPanel.add(bestandSchrauben);
        bestandPanel.add(bestandSchraubenAnzahl);
        bestandPanel.add(bestandGlas);
        bestandPanel.add(bestandGlasAnzahl);
        bestandPanel.add(bestandFarbe);
        bestandPanel.add(bestandFarbeAnzahl);
        bestandPanel.add(bestandKarton);
        bestandPanel.add(bestandKartonAnzahl);

        unteresPanel.add(kapazitätPanel);
        unteresPanel.add(bestandPanel);

        content.add(lagerTitel);
        content.add(unteresPanel);
    }

}
