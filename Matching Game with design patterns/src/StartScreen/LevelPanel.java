package StartScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author sfina
 */
public class LevelPanel extends JPanel implements ActionListener {

    JButton easy = new JButton("Easy");
    JButton medium = new JButton("Medium");
    JButton hard = new JButton("Hard");
    JButton veryhard = new JButton("Very Hard");
    JButton inst = new JButton("Instructions");

    String instructions_text = "Welcome to matching game!\nTry to pair all cards!\nYour goal is to win the Computer player!\nHave fun!!";
    int level;

    public LevelPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(229, 193, 255));
        add(easy, BorderLayout.NORTH);
        add(medium, BorderLayout.NORTH);
        add(hard, BorderLayout.NORTH);
        add(veryhard, BorderLayout.NORTH);
        add(inst, BorderLayout.SOUTH);

        easy.addActionListener(this);
        easy.setEnabled(true);
        medium.addActionListener(this);
        medium.setEnabled(true);
        hard.addActionListener(this);
        hard.setEnabled(true);
        veryhard.addActionListener(this);
        veryhard.setEnabled(true);
        inst.addActionListener(this);
        inst.setEnabled(true);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        Object source = click.getSource();

        if (source == inst) {
            JOptionPane.showMessageDialog(null, instructions_text, "Instructions", JOptionPane.PLAIN_MESSAGE);
        }

        if (source == easy) {
            setLevel(6);
            easy.setForeground(Color.BLUE);
            medium.setForeground(Color.BLACK);
            hard.setForeground(Color.BLACK);
            veryhard.setForeground(Color.BLACK);
        } else if (source == medium) {
            setLevel(10);
            easy.setForeground(Color.BLACK);
            medium.setForeground(Color.BLUE);
            hard.setForeground(Color.BLACK);
            veryhard.setForeground(Color.BLACK);
        } else if (source == hard) {
            setLevel(18);
            easy.setForeground(Color.BLACK);
            medium.setForeground(Color.BLACK);
            hard.setForeground(Color.BLUE);
            veryhard.setForeground(Color.BLACK);
        } else if (source == veryhard) {
            setLevel(50);
            easy.setForeground(Color.BLACK);
            medium.setForeground(Color.BLACK);
            hard.setForeground(Color.BLACK);
            veryhard.setForeground(Color.BLUE);
        }

    }

}
