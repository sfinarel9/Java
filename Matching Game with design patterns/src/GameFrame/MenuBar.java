package GameFrame;

import History_DAO_Pattern.MyGridLayout;
import StartScreen.StartScreen;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author sfina
 */
public class MenuBar extends JMenuBar {

    JFrame frame;
    JMenu gameMenu = new JMenu("Game");
    JMenu optionsMenu = new JMenu("Options");
    JMenu aboutMenu = new JMenu("Help:");
    JMenu historyMenu = new JMenu("Show History");

    public MenuBar(JFrame frame) {
        this.frame = frame;
        add(gameMenu);
        newMenuItem("New Game", gameMenu);
        newMenuItem("Exit", gameMenu);

        add(optionsMenu);
        newMenuItem("Show History", optionsMenu);

        add(aboutMenu);
        newMenuItem("About", aboutMenu);
        setBackground(new Color(229, 193, 255));
    }

    private void newMenuItem(String string, JMenu menu) {
        JMenuItem newItem = new JMenuItem(string);
        newItem.setActionCommand(string);

        if (string == "New Game") {
            newItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {

                    frame.dispose();
                    StartScreen.getInstance();
                }
            });
        }
        if (string == "About") {
            newItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    JOptionPane.showMessageDialog(null, "Match the cards to win!");

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
        if (string == "Show History") {
            newItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    new MyGridLayout();
                }
            });
        }
        menu.add(newItem);
    }

}
