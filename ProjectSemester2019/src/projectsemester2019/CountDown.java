package projectsemester2019;
/*
*
*author @nikoleta
*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CountDown {

    public int num = 1;
    public JFrame frame;

    public CountDown(GridPanel gp) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(200, 100);
                frame.add(new TestPane(gp), BorderLayout.CENTER);
                frame.pack();
                //frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private Timer timer;
        private long startTime = -1;
        private long duration = 60000;

        private JLabel label;

        public TestPane(GridPanel gp) {
            SimpleDateFormat df = new SimpleDateFormat("mm:ss:SSS");

            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (startTime < 0) {
                        startTime = System.currentTimeMillis();
                    }
                    long now = System.currentTimeMillis();
                    long clockTime = now - startTime;
                    if (clockTime >= duration) {
                        clockTime = duration;
                        timer.stop();
                        gp.setUnnabled(0);
                        JFrame gameoverframe = new JFrame();
                        JLabel gameoverlabel = new JLabel("TIME IS OVER!");
                        gameoverframe.setTitle("GAME OVER");
                        gameoverframe.add(gameoverlabel, BorderLayout.CENTER);
                        gameoverframe.setSize(100, 100);
                        gameoverframe.setBackground(Color.LIGHT_GRAY);
                        gameoverframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        gameoverframe.setVisible(true);
                    }
                    label.setText(df.format(duration - clockTime));
                }
            });
            timer.setInitialDelay(0);
            if (!timer.isRunning()) {
                startTime = -1;
                timer.start();
            }

            label = new JLabel("Time");
            add(label, BorderLayout.CENTER);
            setSize(100, 100);
            setBackground(Color.LIGHT_GRAY);
            setLayout(new GridBagLayout());
        }

    }

}
