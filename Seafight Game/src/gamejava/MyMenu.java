package gamejava;

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
public class MyMenu extends JMenuBar {

    JMenu gameMenu = new JMenu("Game");
    JMenu aboutMenu = new JMenu("About");
    int num = -1;

    public MyMenu(JFrame f, int num) {
        this.num = num;
        add(gameMenu);
        newMenuItem("Restart", gameMenu, f);
        newMenuItem("Exit", gameMenu, f);
        newMenuItem("Help", aboutMenu, f);
        add(aboutMenu);
    }

//Function newMenuItem() creates the menu bar and sets actions to menu items
    public void newMenuItem(String string, JMenu menu, JFrame f) {
        JMenuItem newItem = new JMenuItem(string);
        newItem.setActionCommand(string);

        if (string == "Restart") {
            newItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {

                    f.dispose();
                    FirstWindow fs = new FirstWindow();
                }
            });
        }

        if (string == "Help") {
            if (num == 1) {
                newItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        JOptionPane.showMessageDialog(null, "select your battleship from left board and place it on the right board");

                    }
                });
            } else {
                newItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        JOptionPane.showMessageDialog(null, "Press any square from the right board to play and find all computer's boats to win!!");

                    }
                });
            }
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
