/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsemester2019;
/*
*
*author @eleftheria
*/
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndFrame {

    JLabel lb;

    EndFrame(int score, String name) {
        JFrame fr = new JFrame("WINNER");
        JPanel panel = new JPanel();
        lb = new JLabel(name + " won!" + name + "'s score is " + score);

        panel.add(lb, BorderLayout.CENTER);
        fr.setSize(300, 100);
        fr.setLocation(500, 300);
        fr.setLayout(new BorderLayout());
        fr.add(panel, BorderLayout.CENTER);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }

}
