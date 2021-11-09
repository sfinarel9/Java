package StartScreen;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author sfina
 */
public class OptionsPanel extends JPanel implements ActionListener {

    JButton start = new JButton("Start");
    JButton exit = new JButton("Exit");
    String name;
    Boolean start_clicked = false;

    public OptionsPanel() {

        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(229, 193, 255));

//        start.addActionListener(this);
        start.setEnabled(true);
        add(start);
        exit.addActionListener(this);
        exit.setEnabled(true);
        add(exit);
    }

    public String getName() {
        return name;
    }

    public Boolean getStart_clicked() {
        return start_clicked;
    }

    public void setName(String Name) {
        this.name = name;
    }

    public void setStart_clicked(Boolean click) {
        this.start_clicked = click;
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        Object source = click.getSource();
        if (source == exit) {
            System.exit(0);
        }

    }
}
