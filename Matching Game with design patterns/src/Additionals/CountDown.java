package Additionals;

import GameFrame.Controller;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author sfina
 */

//MVC Model
public class CountDown extends JLabel {

    private Timer timer;
    private long duration, startTime = -1;
    SimpleDateFormat df = new SimpleDateFormat("mm:ss:SSS");
    int num = 1, answer = 1, time;
    String countdown = "00:00:000"; // the countdown text

    public CountDown(Controller c, int time) {
        duration = time * 1000;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                timer = new Timer(10, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (startTime < 0) {
                            startTime = System.currentTimeMillis();
                        }
                        long now = System.currentTimeMillis();
                        long clockTime = now - startTime;
                        if (clockTime >= duration) { //when countdown is over
                            setText(df.format(duration - duration)); // 00:00:000

                            clockTime = duration;
                            timer.stop();
                            Object[] options = {"EXIT"};
                            answer = JOptionPane.showOptionDialog(null,
                                    "You run out of time!\n Thanks for playing!",
                                    "GAME OVER",
                                    JOptionPane.PLAIN_MESSAGE,
                                    JOptionPane.WARNING_MESSAGE,
                                    null,
                                    options,
                                    options[0]);
                        }
                        setText(df.format(duration - clockTime));
                        if (answer == 0) {
                            System.exit(0);
                        }
                    }

                });

                timer.setInitialDelay(0);
                if (!timer.isRunning()) {
                    startTime = -1;
                    timer.start();
                }

            }
        });
        this.setHorizontalAlignment(SwingConstants.CENTER);
        setBackground(new Color(229, 193, 255));
    }

    public void setCountdown(String countdown) {
        this.countdown = countdown;
    }

    public void stopTimer() {
        timer.stop();
    }

    public String getCountdown() {
        return countdown;
    }

}
