package GameFrame;

import Additionals.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author sfina
 */
//MVC Model
public class GridPanel extends JPanel {

    int numButtons, number;    // number = the level (6 = easy , 10 = mediun , 18 = hard) //how many pairs of cards are in board
    String[] pics = {"/images/1.png", "/images/2.png", "/images/3.png", "/images/4.png", "/images/5.png", "/images/6.png", "/images/7.png", "/images/8.png", "/images/9.png", "/images/10.png", "/images/11.png", "/images/12.png", "/images/13.png", "/images/14.png", "/images/15.png", "/images/16.png", "/images/17.png", "/images/18.png","/images/1.png", "/images/2.png", "/images/3.png", "/images/4.png", "/images/5.png", "/images/6.png", "/images/7.png", "/images/8.png","/images/1.png", "/images/2.png", "/images/3.png", "/images/4.png", "/images/5.png", "/images/6.png", "/images/7.png", "/images/8.png","/images/1.png", "/images/2.png", "/images/3.png", "/images/4.png", "/images/5.png", "/images/6.png", "/images/7.png", "/images/8.png","/images/1.png", "/images/2.png", "/images/3.png", "/images/4.png", "/images/5.png", "/images/6.png", "/images/7.png", "/images/8.png","/images/1.png", "/images/2.png", "/images/3.png", "/images/4.png", "/images/5.png", "/images/6.png", "/images/7.png", "/images/8.png","/images/1.png", "/images/2.png", "/images/3.png", "/images/4.png", "/images/5.png", "/images/6.png", "/images/7.png", "/images/8.png","/images/1.png", "/images/2.png", "/images/3.png", "/images/4.png", "/images/5.png", "/images/6.png", "/images/7.png", "/images/8.png","/images/1.png", "/images/2.png", "/images/3.png", "/images/4.png", "/images/5.png", "/images/6.png", "/images/7.png", "/images/8.png","/images/1.png", "/images/2.png", "/images/3.png", "/images/4.png", "/images/5.png", "/images/6.png", "/images/7.png", "/images/8.png","/images/1.png", "/images/2.png", "/images/3.png", "/images/4.png", "/images/5.png", "/images/6.png", "/images/7.png", "/images/8.png"};
    Random rnd = new Random();
    JButton[] buttons;
    ImageIcon cardBlanc = new ImageIcon(getClass().getResource("/images/empty.png"));
    ImageIcon cardBack = new ImageIcon(getClass().getResource("/images/flipped.png"));
    ImageIcon[] icons;
    ImageIcon temp;
    static int player_score = 0, computer_score = 0, totalScore;
    boolean gameOver;
    Timer myTimer, computer;
    private Timer timer1;
    int openImages, currentIndex, oddClickIndex;
    String name;
    HashSet<Integer> usedCardsSet = new HashSet<Integer>();
    Controller ctr;
    SaveResults sr;

    public GridPanel(int num, String name, Controller ctr) {
        this.name = name;
        this.ctr = ctr;
        number = num;  // the level
        sr = new SaveResults(name);

        switch (number) {
            case 6:
                this.setLayout(new GridLayout(3, 4, 0, 0));
                break;
            case 10:
                this.setLayout(new GridLayout(4, 5, 0, 0));
                break;
            case 18:
                this.setLayout(new GridLayout(6, 6, 0, 0));
                break;
            case 50:
                this.setLayout(new GridLayout(10, 19, 0, 0));
                break;
            default:
                break;
        }
        addButtons();

    }

    private void addButtons() {
        numButtons = number * 2;
        buttons = new JButton[numButtons];
        icons = new ImageIcon[numButtons];

        ScorePanel.Status.setText(name + ": " + Integer.toString(player_score) + "   Computer: " + Integer.toString(computer_score));

        for (int i = 0, j = 0; i < number; i++) {  // add pair cards side by side
            icons[j] = new ImageIcon(this.getClass().getResource(pics[i]));
            buttons[j] = new JButton();
            buttons[j].addActionListener(new GridPanel.ImageButtonListener());
            buttons[j].setBackground(new Color(229, 193, 255));
            buttons[j].setIcon(cardBack);
            add(buttons[j++]);

            icons[j] = icons[j - 1];
            buttons[j] = new JButton();
            buttons[j].addActionListener(new GridPanel.ImageButtonListener());
            buttons[j].setBackground(new Color(229, 193, 255));
            buttons[j].setIcon(cardBack);
            add(buttons[j++]);

        }

        for (int i = 0; i < numButtons; i++) { // Shuffle the cards!
            int j = rnd.nextInt(numButtons);
            temp = icons[i];
            icons[i] = icons[j];
            icons[j] = temp;
        }
        timer1 = new Timer((number * 200), new ImageButtonListenerFirst());  //Show the cards some seconds before hiding
        for (int i = 0; i < numButtons; i++) {
            buttons[i].setIcon(icons[i]);

        }
        timer1.start();

        myTimer = new Timer(500, new TimerListener());
    }

    private class TimerListener implements ActionListener {  //this timer return the cards to face down if not same

        @Override
        public void actionPerformed(ActionEvent ae) {
            buttons[currentIndex].setIcon(cardBack);
            buttons[oddClickIndex].setIcon(cardBack);
            myTimer.stop();
        }
    }

    private class ComputersTurn implements ActionListener {  //this timer return the cards to face down if not same

        Random rand = new Random();
        int comCard1, comCard2;         //computer cards

        @Override
        public void actionPerformed(ActionEvent ae) {
            do {
                comCard1 = rand.nextInt(numButtons);
                comCard2 = rand.nextInt(numButtons);
            } while (usedCardsSet.contains(comCard1) || usedCardsSet.contains(comCard2) || comCard2 == comCard1);

            buttons[comCard1].setIcon(icons[comCard1]);
            buttons[comCard2].setIcon(icons[comCard2]);

            checkCards(comCard1, comCard2, 1);
            computer.stop();
        }
    }

    public class ImageButtonListenerFirst implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (timer1.isRunning()) {
                for (int i = 0; i < numButtons; i++) {
                    buttons[i].setIcon(cardBack);
                    timer1.stop();
                }
            }
        }
    }

    public class ImageButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            openImages++;
            for (int i = 0; i < numButtons; i++) {  //set ability to change the back
                if (ae.getSource() == buttons[i]) {
                    buttons[i].setIcon(icons[i]);
                    currentIndex = i;
                }
            }
            if (openImages % 2 == 0) {  // if 2 cards are opened
                if (currentIndex == oddClickIndex) { //if same button is pressed 2 times
                    return;
                }
                checkCards(currentIndex, oddClickIndex, 0);

                if (usedCardsSet.size() != numButtons) {
                    computer = new Timer(500, new ComputersTurn());
                    computer.start();
                }
            } else {
                oddClickIndex = currentIndex;
            }

        }

    }

    void checkCards(int card1, int card2, int em) {
        if (icons[card1] == icons[card2]) {
            buttons[card1].setIcon(cardBlanc);
            buttons[card2].setIcon(cardBlanc);
            buttons[card2].setEnabled(false);
            buttons[card1].setEnabled(false);
            usedCardsSet.add(card1);
            usedCardsSet.add(card2);
            if (em == 0) {
                player_score++;
            } else {
                computer_score++;
            }
        } else {
            oddClickIndex = card1;
            currentIndex = card2;
            myTimer.start();
        }
        updateScore();
    }

    public void updateScore() {
        totalScore = player_score + computer_score;
        ScorePanel.Status.setText(name + ": " + Integer.toString(player_score) + "   Computer: " + Integer.toString(computer_score));
        if ((number == 6 & totalScore == 6) || (number == 10 & totalScore == 10) || (number == 18 & totalScore == 18)|| (number == 50 & totalScore == 50)) {

            setVisible(false);
            sr.doSave(player_score, computer_score);
            ctr.stopTimer();
            EndFrame end = new EndFrame();
            end.setComputer_score(computer_score);
            end.setPlayer_score(player_score);

        }
    }

}
