
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Die Klasse Startseite bestimmt, was auf der ersten Seite zu sehen ist, welche an die Klasse "Webseite" weitergegeben wird.
 *
 * @author Gruppe 17
 * @version 16.12.2024
 */
public class Startseite extends Webseite {
    private ArrayList<Integer> bestand;
    private Lager lager;
    private Produktions_Manager produktionsManager;

    public Startseite(Lager lager, Produktions_Manager produktionsManager) {
        this.lager = lager;
        this.produktionsManager = produktionsManager;
        bestand = lager.lagerBestandAusgeben();
        contentErzeugen();
    }

    private void contentErzeugen() {
        JPanel übersichtPanel = new JPanel();
        JPanel bestandPanel = new JPanel();

        content.setLayout(new BorderLayout());
        übersichtPanel.setLayout(new GridLayout(2, 1));
        bestandPanel.setLayout(new GridLayout(5, 2));

        content.setBackground(Color.gray);
        übersichtPanel.setBackground(Color.black);
        bestandPanel.setBackground(Color.white);

        JLabel bestandTitel = new JLabel("Bestand");
        JLabel bestandHolz = new JLabel("Holz:");
        JLabel bestandHolzAnzahl = new JLabel(bestand.get(0).toString());
        JLabel bestandSchrauben = new JLabel("Schrauben:");
        JLabel bestandSchraubenAnzahl = new JLabel(bestand.get(1).toString());

        bestandPanel.add(bestandTitel);
        bestandPanel.add(new JLabel());
        bestandPanel.add(bestandHolz);
        bestandPanel.add(bestandHolzAnzahl);
        bestandPanel.add(bestandSchrauben);
        bestandPanel.add(bestandSchraubenAnzahl);

        übersichtPanel.add(bestandPanel);
        content.add(übersichtPanel, BorderLayout.CENTER);
    }
}