import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;

/**
 * Die Klasse "GUI_Produkte" zeigt die Registerseite "Produkte", welche an die Klasse "Webseite" weitergegeben wird.
 *
 * @author Gruppe 17
 * @version 16.12.2024
 */
public class GUI_Produkte extends Webseite {

    private Produktions_Manager produktionsManager;

    public GUI_Produkte(Produktions_Manager produktionsManager) {
        this.produktionsManager = produktionsManager;
        contentErzeugen();
    }

    private void contentErzeugen() {
        JPanel zuVerarbeitendeProduktePanel = new JPanel();
        JPanel inProduktionPanel = new JPanel();

        content.setLayout(new BorderLayout());
        zuVerarbeitendeProduktePanel.setLayout(new GridLayout(3, 2));
        inProduktionPanel.setLayout(new GridLayout(3, 2));

        content.setBackground(Color.black);
        zuVerarbeitendeProduktePanel.setBackground(Color.white);
        zuVerarbeitendeProduktePanel.setPreferredSize(new Dimension(400, 0));
        inProduktionPanel.setBackground(Color.white);
        inProduktionPanel.setPreferredSize(new Dimension(400, 0));

        JLabel produkteTitel = new JLabel("Produkte");

        JLabel zuVerarbeitendeProdukteTitel = new JLabel("In Warteschlange");
        JLabel zuVerarbeitendeProdukteStandardtuer = new JLabel("Standardtüren:");
        JLabel zuVerarbeitendeProdukteStandardtuerAnzahl = new JLabel("0"); // Placeholder
        JLabel zuVerarbeitendeProduktePremiumtuer = new JLabel("Premiumtüren:");
        JLabel zuVerarbeitendeProduktePremiumtuerAnzahl = new JLabel("0"); // Placeholder

        JLabel inProduktionTitel = new JLabel("In Produktion");
        JLabel inProduktionStandardtuer = new JLabel("Standardtüren:");
        JLabel inProduktionStandardtuerAnzahl = new JLabel("0"); // Placeholder
        JLabel inProduktionPremiumtuer = new JLabel("Premiumtüren:");
        JLabel inProduktionPremiumtuerAnzahl = new JLabel("0"); // Placeholder

        produkteTitel.setForeground(Color.white);
        produkteTitel.setBackground(Color.black);
        produkteTitel.setPreferredSize(new Dimension(0, 100));
        Font produkteTitelFont = produkteTitel.getFont();
        produkteTitel.setAlignmentX(Component.LEFT_ALIGNMENT);
        produkteTitel.setFont(new Font(produkteTitelFont.getName(), produkteTitelFont.getStyle(), 30));

        zuVerarbeitendeProdukteTitel.setForeground(Color.black);
        zuVerarbeitendeProdukteStandardtuerAnzahl.setForeground(Color.black);
        zuVerarbeitendeProduktePremiumtuer.setForeground(Color.black);
        zuVerarbeitendeProduktePremiumtuerAnzahl.setForeground(Color.black);

        inProduktionTitel.setForeground(Color.black);
        inProduktionStandardtuer.setForeground(Color.black);
        inProduktionStandardtuerAnzahl.setForeground(Color.black);
        inProduktionPremiumtuer.setForeground(Color.black);
        inProduktionPremiumtuerAnzahl.setForeground(Color.black);

        zuVerarbeitendeProduktePanel.add(zuVerarbeitendeProdukteTitel);
        zuVerarbeitendeProduktePanel.add(new JLabel());
        zuVerarbeitendeProduktePanel.add(zuVerarbeitendeProdukteStandardtuer);
        zuVerarbeitendeProduktePanel.add(zuVerarbeitendeProdukteStandardtuerAnzahl);
        zuVerarbeitendeProduktePanel.add(zuVerarbeitendeProduktePremiumtuer);
        zuVerarbeitendeProduktePanel.add(zuVerarbeitendeProduktePremiumtuerAnzahl);

        inProduktionPanel.add(inProduktionTitel);
        inProduktionPanel.add(new JLabel());
        inProduktionPanel.add(inProduktionStandardtuer);
        inProduktionPanel.add(inProduktionStandardtuerAnzahl);
        inProduktionPanel.add(inProduktionPremiumtuer);
        inProduktionPanel.add(inProduktionPremiumtuerAnzahl);

        content.add(produkteTitel, BorderLayout.NORTH);
        content.add(zuVerarbeitendeProduktePanel, BorderLayout.WEST);
        content.add(new JLabel(), BorderLayout.CENTER);
        content.add(inProduktionPanel, BorderLayout.EAST);
    }
}