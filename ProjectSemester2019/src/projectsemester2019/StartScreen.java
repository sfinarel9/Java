package projectsemester2019;
/*
*
*author @eleftheria
*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class StartScreen implements ActionListener {

    JFrame frame = new JFrame("MEMORY GAME");
    JPanel start_screen = new JPanel();
    JPanel menu = new JPanel();
    JPanel menu2 = new JPanel();
    JPanel menu3 = new JPanel();
    JPanel mini = new JPanel();
    JPanel instruct_screen = new JPanel();
    JLabel label = new JLabel("NAME:");
    JLabel levelLabel = new JLabel("CHOOSE LEVEL:");
    JButton start = new JButton("Start");
    JButton over = new JButton("Exit");
    JButton easy = new JButton("Easy");
    JButton medium = new JButton("Medium");
    JButton hard = new JButton("Hard");
    JButton inst = new JButton("Instructions");
    JButton goBack = new JButton("Main Menu");
    JTextField text = new JTextField(10);
    JTextArea instructM = new JTextArea("When the game begins, the screen will be filled\nwith pairs of buttons.\n Memorize their placement.\nOnce you press any button, they will all clear. \n Your goal is to click the matching buttons in a row.\nWhen you finish that, you win.\nEvery incorrect click gives you a point (those are bad).\n GOOD LUCK! \n" + "for a single level: enter a level between 1 and 10,\nselect easy or hard, then press start.");
    String name;
    int num;

    public StartScreen() {
        frame.setSize(500, 300);
        frame.setLocation(500, 300);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start_screen.setLayout(new BorderLayout());
        menu.setLayout(new FlowLayout(FlowLayout.CENTER));
        menu2.setLayout(new FlowLayout(FlowLayout.CENTER));
        menu3.setLayout(new FlowLayout(FlowLayout.CENTER));
        mini.setLayout(new FlowLayout(FlowLayout.CENTER));
//***************************************************************
        start_screen.add(menu, BorderLayout.NORTH);
        start_screen.add(menu3, BorderLayout.CENTER);
        start_screen.add(menu2, BorderLayout.SOUTH);
//***************************************************************
        menu3.add(mini, BorderLayout.CENTER);
        menu.add(label);
        menu.add(text);
        menu2.setBackground(Color.pink);
        menu3.setBackground(Color.LIGHT_GRAY);
        menu.setBackground(Color.pink);
        mini.setBackground(Color.LIGHT_GRAY);
        mini.add(easy, BorderLayout.NORTH);
        mini.add(medium, BorderLayout.NORTH);
        mini.add(hard, BorderLayout.NORTH);
        mini.add(inst, BorderLayout.SOUTH);

        start.addActionListener(this);
        start.setEnabled(true);
        menu2.add(start);
        over.addActionListener(this);
        over.setEnabled(true);
        menu2.add(over);
        easy.addActionListener(this);
        easy.setEnabled(true);
        medium.addActionListener(this);
        medium.setEnabled(true);
        hard.addActionListener(this);
        hard.setEnabled(true);
        inst.addActionListener(this);
        inst.setEnabled(true);

        frame.add(start_screen, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        Object source = click.getSource();
        if (source == start) {
            try {
                name = text.getText();
            } catch (Exception e) {
                name = null;
            }

            GameFrame gf = new GameFrame(num, name);
            gf.setVisible(true);
        }
        if (source == over) {
            System.exit(0);
        }
        if (source == inst) {
            start_screen.remove(menu);
            start_screen.remove(menu2);
            start_screen.remove(menu3);
            start_screen.revalidate();
            start_screen.repaint();

            start_screen.add(instruct_screen, BorderLayout.NORTH);

            JPanel one = new JPanel();
            one.setLayout(new FlowLayout(FlowLayout.CENTER));
            JPanel two = new JPanel();
            two.setLayout(new FlowLayout(FlowLayout.CENTER));
            instruct_screen.setLayout(new BorderLayout());
            instruct_screen.add(one, BorderLayout.NORTH);
            instruct_screen.add(two, BorderLayout.SOUTH);

            one.add(instructM);
            two.add(goBack);
            goBack.addActionListener(this);
            goBack.setEnabled(true);
        }
        if (source == goBack) {
            frame.dispose();
            new StartScreen();
        }
        if (source == easy) {
            num = 6;
            easy.setForeground(Color.BLUE);
            medium.setForeground(Color.BLACK);
            hard.setForeground(Color.BLACK);
        } else if (source == medium) {
            num = 10;
            easy.setForeground(Color.BLACK);
            medium.setForeground(Color.BLUE);
            hard.setForeground(Color.BLACK);
        } else if (source == hard) {
            num = 18;
            easy.setForeground(Color.BLACK);
            medium.setForeground(Color.BLACK);
            hard.setForeground(Color.BLUE);
        }

    }
}
