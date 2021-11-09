package Additionals;

/**
 *
 * @author sfina
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;

public class SaveResults extends JFrame {

    private EndFrame end;
    private String name;

    public SaveResults(String name) {
        this.name = name;

    }

    public void doSave(int players_points, int computer_points) {

        FileWriter myFile = null;
        BufferedWriter buff = null;
        end = new EndFrame(players_points, computer_points, name);
        try {
            myFile = new FileWriter("myFile.txt", true);

            buff = new BufferedWriter(myFile);
            buff.append(end.getWinner());
            System.out.println("Saved");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buff.flush();
                buff.close();
                myFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
