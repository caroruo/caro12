
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Die Klasse GUI_Header beinhaltet das Registerblatt, den Titel "Aeki", sowie
 * die Beschreibung "Process Management Tool"
 *
 * @author (Gruppe 16) 
 * @version (29.12.2022)
 */

public class GUI_Header {
    // Erzeugen der Panels für den Header
    private JPanel header = new JPanel();
    private JPanel registerPanel = new JPanel();
    private JPanel firmaPanel = new JPanel();
    private JPanel aktuellerContent = new JPanel();
    private WebsiteErsteller guiWebsite;

    // Konstruktor der Klasse GUI_Header
    public GUI_Header() {       
        // Die Layouts für die Panels des Headers bestimmen, also wie die Panels aufgebaut sind
        header.setLayout(new BorderLayout());
        registerPanel.setLayout(new FlowLayout());
        firmaPanel.setLayout(new BoxLayout(firmaPanel, BoxLayout.Y_AXIS));
        
        registerErstellen(registerPanel);
        firmaTitelErstellen(firmaPanel);
        
        // registerPanel und firmaPanel dem Header hinzufügen
        header.add(registerPanel, BorderLayout.NORTH);
        header.add(firmaPanel, BorderLayout.SOUTH);
    }
    
    // Diese Methode erstellt das Register
    private void registerErstellen(JPanel panel) {
        // Die Buttons für den Header initialisieren
        JButton produkte = new JButton("Produkte");
        JButton fabrik = new JButton("Fabrik");
        JButton lager = new JButton("Lager");
        JButton bestellungen = new JButton("Bestellungen");
        
        // Farbe der Registerbuttons bestimmen
        produkte.setForeground(Color.black);
        fabrik.setForeground(Color.black);
        lager.setForeground(Color.black);
        bestellungen.setForeground(Color.black);
        
        // Hier wird bestimmt, was passiert, wenn man auf den jeweiligen Button klickt, also welches Register dabei aufgerufen wird
        produkte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiWebsite.setzeContent("GUI_Produkte");
            }
        });
        
        bestellungen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiWebsite.setzeContent("GUI_Bestellungen");
            }
        });
        
        lager.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiWebsite.setzeContent("GUI_Lager");
            }
        });
        
        fabrik.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiWebsite.setzeContent("GUI_Fabrik");
            }
        });
        
        // Die Buttons werden dem Panel hinzugefügt
        panel.add(produkte);
        panel.add(fabrik);
        panel.add(lager);
        panel.add(bestellungen);
    }
    
    // Diese Methode gibt einfach den Titel aus
    private void firmaTitelErstellen(JPanel panel) {
        // Labels für den Namen Aeki und der Softwarebeschreibung initialisieren 
        JLabel aeki = new JLabel("Aeki Gruppe 17");
        JLabel softwareBeschreibung = new JLabel("Service Mitarbeiter Software");
        
        // Font und Positionen für den Header (Titel) bestimmen
        Font aekiFont = aeki.getFont();
        aeki.setAlignmentX(Component.CENTER_ALIGNMENT);
        aeki.setFont(new Font(aekiFont.getName(), aekiFont.getStyle(), 50));
        softwareBeschreibung.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Beschriftungen der Firma und der Software dem Firma Panel hinzufügen
        panel.add(aeki);
        panel.add(softwareBeschreibung);
    }
    
    // Gibt den Header aus
    public JPanel gibPanel() {
        return header;
    }
    
    public void setzteWebseite(WebsiteErsteller webseite) {
        guiWebsite = webseite;
    }
}