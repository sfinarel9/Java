package History_DAO_Pattern;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author sfina
 */
public class MyGridLayout implements HistoryDAO {

    JLabel lll;
    JFrame f = new JFrame();
    int num = 0, count = 0, lines = 0;
    String[] fields;
    Scanner nameInputStream = null, nameInputStream1 = null;

    public MyGridLayout() {
        try {
            nameInputStream = new Scanner(new FileInputStream("myFile.txt"));
            nameInputStream1 = new Scanner(new FileInputStream("myFile.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Problem opening file.");
            System.exit(0);
        }

        while (nameInputStream1.hasNextLine()) { //count how many lines are
            lines++;
            nameInputStream1.nextLine();
        }
        create_records();
        f.setLayout(new GridLayout((count / 2), 2)); // set layout ( each line is a game)
        f.setTitle("History");
        f.getContentPane().setBackground(new Color(229, 193, 255));
        f.setBounds(170, 120, 300, 300);
        f.setVisible(true);
    }

    @Override
    public void create_records() {
        while (nameInputStream.hasNextLine()) {
            num++;
            if (num == 3) {
                nameInputStream.nextLine();
                num = 0;
                continue;
            }
            System.out.println(num);
            if (num >= lines) {
                break;
            }
            count++;
            String line = nameInputStream.nextLine();
            fields = line.split("\\s+");
            String s0 = "  " + fields[0] + " " + fields[1] + " " + fields[2];
            lll = new JLabel(s0);

            lll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            f.add(lll);
        }
        nameInputStream.close();
    }
}
