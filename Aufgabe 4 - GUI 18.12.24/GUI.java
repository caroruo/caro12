import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.awt.image.*;
import javax.imageio.*;

/**
 * GUI-Klasse zur Anzeige und Verwaltung von Bestellungen und Produkten.
 * 
 * @author Gruppe 17
 * @version 0.2.2
 */
public class GUI {
    private JFrame fenster;
    private JPanel mainPanel;
    private Fabrik fabrik;
    private JTextArea bestellungenTextArea;

    /**
     * Konstruktor der Klasse GUI
     */
    public GUI() {
        fabrik = new Fabrik();
        fensterErzeugen();
    }

    /**
     * Aktualisiert die Textausgabe der Bestellungen.
     */
    private void aktualisiereBestellungenAnzeigen() {
        bestellungenTextArea.setText(""); // Textbereich leeren
        for (Bestellung bestellung : fabrik.gibBestellungen()) {
            bestellungenTextArea.append("Bestellnummer: " + bestellung.gibBestellungsNr() 
                + ", Standardtüren: " + bestellung.gibAnzahlStandardTueren() 
                + ", Premiumtüren: " + bestellung.gibAnzahlPremiumTueren() 
                + ", Lieferzeit: " + bestellung.gibLieferzeit() + " Tage\n");
        }
    }

    /**
     * Erzeugt das Hauptfenster und die Layouts.
     */
    private void fensterErzeugen() {
        fenster = new JFrame("AEKI Management System");
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setLayout(new BorderLayout());

        // Menüleiste hinzufügen
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Navigation");
        JMenuItem startseiteItem = new JMenuItem("Startseite");
        JMenuItem bestellinfoItem = new JMenuItem("Bestellinformationen");
        JMenuItem produktinfoItem = new JMenuItem("Produktinformationen");

        startseiteItem.addActionListener(e -> zeigeStartseite());
        bestellinfoItem.addActionListener(e -> zeigeBestellseite());
        produktinfoItem.addActionListener(e -> zeigeProduktseite());

        menu.add(startseiteItem);
        menu.add(bestellinfoItem);
        menu.add(produktinfoItem);
        menuBar.add(menu);
        fenster.setJMenuBar(menuBar);

        // Hauptbereich erstellen
        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());

        // Startseite hinzufügen
        JPanel startseitePanel = new JPanel(new BorderLayout());
        JLabel startseiteLabel = new JLabel("Willkommen bei AEKI", JLabel.CENTER);
        startseiteLabel.setFont(new Font("Arial", Font.BOLD, 24));
        startseitePanel.add(startseiteLabel, BorderLayout.NORTH);

        try {
            BufferedImage img = ImageIO.read(new File("fabrik.jpg"));
            ImageIcon icon = new ImageIcon(img.getScaledInstance(600, 400, Image.SCALE_SMOOTH));
            JLabel bildLabel = new JLabel(icon);
            startseitePanel.add(bildLabel, BorderLayout.CENTER);
        } catch (Exception ex) {
            startseitePanel.add(new JLabel("Bild konnte nicht geladen werden"), BorderLayout.CENTER);
        }

        startseitePanel.add(erzeugeFooterPanel(), BorderLayout.SOUTH);
        mainPanel.add(startseitePanel, "Startseite");

        // Bestellseite hinzufügen
        JPanel bestellseitePanel = new JPanel(new BorderLayout());

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        headerPanel.setBackground(new Color(173, 216, 230));
        JButton bestellungAufgebenButton = new JButton("Bestellung aufgeben");
        JButton bestellstatusButton = new JButton("Bestellstatus überprüfen");
        JButton bestellungAnsehenButton = new JButton("Bestellung ansehen");
        JButton lagerbestandButton = new JButton("Lagerbestand einsehen");

        bestellungAufgebenButton.addActionListener(e -> bestellungAufgeben());
        bestellstatusButton.addActionListener(e -> bestellstatusPruefen());
        bestellungAnsehenButton.addActionListener(e -> aktualisiereBestellungenAnzeigen());
        lagerbestandButton.addActionListener(e -> lagerbestandAnsehen());

        headerPanel.add(bestellungAufgebenButton);
        headerPanel.add(bestellstatusButton);
        headerPanel.add(bestellungAnsehenButton);
        headerPanel.add(lagerbestandButton);

        bestellseitePanel.add(headerPanel, BorderLayout.NORTH);

        bestellungenTextArea = new JTextArea(10, 50);
        bestellungenTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(bestellungenTextArea);
        bestellseitePanel.add(scrollPane, BorderLayout.CENTER);

        bestellseitePanel.add(erzeugeFooterPanel(), BorderLayout.SOUTH);
        mainPanel.add(bestellseitePanel, "Bestellinformationen");

        // Produktseite hinzufügen
        JPanel produktseitePanel = new JPanel(new BorderLayout());

        JLabel produktseiteLabel = new JLabel("Produktinformationen", JLabel.CENTER);
        produktseiteLabel.setFont(new Font("Arial", Font.BOLD, 24));
        produktseitePanel.add(produktseiteLabel, BorderLayout.NORTH);

        JPanel produktbilderPanel = new JPanel(new GridLayout(1, 2));
        try {
            BufferedImage standardImg = ImageIO.read(new File("standardtuer.jpg"));
            ImageIcon standardIcon = new ImageIcon(standardImg.getScaledInstance(300, 400, Image.SCALE_SMOOTH));
            produktbilderPanel.add(new JLabel(standardIcon));

            BufferedImage premiumImg = ImageIO.read(new File("premiumtuer.jpg"));
            ImageIcon premiumIcon = new ImageIcon(premiumImg.getScaledInstance(300, 400, Image.SCALE_SMOOTH));
            produktbilderPanel.add(new JLabel(premiumIcon));
        } catch (Exception ex) {
            produktbilderPanel.add(new JLabel("Standardtür-Bild fehlt"));
            produktbilderPanel.add(new JLabel("Premiumtür-Bild fehlt"));
        }

        produktseitePanel.add(produktbilderPanel, BorderLayout.CENTER);
        produktseitePanel.add(erzeugeFooterPanel(), BorderLayout.SOUTH);

        mainPanel.add(produktseitePanel, "Produktinformationen");

        fenster.add(mainPanel, BorderLayout.CENTER);

        fenster.setSize(800, 600);
        fenster.setVisible(true);
        zeigeStartseite();
    }

    /**
     * Erzeugt das Footer-Panel mit "AEKI"-Label und Buttons.
     */
    private JPanel erzeugeFooterPanel() {
        JPanel footerPanel = new JPanel(new GridLayout(2, 1));
        footerPanel.setBackground(new Color(135, 206, 250));

        JLabel footerLabel = new JLabel("AEKI Management Systems", SwingConstants.CENTER);
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        footerPanel.add(footerLabel);

        JPanel footerButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        footerButtons.setBackground(new Color(135, 206, 250));

        JButton impressumButton = new JButton("Impressum");
        JButton kontaktButton = new JButton("Kontakt");
        JButton ueberUnsButton = new JButton("Über uns");

        impressumButton.addActionListener(e -> JOptionPane.showMessageDialog(fenster, "Hier könnte das Impressum sein.", "Impressum", JOptionPane.INFORMATION_MESSAGE));
        kontaktButton.addActionListener(e -> JOptionPane.showMessageDialog(fenster, "Kontaktieren Sie uns unter Gruppe17@aeki_türen.ch", "Kontakt", JOptionPane.INFORMATION_MESSAGE));
        ueberUnsButton.addActionListener(e -> JOptionPane.showMessageDialog(fenster, "Wir sind Gruppe 17 von Aeki", "Über uns", JOptionPane.INFORMATION_MESSAGE));

        footerButtons.add(impressumButton);
        footerButtons.add(kontaktButton);
        footerButtons.add(ueberUnsButton);

        footerPanel.add(footerButtons);

        return footerPanel;
    }

    /**
     * Zeigt die Startseite an.
     */
    private void zeigeStartseite() {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "Startseite");
    }

    /**
     * Zeigt die Bestellseite an.
     */
    private void zeigeBestellseite() {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "Bestellinformationen");
    }

    /**
     * Zeigt die Produktseite an.
     */
    private void zeigeProduktseite() {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "Produktinformationen");
    }

    /**
     * Aktion: Bestellung aufgeben.
     */
    private void bestellungAufgeben() {
        JDialog dialog = new JDialog(fenster, "Bestellung aufgeben", true);
        dialog.setLayout(new GridLayout(3, 2));

        JTextField standardTuerenField = new JTextField();
        JTextField premiumTuerenField = new JTextField();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Abbrechen");

        dialog.add(new JLabel("Standardtüren:"));
        dialog.add(standardTuerenField);
        dialog.add(new JLabel("Premiumtüren:"));
        dialog.add(premiumTuerenField);
        dialog.add(okButton);
        dialog.add(cancelButton);

        okButton.addActionListener(e -> {
                    try {
                        int standard = Integer.parseInt(standardTuerenField.getText());
                        int premium = Integer.parseInt(premiumTuerenField.getText());
                        fabrik.bestellungAufgeben(standard, premium);
                        dialog.dispose();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(dialog, "Bitte gültige Zahlen eingeben!", "Fehler",
                            JOptionPane.ERROR_MESSAGE);
                    }
            });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.pack();
        dialog.setLocationRelativeTo(fenster);
        dialog.setVisible(true);
    }

    /**
     * Aktion: Bestellstatus prüfen.
     */
    private void bestellstatusPruefen() {
        StringBuilder status = new StringBuilder();
        for (Bestellung bestellung : fabrik.gibBestellungen()) {
            status.append("Bestellung ").append(bestellung.gibBestellungsNr()).append(": ")
            .append(bestellung.gibBestellBestaetigung() ? "Bestätigt" : "Nicht bestätigt").append("\n");
        }
        JOptionPane.showMessageDialog(fenster, status.toString(), "Bestellstatus", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Aktion: Bestellungen ansehen.
     */
    private void bestellungenAnsehen() {
        StringBuilder details = new StringBuilder();
        for (Bestellung bestellung : fabrik.gibBestellungen()) {
            details.append("Bestellnummer: ").append(bestellung.gibBestellungsNr()).append(", Standardtüren: ")
            .append(bestellung.gibAnzahlStandardTueren()).append(", Premiumtüren: ")
            .append(bestellung.gibAnzahlPremiumTueren()).append(", Lieferzeit: ")
            .append(bestellung.gibLieferzeit()).append(" Tage\n");
        }
        JOptionPane.showMessageDialog(fenster, details.toString(), "Alle Bestellungen", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Aktion: Lagerbestand ansehen.
     */
    private void lagerbestandAnsehen() {
        int gesamtBestand = fabrik.gibLager().getGesamtBestand();
        JOptionPane.showMessageDialog(fenster, "Gesamtbestand im Lager: " + gesamtBestand, "Lagerbestand",
            JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
