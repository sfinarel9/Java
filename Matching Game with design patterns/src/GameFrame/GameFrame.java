package GameFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author sfina
 */
public class GameFrame extends JFrame {

    MenuBar myMenu = new MenuBar(this);
    Controller ctr ;
    ScorePanel scorePane;
    private GamePanel gp;

    public GameFrame(int level, String name) {

        setTitle("Matching game");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        ctr = new Controller(level*10);
        setBackground(new Color(229, 193, 255));
        setSize(900, 700);
        setLocation((width / 2) - 430, (height / 2) - 360);
        setJMenuBar(myMenu);
        gp = new GamePanel(level, name, ctr);
        this.setContentPane(gp);

        ctr.updateView(); // MVC use

        scorePane = ctr.getScorePanel();
        add(BorderLayout.SOUTH, scorePane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
