package projectsemester2019;
/*
*
*author @eleftheria
*/
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class SaveResults extends JFrame {

    private JFileChooser fc;
    private File theFile;
    private GridPanel gpb;
    private int x;
    private String name;

    public SaveResults(int x, String name) {
        this.x = x;
        this.name = name;

    }

    public void doSave() {

        FileWriter myFile = null;
        FileWriter newFile = null;
        BufferedWriter buff = null;
        gpb = new GridPanel(x, name);
        try {
            myFile = new FileWriter("myFile.txt", true);

            buff = new BufferedWriter(myFile);
            buff.append(gpb.getWinner());
            buff.newLine();
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
