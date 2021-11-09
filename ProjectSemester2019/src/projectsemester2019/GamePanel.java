package projectsemester2019;
/*
*
*author @marilena
*/
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    GridPanel gridPane;

    GamePanel(int num, String name) {
        this.setLayout(new BorderLayout(0, 0));
        gridPane = new GridPanel(num, name);
        add(BorderLayout.CENTER, gridPane);
    }

}
