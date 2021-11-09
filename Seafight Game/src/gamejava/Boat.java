package gamejava;

/**
 *
 * @author sfina
 */
public abstract class Boat {

    private int size;
    private boolean direction = false;

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setDirection() {
        if (direction == false) {
            direction = true;
        } else {
            direction = false;
        }
    }
}
