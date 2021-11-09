package gamejava;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 *
 * @author nikoleta
 */
public class GameFrame {

    int numButtons;
    int dimension = 11;
    JButton[][] buttons = new JButton[5][5]; // boat buttons
    JButton[][] boardButtons = new JButton[dimension][dimension]; //board buttons

    JPanel statusPanel = new JPanel();        //Bottom panel includes  rotate and start buttons
    JButton startbtn = new JButton("start");
    JButton rotatebtn = new JButton("rotate");

//    JFrame Panels
    JPanel p0 = new JPanel();  // Main panel
    JPanel p = new JPanel();   // Top Panel includes label
    JPanel p1 = new JPanel();  // Main board panel, where ships are being placed
    JPanel p2 = new JPanel();  // Panel with ship board  and main board
    JPanel p3 = new JPanel();  // Panel includes ships board

//     All 5 boats
    Myboat boat5 = new Myboat("Aircraft Carrier", 5);
    Myboat boat4 = new Myboat("Battle Ship", 4);
    Myboat boat3a = new Myboat("Submarine", 3);
    Myboat boat3b = new Myboat("Destroyer", 3);
    Myboat boat2 = new Myboat("Patrol Boat", 2);

    private int clicked = -1;
    static JFrame f = new JFrame("Battleship-Ship's Placement");
    static JLabel l;  // label to display text 

    JMenuBar menuBar = new MyMenu(f , 1);

    HashSet<String> positionsSet = new HashSet<String>(); // set that includes all "catched" positions in board with boats
    HashSet<String> disabledShipsSet = new HashSet<String>();// set includes all placed ships by theid id
    HashSet<String> shipSelectedSet = new HashSet<String>();// set includes all positions the selected ship needs
    String cursor_pos;  //cursor position in boat board
    int rotate = 1; // rotate = 1 for horizontal pos , rotate = -1 for vertical pos
    boolean isOk = true;
    boolean contains = false;
    String usersName;

    public GameFrame(String name) {
        l = new JLabel("Welcome " + name + "! Please select ships from the left and place them in your board. Press ''start game'' when you are ready!");
        usersName = name;
        p.add(l, BorderLayout.PAGE_START);
        f.setJMenuBar(menuBar);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        p1.setLayout(new GridLayout(dimension, dimension));        //create  main board (where ships are placed)
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                if (col == 0 && row == 0) {
                    p1.add(new JLabel(""));
                } else if (row == 0) {
                    p1.add(new JLabel(Integer.toString(col)));
                } else if (col == 0) {
                    p1.add(new JLabel(Character.toString((char) ('A' + row - 1))));
                } else {
                    boardButtons[row][col] = new JButton("");
                    boardButtons[row][col].setPreferredSize(new Dimension(40, 40));
                    boardButtons[row][col].setBackground(Color.LIGHT_GRAY);
                    boardButtons[row][col].setName(Character.toString((char) ('A' + row - 1)) + "" + col);
                    p1.add(boardButtons[row][col]);
                }
            }
        }

        p3.setLayout(new GridLayout(5, 5));             // create battleships board
        for (int row = 0; row < 5; row++) {
            p3.add(new JLabel(Character.toString((char) ('A' + row))));
            for (int col = 0; col < 5; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setPreferredSize(new Dimension(40, 40));
                buttons[row][col].setBackground(Color.white);

                p3.add(buttons[row][col]);
            }
        }

        for (int i = 0; i < 5; i++) {   // Paint eack boat blocks. Then disable extra blocks in ships where size is < 5
            if (i == 0) {
                for (int j = 0; j < boat5.getSize(); j++) {
                    buttons[i][j].setBackground(Color.DARK_GRAY);
                    buttons[i][j].setName("5");
                }
            } else if (i == 1) {
                for (int j = 0; j < 5; j++) {
                    if (j < boat4.getSize()) {
                        buttons[i][j].setBackground(Color.DARK_GRAY);
                        buttons[i][j].setName("4");
                    } else {
                        buttons[i][j].setEnabled(false);
                    }
                }
            } else if (i == 2 || i == 3) {
                for (int j = 0; j < 5; j++) {
                    if (j < boat3a.getSize()) {
                        buttons[i][j].setBackground(Color.DARK_GRAY);
                        if (i == 2) {
                            buttons[i][j].setName("31");
                        } else {
                            buttons[i][j].setName("32");
                        }
                    } else {
                        buttons[i][j].setEnabled(false);
                    }
                }
            } else if (i == 4) {
                for (int j = 0; j < 5; j++) {
                    if (j < boat2.getSize()) {
                        buttons[i][j].setBackground(Color.DARK_GRAY);
                        buttons[i][j].setName("2");
                    } else {
                        buttons[i][j].setEnabled(false);
                    }
                }
            }
        }

        for (int i = 0; i < 5; i++) {          //Add actionlisteners into boat board buttons.
            for (int j = 0; j < 5; j++) {
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String[] word = e.toString().split(" ");
                        System.out.println("you pressed " + word[word.length - 1]);
                        int num = 0;
                        for (int i = 0; i < 5; i++) {
                            num = i;
                            if (i > 2) {
                                num--;
                            }
                            for (int j = 0; j < 5 - num; j++) {
                                if (buttons[i][j].getName().equals(word[word.length - 1])) {
                                    buttons[i][j].setBackground(Color.YELLOW);
                                } else {
                                    if (!buttons[i][j].isEnabled() && disabledShipsSet.contains(buttons[i][j].getName())) {
                                        buttons[i][j].setBackground(Color.white);
                                    } else if (buttons[i][j].getName().equals("31") && !buttons[i][j].isEnabled() && disabledShipsSet.contains(buttons[i][j].getName())) {
                                        buttons[2][j].setBackground(Color.white);
                                    } else if (buttons[i][j].getName().equals("32") && !buttons[i][j].isEnabled() && disabledShipsSet.contains(buttons[i][j].getName())) {
                                        buttons[3][j].setBackground(Color.white);
                                    } else {
                                        buttons[i][j].setBackground(Color.DARK_GRAY);
                                    }
                                }
                            }
                            num++;
                        }
                        clicked = Integer.valueOf(word[word.length - 1]);
                    }
                });
            }
        }

        for (int i = 1; i < dimension; i++) {
            for (int j = 1; j < dimension; j++) {
                boardButtons[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        boolean ret = placeShips(clicked, cursor_pos);
                        if (ret) {
                            clicked = -1;
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        String[] word = e.toString().split(" ");
                        cursor_pos = word[word.length - 1];  //contains cursor position in nubmers: A1 is 11 , B7 is 27.
                        if (clicked != -1) {
                            check(clicked, cursor_pos);

                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        for (int i = 1; i < dimension; i++) {
                            for (int j = 1; j < dimension; j++) {
                                if (!positionsSet.contains(boardButtons[i][j].getName())) {
                                    boardButtons[i][j].setBackground(Color.LIGHT_GRAY);
                                }
                            }
                        }

                    }
                });
            }
        }

        p2.add(p3, BorderLayout.LINE_START);
        p2.add(p1, BorderLayout.LINE_END);
        p0.add(p, BorderLayout.PAGE_START);
        p0.add(p2, BorderLayout.CENTER);
        f.add(p0);

        statusPanel.setPreferredSize(new Dimension(f.getWidth(), 24));

        rotatebtn.addActionListener(new ActionListener() { //Rotate button
            @Override
            public void actionPerformed(ActionEvent e) {
                rotate *= -1;
                System.out.println(rotate);
            }
        });
        startbtn.addActionListener(new ActionListener() {  //Start game button
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                MainGame mg = new MainGame(positionsSet, usersName);
            }
        });

        startbtn.setEnabled(false);
        statusPanel.setLayout(new BorderLayout());
        statusPanel.add(startbtn, BorderLayout.EAST);
        statusPanel.add(rotatebtn, BorderLayout.WEST);

        f.add(statusPanel, BorderLayout.SOUTH);
        f.setSize(700, 500);
        f.setBounds(250, 50, 850, 650);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.show();

    }

//    Function check() checks when a ship is within or outside the limits of the Board. 
    public void check(int clicked, String cursor_pos) {

        shipSelectedSet.clear();
        boolean contains = false;
        int[] pos = findCursorPosition(cursor_pos);
        if (clicked == 31 || clicked == 32) {
            clicked = 3;
        }
        if (rotate == 1) {               //Create a set that includes the selected ships buttons needed 
            if (pos[1] + clicked < dimension) {
                for (int num = pos[1]; num < pos[1] + clicked; num++) {
                    shipSelectedSet.add(boardButtons[pos[0]][num].getName());
                }
            } else {
                for (int num = pos[1]; num < dimension; num++) {
                    shipSelectedSet.add(boardButtons[pos[0]][num].getName());
                }
            }
        } else {
            if (pos[0] + clicked < dimension) {
                for (int num = pos[0]; num < pos[0] + clicked; num++) {
                    shipSelectedSet.add(boardButtons[num][pos[1]].getName());
                }
            } else {
                for (int num = pos[0]; num < dimension; num++) {
                    shipSelectedSet.add(boardButtons[num][pos[1]].getName());
                }
            }
        }
        for (String s : shipSelectedSet) {
            if (!positionsSet.isEmpty() && positionsSet.contains(s)) {
                contains = true;
            }
        }

        if (positionsSet.contains(boardButtons[pos[0]][pos[1]].getName()) || contains) {  // check if a ship is going to be placed over another ship
            for (String s : positionsSet) {                            //paint pink all placed ships
                for (int i = 1; i < dimension; i++) {
                    for (int j = 1; j < dimension; j++) {
                        if (positionsSet.contains(boardButtons[i][j].getName())) {
                            boardButtons[i][j].setBackground(Color.pink);
                        }
                    }
                }
            }
            if (rotate == 1) {
                for (int num = pos[1]; num < pos[1] + clicked; num++) {
                    boardButtons[pos[0]][num].setBackground(Color.red);
                    if (num + 1 >= dimension) {
                        break;
                    }
                }
                isOk = false;
            } else if (rotate == -1) {
                for (int num = pos[0]; num < pos[0] + clicked; num++) {
                    boardButtons[num][pos[1]].setBackground(Color.red);
                    if (num + 1 >= dimension) {
                        break;
                    }
                }
                isOk = false;
            }
        } else {
            for (String s : positionsSet) {                            //paint pink all placed ships
                for (int i = 1; i < dimension; i++) {
                    for (int j = 1; j < dimension; j++) {
                        if (positionsSet.contains(boardButtons[i][j].getName())) {
                            boardButtons[i][j].setBackground(Color.pink);
                        }
                    }
                }
            }
            if (rotate == 1) {       // check if a ship is going to be placed inside board limits

                if (pos[1] == dimension - 1) {
                    boardButtons[pos[0]][dimension - 1].setBackground(Color.red);
                    isOk = false;
                } else if (pos[1] + clicked - 1 < dimension) {
                    for (int num = pos[1]; num < pos[1] + clicked; num++) {
                        boardButtons[pos[0]][num].setBackground(Color.pink);
                    }
                    isOk = true;
                } else if (pos[1] + clicked - 1 > dimension - 1) {
                    {
                        for (int num = pos[1]; num < dimension; num++) {
                            boardButtons[pos[0]][num].setBackground(Color.red);
                            if (num + 1 >= dimension) {
                                break;
                            }
                        }
                        isOk = false;
                    }
                }

            } else if (rotate == -1) {
                if (pos[0] == dimension - 1) {
                    boardButtons[dimension - 1][pos[1]].setBackground(Color.red);
                    isOk = false;
                } else if (pos[0] + clicked - 1 < dimension) {
                    for (int num = pos[0]; num < pos[0] + clicked; num++) {
                        boardButtons[num][pos[1]].setBackground(Color.pink);
                    }
                    isOk = true;
                } else if (pos[0] + clicked - 1 > dimension - 1) {
                    {
                        for (int num = pos[0]; num < dimension; num++) {
                            boardButtons[num][pos[1]].setBackground(Color.red);
                            if (num + 1 >= dimension) {
                                break;
                            }
                        }
                        isOk = false;
                    }
                }
            }
        }
        for (String s : shipSelectedSet) {
            if (!positionsSet.isEmpty() && positionsSet.contains(s)) {
                contains = true;
            }
        }
    }

//    Function findCursorPosition() returns the actual position of the cursor.2 numbers , x and y position.
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

//    Function placeShips() place the ship in cursor position only when it is inside board limits.
    public boolean placeShips(int clicked, String cursor_pos) {
        boolean ret = false;
        int num;
        int clicked1 = clicked;
        int[] pos = findCursorPosition(cursor_pos);
        if (clicked == 31 || clicked == 32) {
            clicked1 = 3;
        }
        if (isOk) {
            if (rotate == 1) {
                for (num = pos[1]; num < pos[1] + clicked1; num++) {
                    positionsSet.add(boardButtons[pos[0]][num].getName());
                    boardButtons[pos[0]][num].setBackground(Color.pink);
                    ret = true;
                }

            } else if (rotate == -1) {
                for (num = pos[0]; num < pos[0] + clicked1; num++) {
                    positionsSet.add(boardButtons[num][pos[1]].getName());
                    boardButtons[num][pos[1]].setBackground(Color.pink);
                    ret = true;
                }
            }
            for (int i = 0; i < 5; i++) {          //Add actionlisteners into boat board buttons.
                for (int j = 0; j < 5; j++) {
                    if (buttons[i][j].isEnabled()) {
                        if (buttons[i][j].getName().equals(Integer.toString(clicked))) {
                            buttons[i][j].setBackground(Color.white);
                            buttons[i][j].setEnabled(false);
                            disabledShipsSet.add(buttons[i][j].getName());
                        }
                    }
                }
            }
            clicked = -1;
        }
        if (disabledShipsSet.size() == 5) {
            startbtn.setEnabled(true);
        }
        return ret;
    }
}
