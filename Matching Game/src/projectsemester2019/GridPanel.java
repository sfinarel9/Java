package projectsemester2019;
/*
*
*author @nikoleta,marilena,eleftheria
*/
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class GridPanel extends JPanel {

    int numButtons;
    int number;
    int leftTime1;
    String[] pics = {"/images/1.png", "/images/2.png", "/images/3.png", "/images/4.png", "/images/5.png", "/images/6.png", "/images/7.png", "/images/8.png", "/images/9.png", "/images/10.png", "/images/11.png", "/images/12.png", "/images/13.png", "/images/14.png", "/images/15.png", "/images/16.png", "/images/17.png", "/images/18.png"};

    JButton[] buttons;
    ImageIcon cardBlanc = new ImageIcon(getClass().getResource("/images/empty.png"));
    ImageIcon cardBack = new ImageIcon(getClass().getResource("/images/flipped.png"));
    ImageIcon[] icons;
    ImageIcon temp;
    JPanel panelEmu = new JPanel();
    static int score1 = 0;
    static int score2 = 0;
    boolean gameOver;
    Timer myTimer;
    public int closenum;
    int openImages;
    int currentIndex;
    int oddClickIndex;
    int enabled = 1;
    int numClicks;
    boolean isplaying = false;
    Timer timer = new Timer(1000, new MyTimerActionListener());
    JLabel Status = new JLabel();
    JLabel label1 = new JLabel();
    String name;
    CountDown cd;
    static int totalScore;
    SaveResults sr = new SaveResults(numButtons, name);

    public GridPanel(int num, String name) {
        closenum = 1;
        this.name = name;
        number = num;
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        if (number == 6) {
            this.setLayout(new GridLayout(3, 4, 0, 0));
            cd = new CountDown(this);
        } else if (number == 10) {
            this.setLayout(new GridLayout(4, 5, 0, 0));
            cd = new CountDown(this);
        } else if (number == 18) {
            this.setLayout(new GridLayout(6, 6, 0, 0));
            cd = new CountDown(this);
        }
        addButtons();

        timer.start();
        ScorePanel.Status.add(label1);
    }

    private void addButtons() {
        numButtons = number * 2;
        buttons = new JButton[numButtons];
        icons = new ImageIcon[numButtons];

        ScorePanel.Status.setText("Player: " + Integer.toString(score1) + "    Computer: " + Integer.toString(score2));

        for (int i = 0, j = 0; i < number; i++) {
            if (i % 2 == 0) {
                icons[j] = new ImageIcon(this.getClass().getResource(pics[i]));
                buttons[j] = new JButton();
                buttons[j].addActionListener(new GridPanel.ImageButtonListener());
                buttons[j].setIcon(cardBack);
                add(buttons[j++]);

                icons[j] = icons[j - 1];
                buttons[j] = new JButton();
                buttons[j].addActionListener(new GridPanel.ImageButtonListener());
                buttons[j].setIcon(cardBack);
                add(buttons[j++]);
            } else {
                icons[j] = new ImageIcon(this.getClass().getResource(pics[i]));
                buttons[j] = new JButton();
                buttons[j].addActionListener(new GridPanel.ComputerTurn());
                buttons[j].setIcon(cardBack);
                add(buttons[j++]);

                icons[j] = icons[j - 1];
                buttons[j] = new JButton();
                buttons[j].addActionListener(new GridPanel.ImageButtonListener());
                buttons[j].setIcon(cardBack);
                add(buttons[j++]);
            }

        }

        Random rnd = new Random();
        for (int i = 0; i < numButtons; i++) {
            int j = rnd.nextInt(numButtons);
            temp = icons[i];
            icons[i] = icons[j];
            icons[j] = temp;
        }

//        for (int i = 0; i < numButtons; i++) {
//            buttons[i].setIcon(icons[i]);
//        }
//        for (int i = 0; i < numButtons; i++) {
//            buttons[i].setIcon(cardBack);
//        }
        myTimer = new Timer(1000, new TimerListener());
    }

    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            buttons[currentIndex].setIcon(cardBack);
            buttons[oddClickIndex].setIcon(cardBack);
            myTimer.stop();
        }
    }

    private class ImageButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            if (myTimer.isRunning()) {
                return;
            }
            openImages++;
            for (int i = 0; i < numButtons; i++) {
                if (ae.getSource() == buttons[i]) {
                    buttons[i].setIcon(icons[i]);
                    currentIndex = i;
                }
            }
            if (openImages % 2 == 0) {
                if (currentIndex == oddClickIndex) {
                    numClicks--;
                    return;
                }
                if (icons[currentIndex] != icons[oddClickIndex]) {
                    myTimer.start();
                } else {
                    buttons[currentIndex].setIcon(cardBlanc);
                    buttons[oddClickIndex].setIcon(cardBlanc);
                    buttons[oddClickIndex].setEnabled(false);
                    buttons[currentIndex].setEnabled(false);
                    score1++;
                }
                sr.doSave();
                totalScore = score1 + score2;
                ScorePanel.Status.setText(name + ": " + Integer.toString(score1) + "   Computer: " + Integer.toString(score2));
                if (number == 6 & totalScore == 6) {
                    cd.frame.dispose();
                    setVisible(false);
                    EndFrame end = new EndFrame(score1, name);

                } else if (number == 10 & totalScore == 10) {
                    cd.frame.dispose();
                    setVisible(false);
                    EndFrame end = new EndFrame(score1, name);

                } else if (number == 18 & totalScore == 18) {
                    cd.frame.dispose();
                    setVisible(false);
                    EndFrame end = new EndFrame(score1, name);

                }

            } else {
                oddClickIndex = currentIndex;
            }
            if (enabled == 0) {
                setVisible(false);
            }
        }
    }

    class MyTimerActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Calendar startCalendar = Calendar.getInstance();
            long startTime = startCalendar.getTime().getTime();
            long endTime = startTime + 2 * 60 * 1000;
            long nowTime, leftTime;
            Calendar now;

            while (isplaying) {

                now = Calendar.getInstance();
                nowTime = now.getTime().getTime();
                leftTime = (endTime - nowTime) / 1000;
                leftTime1 = (int) leftTime;

                if (leftTime == 0) {
                    JOptionPane.showMessageDialog(panelEmu, "NO TIME,GAME OVER", "", JOptionPane.OK_OPTION);
                    isplaying = false;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException es) {
                    es.printStackTrace();
                }
            }

        }
    }

    public String getWinner() {
        if (score1 < score2) {
            return name + ": " + score1 + " points\nComputer: " + score2 + " points\nComputer won!\n";
        } else if (score1 > score2) {
            return name + " : " + score1 + " points\nComputer: " + score2 + " points\n" + name + " won!\n";
        } else {
            return name + " : " + score1 + " points\nComputer: " + score2 + " points\nTie!\n";
        }
    }

    public void setScore(int score) {
        this.score1 = score;
    }

    public int getTime() {
        return leftTime1;
    }

    public int getScore() {
        return score1;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setUnnabled(int num) {
        this.enabled = num;
    }

    public void delayForThreeSeconds() {
        try {
            Thread.sleep(3000);
        } catch (java.lang.InterruptedException iex) {
            System.out.println(iex);
        }
    }

    public class ComputerTurn implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            Random random = new Random();
            int image = random.nextInt(numButtons);
            if (buttons[image].getIcon() != cardBlanc) {

                if (myTimer.isRunning()) {
                    return;
                }

                openImages++;
                for (int i = 0; i < numButtons; i++) {
                    if (ae.getSource() == buttons[i]) {
                        buttons[i].setIcon(icons[i]);
                        currentIndex = i;
                    }
                }
                if (openImages % 2 == 0) {
                    if (currentIndex == oddClickIndex) {
                        numClicks--;
                        return;
                    }
                    if (icons[currentIndex] != icons[oddClickIndex]) {
                        myTimer.start();
                    } else {
                        buttons[currentIndex].setIcon(cardBlanc);
                        buttons[oddClickIndex].setIcon(cardBlanc);
                        buttons[currentIndex].setEnabled(false);
                        buttons[oddClickIndex].setEnabled(false);
                        score2++;
                    }
                    totalScore = score1 + score2;
                    ScorePanel.Status.setText("Player: " + Integer.toString(score1) + "   Computer: " + Integer.toString(score2));
                    if (number == 6 & totalScore == 6) {
                        cd.frame.dispose();
                        setVisible(false);
                        EndFrame end = new EndFrame(score1, name);

                    } else if (number == 10 & totalScore == 10) {
                        cd.frame.dispose();
                        setVisible(false);
                        EndFrame end = new EndFrame(score1, name);

                    } else if (number == 18 & totalScore == 18) {
                        cd.frame.dispose();
                        setVisible(false);
                        EndFrame end = new EndFrame(score1, name);
                    }

                } else {
                    oddClickIndex = currentIndex;
                }
                if (enabled == 0) {
                    setVisible(false);
                }
            }
        }
    }

    public void setScore2(int score) {
        this.score2 = score;
    }

    public int getScore2() {
        return score2;
    }
}
