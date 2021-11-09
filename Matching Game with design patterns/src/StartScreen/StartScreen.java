package StartScreen;

import GameFrame.GameFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author sfina
 */
//SINGLETON 
public class StartScreen extends JFrame implements ActionListener {

    private static StartScreen instance = new StartScreen();

    private StartScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        setSize(500, 300);
        setLocation((width / 2) - 250, (height / 2) - 200);
        setTitle("Matching Game");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start_screen.setLayout(new BorderLayout());
        start_screen.add(detailsP, BorderLayout.NORTH);
        start_screen.add(levelP, BorderLayout.CENTER);
        start_screen.add(optionsP, BorderLayout.SOUTH);
        add(start_screen, BorderLayout.CENTER);

        optionsP.start.addActionListener(this);

    }

    public static StartScreen getInstance() {
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        Object source = click.getSource();
        if (source == optionsP.start) {
            System.out.println("GOT IN BABY! ");
            if (!detailsP.getText().isEmpty()) {
                players_name = detailsP.getText();
            }

            System.out.println(players_name);
            GameFrame gf = new GameFrame(levelP.getLevel(), players_name);
            gf.setVisible(true);
        }
    }

    DetailsPanel detailsP = new DetailsPanel();
    OptionsPanel optionsP = new OptionsPanel();
    LevelPanel levelP = new LevelPanel();
    JPanel start_screen = new JPanel();
    String players_name = "Player";
}
