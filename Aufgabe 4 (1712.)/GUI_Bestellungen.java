
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Die Klasse GUI_Bestellungen bestimmt, was auf der Registerseite "Bestellungen" zu sehen ist, welche an die Klasse "Webseite" weitergegeben wird
 *
 * @author (Aeki Gruppe 17) 
 * @version (16.12.2024)
 */
public class GUI_Bestellungen extends Webseite{
    private String[][] bestellungen;

    //Konstruktor der Klasse GUI_Bestellungen
    public GUI_Bestellungen(String[][] bestellung) {
        this.bestellungen = bestellung;  

        contentErzeugen();
    }

    //Diese Methode definiert den Content der Bestellungen
    private void contentErzeugen(){
        //Layout für Panel bestimmen, also wie das Panel aufgebaut sind
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        //Farbe des Layout bestimmen
        content.setBackground(Color.black);
        
        //Label für den Titel initialisieren
        JLabel bestellungenTitel = new JLabel("Bestellungen");
        
        //Farben und Grösse und Position des Labels bestimmen
        bestellungenTitel.setForeground(Color.white);
        bestellungenTitel.setPreferredSize(new Dimension(0, 100));
        Font bestellungenTitelFont = bestellungenTitel.getFont();
        bestellungenTitel.setAlignmentX(Component.LEFT_ALIGNMENT);
        bestellungenTitel.setFont(new Font(bestellungenTitelFont.getName(),bestellungenTitelFont.getStyle(), 30));

        //Hier wird die Methode "tabelleErstellen" und das Label für den Titel dem "content" Panel hinzugefügt
        content.add(bestellungenTitel);
        content.add(tabelleErstellen());
    }

    //Hier wird die Tabelle erstellt und definiert
    public JScrollPane tabelleErstellen() {
        //die Spalten werden benannt
        String[] spalten = {"Bestellung", "Standardtür", "Premiumtür", 
                "Bestätigt", "Beschaffungszeit in h", "Lieferung in Tagen"};
        
        //Die Tabelle wird initialisiert
        JTable tabelle = new JTable(bestellungen, spalten);
        
        //Tabelle wird definiert (Grösse, Farbe, Position)
        tabelle.setEnabled(false);
        tabelle.getTableHeader().setBackground(new Color(50,84,107));
        tabelle.getTableHeader().setForeground(Color.white);
        tabelle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //Die scrolling Funktion wird initialisiert und zurückgegeben
        JScrollPane scrollPane = new JScrollPane(tabelle);
        return scrollPane;
    }
}