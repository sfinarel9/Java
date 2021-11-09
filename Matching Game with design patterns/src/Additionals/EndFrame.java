package Additionals;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sfina
 */
public class EndFrame {

    JLabel lb;
    JFrame fr = new JFrame("WINNER");
    JPanel panel = new JPanel();
    int player_score, computer_score;
    String name;

    public EndFrame() {
    }

    public EndFrame(int score1, int score2, String name) {
        this.player_score = score1;
        this.computer_score = score2;
        this.name = name;
        if (score1 > score2) {
            lb = new JLabel("Congratulations " + name + ". You won!");
        } else if (score1 < score2) {
            lb = new JLabel("Computer won!");
        } else {
            lb = new JLabel("Tie!");
        }

        panel.add(lb, BorderLayout.CENTER);
        fr.setSize(300, 100);
        fr.setLocation(500, 300);
        fr.setLayout(new BorderLayout());
        fr.add(panel, BorderLayout.CENTER);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }

    public String getWinner() {

        if (player_score < computer_score) {
            return name + " : " + player_score + " points.\nComputer : " + computer_score + " points.\nComputer has won !\n";
        } else if (player_score > computer_score) {
            return name + " : " + player_score + " points.\nComputer : " + computer_score + " points.\n" + name + " has won !\n";
        } else {
            return name + " : " + player_score + " points.\nComputer : " + computer_score + " points.\nTie! No one won!\n";
        }
    }

    public void setPlayer_score(int player_score) {
        this.player_score = player_score;
    }

    public void setComputer_score(int computer_score) {
        this.computer_score = computer_score;
    }

}
