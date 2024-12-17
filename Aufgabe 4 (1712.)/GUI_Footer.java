

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Die Klasse GUI_Footer beinhaltet den unten ersichtlichen Text, welcher im GUI in jeder Registerkarte ersichtlich ist
 *
 * @author (Aeki Gruppe 17) 
 * @version (16.12.2024)
 */
public class GUI_Footer
{
    JPanel footer = new JPanel();
    
    //Konstruktor der Klasse GUI_Footer zeugen". Hier wird auch das Layout des Footers bestimmt
    public GUI_Footer()
    {
        footerErzeugen();
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
    }

    //Methode um den Footer zu erzeugen
    private JPanel footerErzeugen() {
        //Layout für Footer bestimmen
        footer.setLayout(new BorderLayout());
        
        //Labels für Footer initialisieren
        JLabel message1 = new JLabel("Bei einer Fehlermeldung bitte wie folgt melden:");
        JLabel message2 = new JLabel("über unsere Email: gruppe17@aeki.ch");
        JLabel message3 = new JLabel("oder per Telefon +41 12 345 67 89");
        
        //Position der Labels bestimmen
        message1.setAlignmentX(Component.CENTER_ALIGNMENT);
        message2.setAlignmentX(Component.CENTER_ALIGNMENT);
        message3.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //Labels dem "Footer" Panel hinzufügen
        footer.add(message1, BorderLayout.NORTH);
        footer.add(message2, BorderLayout.CENTER);
        footer.add(message3, BorderLayout.SOUTH);
        
        return footer;
    }
    
    //Gibt den footer aus
    public JPanel gibPanel(){
        return footer;
    }
}