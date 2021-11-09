package gamejava;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author nikoleta
 */
class FirstWindow implements ActionListener {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JPanel controlPanel;
    String name;
    String text;
    JTextArea area;

    JTextArea x = new JTextArea();
    JButton y = new JButton(" OK ");

    FirstWindow() {

        mainFrame = new JFrame("Battleship-Player's Details");
        mainFrame.setBounds(450, 250, 350, 300);
        mainFrame.setResizable(false);
        mainFrame.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("", JLabel.CENTER);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        headerLabel.setText("Please insert your name and press 'OK'");

        JPanel panel = new JPanel();

        BorderLayout layout = new BorderLayout();

        panel.setLayout(layout);
        area = new JTextArea();

        area.setPreferredSize(new Dimension(250, 20));
        //  x.addActionListener(this);
        panel.add(area, BorderLayout.CENTER);

        y.addActionListener(this);
        y.setEnabled(true);
        panel.add(y, BorderLayout.SOUTH);
        y.setPreferredSize(new Dimension(200, 30));
        controlPanel.add(panel);
        mainFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (area.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter a Name",
                    "WARNING", JOptionPane.WARNING_MESSAGE);

        } else {
            text = area.getText();
            mainFrame.dispose();
            GameFrame x = new GameFrame(text);
        }

    }
}
