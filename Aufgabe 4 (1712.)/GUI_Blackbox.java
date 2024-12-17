
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Diese Klasse GUI_Blackbox existiert nur aus designtechnischen Gründen und sorgt dafür, dass überall auf der linken Seite ein schwarzer Balken auftaucht.
 *
 * @author (Gruppe 16) 
 * @version (29.12.2022)
 */
public class GUI_Blackbox
{
    private JPanel panel = new JPanel();

    //Konstruktor der Klasse GUI_Blackbox, hier wird die Grösse und Farbe der Blackbox bestimmt
    public GUI_Blackbox()
    {
        panel.setPreferredSize(new Dimension(100, 100));
        panel.setBackground(Color.black);
    }

    //die Blackbox wird zurückgegeben
    public JPanel gibPanel()
    {
        return panel;
    }

}