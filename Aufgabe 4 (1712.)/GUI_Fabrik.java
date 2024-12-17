import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;

/**
 * Die Klasse "GUI_Fabrik" zeigt die Registerseite "Fabrik", welche an die Klasse "Webseite" weitergegeben wird
 *
 * @author Gruppe 17
 * @version 16.12.2024
 */
public class GUI_Fabrik extends Webseite {
    private Fabrik fabrik;

    // Konstruktor der Klasse GUI_Fabrik
    public GUI_Fabrik(Fabrik fabrik) {
        this.fabrik = fabrik;

        contentErzeugen();
    }

    // Diese Methode erzeugt den Content für das Register "Fabrik"
    private void contentErzeugen() {
        // Panel für Fabrik initialisieren
        JPanel produkteInAuftragGebenPanel = new JPanel();

        // Layout für Panels bestimmen, also wie die Panels aufgebaut sind
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        produkteInAuftragGebenPanel.setLayout(new GridLayout(3, 3));

        // Farben und Größen für Panels bestimmen
        content.setBackground(Color.black);
        content.setPreferredSize(new Dimension(0, 100));
        produkteInAuftragGebenPanel.setBackground(Color.white);
        produkteInAuftragGebenPanel.setPreferredSize(new Dimension(500, 500));

        // Labels für "Produkte in Auftrag geben" initialisieren
        JLabel fabrikTitel = new JLabel("Fabrik");

        JLabel produkteInAuftragGebenTitel = new JLabel("Produkte in Auftrag geben");
        JLabel produkteInAuftragLeereFlaeche1 = new JLabel("");
        JLabel produkteInAuftragLeereFlaeche2 = new JLabel("");
        JLabel produkteInAuftragGebenStandardtuer = new JLabel("Standardtüren:");
        JFormattedTextField anzahlStandardtuer = setzeJFormatTextField();
        JLabel produkteInAuftragLeereFlaeche3 = new JLabel("");
        JLabel produkteInAuftragGebenPremiumtuer = new JLabel("Premiumtüren:");
        JFormattedTextField anzahlPremiumtuer = setzeJFormatTextField();

        // Button für das Aufgeben des Auftrags initialisieren und bestimmen
        JButton aufgeben = new JButton("Aufgeben");
        aufgeben.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int standardtuer = validierungAuftrag(anzahlStandardtuer);
                int premiumtuer = validierungAuftrag(anzahlPremiumtuer);

                if (standardtuer >= 0 && premiumtuer >= 0) {
                    fabrik.bestellungAufgeben(standardtuer, premiumtuer);
                    dialogErstellen("Rückmeldung", "Auftrag wurde aufgegeben");
                }
            }
        });

        // Farben und Größen der Labels bestimmen
        fabrikTitel.setForeground(Color.white);
        Font fabrikTitelFont = fabrikTitel.getFont();
        fabrikTitel.setAlignmentX(Component.LEFT_ALIGNMENT);
        fabrikTitel.setFont(new Font(fabrikTitelFont.getName(), fabrikTitelFont.getStyle(), 30));
        fabrikTitel.setPreferredSize(new Dimension(0, 100));

        produkteInAuftragGebenTitel.setForeground(Color.black);
        produkteInAuftragLeereFlaeche1.setForeground(Color.black);
        produkteInAuftragLeereFlaeche2.setForeground(Color.black);
        produkteInAuftragGebenStandardtuer.setForeground(Color.black);
        anzahlStandardtuer.setForeground(Color.black);
        produkteInAuftragLeereFlaeche3.setForeground(Color.black);
        produkteInAuftragGebenPremiumtuer.setForeground(Color.black);
        anzahlPremiumtuer.setForeground(Color.black);

        aufgeben.setForeground(Color.black);

        // Labels den Panels hinzufügen
        produkteInAuftragGebenPanel.add(produkteInAuftragGebenTitel);
        produkteInAuftragGebenPanel.add(produkteInAuftragLeereFlaeche1);
        produkteInAuftragGebenPanel.add(produkteInAuftragLeereFlaeche2);
        produkteInAuftragGebenPanel.add(produkteInAuftragGebenStandardtuer);
        produkteInAuftragGebenPanel.add(anzahlStandardtuer);
        produkteInAuftragGebenPanel.add(produkteInAuftragLeereFlaeche3);
        produkteInAuftragGebenPanel.add(produkteInAuftragGebenPremiumtuer);
        produkteInAuftragGebenPanel.add(anzahlPremiumtuer);
        produkteInAuftragGebenPanel.add(aufgeben);

        // Panel "Produkte in Auftrag geben" dem Panel "Fabrik" hinzufügen
        content.add(fabrikTitel);
        content.add(produkteInAuftragGebenPanel);
    }

    // Hier wird das Minimum des Auftrages validiert. Falls der Auftrag das Minimum unterschreitet, erscheint die unten stehende Fehlermeldung
    public int validierungAuftrag(JFormattedTextField feld) {
        int aktuellerWert = Integer.parseInt(feld.getText());
        if (aktuellerWert < 0) {
            dialogErstellen("Fehlermeldung", "Dein eingegebener Wert ist zu tief");
            return -1;
        }
        return aktuellerWert;
    }

    // Hier wird das Minimum der eingegebenen Anzahl definiert (nur Zahlen können eingegeben werden, zu Beginn steht eine 0, etc.)
    public JFormattedTextField setzeJFormatTextField() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumIntegerDigits(0);
        NumberFormatter formatter = new NumberFormatter(numberFormat);
        formatter.setAllowsInvalid(false);
        JFormattedTextField jFormatText = new JFormattedTextField(formatter);
        jFormatText.setValue(0);
        return jFormatText;
    }
}