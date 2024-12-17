
import javax.swing.*;

/**
 * Die Klasse Webseite hilft im Hintergrund, den Content der anderen Klassen zu bündeln und die Dialoge zu definieren.
 *
 * @author (Aeki Gruppe 17) 
 * @version (17.12.2024)
 */
public abstract class Webseite
{
    protected JPanel content = new JPanel();
    
    //Konstruktor der Klasse Webseite
    public Webseite() {
        contentErzeugen();
    }
    
    //Diese Klasse hat keinen Content, die anderen haben jedoch einen
    private void contentErzeugen(){
        System.out.println("Super Klasse");
    }
    
    //Hier wird der content ausgegeben
    public JPanel gibContent(){
        return content;
    }
    
    //Dialoge (Meldungen) werden erstellt und definiert (Grösse, Sichtbarkeitund)
    public JDialog dialogErstellen(String titel, String meldung){
        JDialog dialog = new JDialog();
        dialog.setTitle(titel);
        dialog.add(new JLabel(meldung));
        dialog.setSize(400, 400);
        dialog.setVisible(true);
        return dialog;
    }
}