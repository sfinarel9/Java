package projectsemester2019;
/*
*
*author @marilena
*/
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ScorePanel extends JPanel {

    static JLabel Status = new JLabel();
    GridPanel gridPane = new GridPanel(0, "");

    public ScorePanel() {
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setLayout(new GridLayout(0, 2, 0, 0));

        Status.setText("Score: " + gridPane.getScore());
        Status.setText("Time left: " + gridPane.getTime() + "sec.");
        Status.getText();

        add(Status);
    }

}
