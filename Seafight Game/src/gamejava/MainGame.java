/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejava;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author sfina
 */
public class MainGame extends JFrame {

    String usersName;   //Users name
    boolean turn = true;    //True if its users turn to play , false if its Computers turn.
    int dimension = 11;
    JMenuBar menuBar = new MyMenu(this , 2);

    JPanel computersBoard = new JPanel();
    JPanel usersBoard = new JPanel();
    JPanel mainPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel labelPanel = new JPanel();

    JLabel computersLabel = new JLabel("|   Computer's Board");
    JLabel usersLabel;

    JButton[][] computersButtons = new JButton[dimension][dimension];
    JButton[][] usersButtons = new JButton[dimension][dimension];

    HashSet<String> computersShips = new HashSet<String>(); // set that includes computers ships positions
    HashSet<String> computersClicked = new HashSet<String>(); // set that includes all clicked buttons in computers board
    HashSet<String> usersClicked = new HashSet<String>(); // set that includes all clicked buttons in users board
    HashSet<String> alreadySelected = new HashSet<String>();// set includes the selected positions from Random()
    HashSet<String> alreadySelectedU = new HashSet<String>();// set includes the selected positions from User
    private Random randomGenerator = new Random();

    public MainGame(HashSet<String> usersShips, String usersName) {

        this.usersName = usersName;

        usersBoard.setLayout(new GridLayout(dimension, dimension));        //create  Users Board
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                if (col == 0 && row == 0) {
                    usersBoard.add(new JLabel(""));
                } else if (row == 0) {
                    usersBoard.add(new JLabel(Integer.toString(col)));
                } else if (col == 0) {
                    usersBoard.add(new JLabel(Character.toString((char) ('A' + row - 1))));
                } else {
                    usersButtons[row][col] = new JButton("");
                    usersButtons[row][col].setPreferredSize(new Dimension(40, 40));
                    usersButtons[row][col].setBackground(Color.LIGHT_GRAY);
                    usersButtons[row][col].setName(Character.toString((char) ('A' + row - 1)) + "" + col);
                    usersButtons[row][col].setEnabled(false);
                    usersBoard.add(usersButtons[row][col]);
                }
            }
        }

        computersBoard.setLayout(new GridLayout(dimension, dimension));        //create  Computers Board)
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                if (col == 0 && row == 0) {
                    computersBoard.add(new JLabel(""));
                } else if (row == 0) {
                    computersBoard.add(new JLabel(Integer.toString(col)));
                } else if (col == 0) {
                    computersBoard.add(new JLabel(Character.toString((char) ('A' + row - 1))));
                } else {
                    computersButtons[row][col] = new JButton("");
                    computersButtons[row][col].setPreferredSize(new Dimension(40, 40));
                    computersButtons[row][col].setBackground(Color.LIGHT_GRAY);
                    computersButtons[row][col].setName(Character.toString((char) ('A' + row - 1)) + "" + col);
                    computersBoard.add(computersButtons[row][col]);
                }
            }
        }
        System.out.println("--------------------------");
        for (int row = 1; row < dimension; row++) {
            for (int col = 1; col < dimension; col++) {
                if (usersShips.contains(usersButtons[row][col].getName())) {
                    usersButtons[row][col].setBackground(Color.pink);
                }
            }
        }

        placeComputerShips();

        for (int i = 1; i < dimension; i++) {
            for (int j = 1; j < dimension; j++) {
                computersButtons[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        turn = true;
                        startGame(usersShips, e.getComponent().getName());
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });

            }
        }

        usersLabel = new JLabel(usersName + " 's Board   ");
        labelPanel.add(usersLabel);
        labelPanel.add(computersLabel);
        setJMenuBar(menuBar);
        add(labelPanel, BorderLayout.PAGE_START);
        boardPanel.add(usersBoard, BorderLayout.LINE_START);
        boardPanel.add(computersBoard, BorderLayout.LINE_END);
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        add(mainPanel);

        setTitle("Battleship-Ship's Game");
        setSize(700, 500);
        setBounds(250, 50, 950, 550);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        show();
    }

    public void startGame(HashSet<String> usersShips, String clicked) {
//        USERS TURN
        int[] pos;
        pos = findCursorPosition(clicked);
        if (!alreadySelectedU.contains(clicked)) { // checks if clicked button is already clicked so computer doesn't play
            alreadySelectedU.add(clicked);
            if (computersShips.contains(clicked)) {
                computersShips.remove(clicked);
                computersButtons[pos[0]][pos[1]].setBackground(Color.red);
                computersButtons[pos[0]][pos[1]].setEnabled(false);
            } else {
                computersButtons[pos[0]][pos[1]].setBackground(Color.white);
                computersButtons[pos[0]][pos[1]].setEnabled(false);
            }
//        COMPUTERS TURN
            int randomNum1;
            int randomNum2;
            String s;
            do {
                randomNum1 = randomGenerator.nextInt(dimension - 1);
                randomNum2 = randomGenerator.nextInt(dimension - 1);
                randomNum1 += 1;
                randomNum2 += 1;
                s = numbersToString(randomNum1, randomNum2);
            } while (alreadySelected.contains(s));
            alreadySelected.add(s);
            if (usersShips.contains(s)) {
                usersShips.remove(s);
                usersButtons[randomNum1][randomNum2].setBackground(Color.red);
                usersButtons[randomNum1][randomNum2].setEnabled(false);
            } else {
                usersButtons[randomNum1][randomNum2].setBackground(Color.white);
                usersButtons[randomNum1][randomNum2].setEnabled(false);
            }
        }

        if (computersShips.isEmpty()) {
            int input = -1;
            input = JOptionPane.showOptionDialog(null, "The Winner is : " + usersName + "!!!", "WINNER", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            if (input != -1) {
                this.dispose();
            }
        }
        if (usersShips.isEmpty()) {
            int input = -1;
            input = JOptionPane.showOptionDialog(null, "The Winner is : Computer!!!", "WINNER", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            if (input != -1) {
                this.dispose();
            }
        }
        if (computersShips.isEmpty() && usersShips.isEmpty()) {
            int input = -1;
            input = JOptionPane.showOptionDialog(null, "Theres no winner! TIE ! Play again!!", "WINNER", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            if (input != -1) {
                this.dispose();
            }
        }
    }

    public void placeComputerShips() {
        int randomNum1 = randomGenerator.nextInt(dimension - 1);
        int randomNum2 = randomGenerator.nextInt(dimension - 1);
        randomNum1 += 1;
        randomNum2 += 1;
        int randomRotate; // 0 = horizontal, 1 = vertical
        boolean selected;
        boolean is3 = true;
        computersShips.clear();
        for (int i = 5; i > 1; i--) {
            randomRotate = randomGenerator.nextInt(2);
            switch (i) {
                case (5):

                    do {    // Find new numbers while it is out of bounds for 5-blocked ship
                        randomNum1 = randomGenerator.nextInt(dimension - 1);
                        randomNum2 = randomGenerator.nextInt(dimension - 1);
                        randomNum1 += 1;
                        randomNum2 += 1;
                    } while (randomNum1 >= dimension - 4 && randomNum2 >= dimension - 4 || randomNum1 >= dimension - 4 || randomNum2 >= dimension - 4);

                    if (randomRotate == 0) {
                        for (int n = randomNum2; n < randomNum2 + i; n++) {
                            computersShips.add(computersButtons[randomNum1][n].getName());
                        }
                    } else {
                        for (int n = randomNum1; n < randomNum1 + i; n++) {
                            computersShips.add(computersButtons[n][randomNum2].getName());
                        }
                    }

                    break;
                default:
                    do {
                        randomNum1 = randomGenerator.nextInt(dimension - 1);// Find new numbers while it is out of bounds or hovers anothes ship
                        randomNum2 = randomGenerator.nextInt(dimension - 1);
                        randomNum1 += 1;
                        randomNum2 += 1;
                        selected = false;
                        if ((randomNum1 < dimension + 1 - i && randomRotate == 1) || (randomNum2 < dimension + 1 - i && randomRotate == 0)) {

                            if (randomRotate == 0) {
                                for (int n = randomNum2; n < randomNum2 + i; n++) {
                                    if (computersShips.contains(computersButtons[randomNum1][n].getName())) {
                                        selected = true;
                                    }
                                }
                            } else {
                                for (int n = randomNum1; n < randomNum1 + i; n++) {
                                    if (computersShips.contains(computersButtons[n][randomNum2].getName())) {
                                        selected = true;
                                    }
                                }
                            }
                        }
                    } while (randomNum1 >= dimension + 1 - i || randomNum2 >= dimension + 1 - i || selected == true);
                    if (randomRotate == 0) {
                        for (int n = randomNum2; n < randomNum2 + i; n++) {
                            computersShips.add(computersButtons[randomNum1][n].getName());
                        }
                    } else {
                        for (int n = randomNum1; n < randomNum1 + i; n++) {
                            computersShips.add(computersButtons[n][randomNum2].getName());
                        }
                    }
                    if (i == 3 && is3) { // I have 2 ships of 3blocks so i have to rerun for i=3 
                        i++;
                        is3 = false;
                    }
                    break;
            }
        }
    }

    public String numbersToString(int num1, int num2) {
        String s = "";
        switch (num1) {
            case 1:
                s = "A";
                break;
            case 2:
                s = "B";
                break;
            case 3:
                s = "C";
                break;
            case 4:
                s = "D";
                break;
            case 5:
                s = "E";
                break;
            case 6:
                s = "F";
                break;
            case 7:
                s = "G";
                break;
            case 8:
                s = "H";
                break;
            case 9:
                s = "I";
                break;
            case 10:
                s = "J";
                break;
        }
        s = s + Integer.toString(num2);
        return s;
    }

    public int[] findCursorPosition(String cursor_pos) {
        String fields[] = cursor_pos.split("");
        int n = -1;
        switch (fields[0]) {
            case "A":
                n = 1;
                break;
            case "B":
                n = 2;
                break;
            case "C":
                n = 3;
                break;
            case "D":
                n = 4;
                break;
            case "E":
                n = 5;
                break;
            case "F":
                n = 6;
                break;
            case "G":
                n = 7;
                break;
            case "H":
                n = 8;
                break;
            case "I":
                n = 9;
                break;
            case "J":
                n = 10;
                break;
        }
        if (fields.length > 2) {
            return new int[]{n, Integer.parseInt(fields[1]) * 10 + Integer.parseInt(fields[2])};
        }
        return new int[]{n, Integer.parseInt(fields[1])};
    }
}
