package GameFrame;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author sfina
 */

//MVC View
public class ScorePanel extends JPanel {

    static JLabel Status = new JLabel();
    GamePanel gridPane;
    JLabel countdownLabel;

    public ScorePanel() {
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setLayout(new GridLayout(0, 2, 0, 0));
        Status.getText();

        this.setBackground(new Color(229,193,255));
        add(Status);

    }

}
