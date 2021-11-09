package projectsemester2019;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GameFrame extends JFrame {

    private GamePanel gp;
    JMenuBar menuBar = new JMenuBar();
    JMenu gameMenu = new JMenu("Game");
    JMenu optionsMenu = new JMenu("Options");
    JMenu aboutMenu = new JMenu("Help:");
    ScorePanel scorePane;
    int number;

    public GameFrame(int num, String name) {
        this.setTitle("Matching game");
        this.setBounds(150, 50, 900, 700);
        scorePane = new ScorePanel();
        number = num;
        gp = new GamePanel(num, name);
        this.setContentPane(gp);
        setJMenuBar(menuBar);

        menuBar.add(gameMenu);
        newMenuItem("New game", gameMenu);
        newMenuItem("Restart", gameMenu);
        newMenuItem("Exit", gameMenu);

        menuBar.add(optionsMenu);
        JMenu historyMenu = new JMenu("History");
        optionsMenu.add(historyMenu);
        newMenuItem("Show History", historyMenu);
        newMenuItem("Hide History", historyMenu);

        menuBar.add(aboutMenu);
        newMenuItem("About", aboutMenu);

        add(BorderLayout.SOUTH, scorePane);
    }

    private void newMenuItem(String string, JMenu menu) {
        JMenuItem newItem = new JMenuItem(string);
        newItem.setActionCommand(string);

        if (string == "Restart") {
            newItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    dispose();
                    new StartScreen();
                }
            });
        }
        if (string == "New Game") {
            newItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    dispose();
                }
            });
        }
        if (string == "About") {
            newItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    JOptionPane.showMessageDialog(null, "Match the hidden images to win!");

                }
            });
        }
        if (string == "Exit") {
            newItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    System.exit(0);
                }
            });
        }
        menu.add(newItem);
    }

}
