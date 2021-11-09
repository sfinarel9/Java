package StartScreen;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author sfina
 */
public class DetailsPanel extends JPanel {

    JLabel label = new JLabel("NAME:");
    JTextField text = new JTextField(25);

    public DetailsPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(label);
        add(text);
        setBackground(new Color(229, 193, 255));
    }

    public String getText() {
        return text.getText();
    }

}
