import java.awt.*;
import javax.swing.*;

/**
 * Die Klasse WebsiteErsteller hilft dabei, die einzelnen Parts (Header, Footer, Blackbox, etc.) zu organisieren und in einem Fenster auszuweisen.
 *
 * @author Gruppe 16
 * @version 29.12.2022
 */
public class WebsiteErsteller {
    private final JFrame FENSTER = new JFrame("Aeki Software");
    private final GUI_Blackbox BLACKBOX;
    private final GUI_Header HEADER;
    private final GUI_Footer FOOTER;
    private final Fabrik FABRIK;
    private JPanel aktuellerContent;

    public WebsiteErsteller(GUI_Blackbox newBlackbox, GUI_Header newHeader, GUI_Footer newFooter, Fabrik newFabrik) {
        FENSTER.setSize(1000, 700);

        HEADER = newHeader;
        BLACKBOX = newBlackbox;
        FOOTER = newFooter;
        FABRIK = newFabrik;

        FENSTER.add(HEADER.gibPanel(), BorderLayout.NORTH);
        FENSTER.add(BLACKBOX.gibPanel(), BorderLayout.WEST);
        FENSTER.add(FOOTER.gibPanel(), BorderLayout.SOUTH);

        setzeContent("Start");
        FENSTER.setVisible(true);
    }

    public void setzeContent(String webseiteType) {
        JPanel neuerContent = new JPanel();

        if (aktuellerContent != null) {
            FENSTER.remove(aktuellerContent);
        }

        switch (webseiteType) {
            case "GUI_Produkte":
                neuerContent = new GUI_Produkte(new Produktions_Manager(new Lager(), FABRIK)).gibContent();
                break;
            default:
                neuerContent = new JPanel(); // Fallback
        }

        FENSTER.add(neuerContent, BorderLayout.CENTER);
        FENSTER.invalidate();
        FENSTER.validate();
        FENSTER.repaint();
        aktuellerContent = neuerContent;
    }
}
