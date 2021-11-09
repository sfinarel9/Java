package GameFrame;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author sfina
 */
//MVC Controller
public class GamePanel extends JPanel {

    GridPanel gridPane;

    public GamePanel(int num, String name,Controller cd) {
        this.setLayout(new BorderLayout(0, 0));
        gridPane = new GridPanel(num, name,cd);
        add(BorderLayout.CENTER, gridPane);
    }
}
