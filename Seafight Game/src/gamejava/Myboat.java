package gamejava;

/**
 *
 * @author sfina
 */
public class Myboat extends Boat implements BoatInterface {

    private String name;

    public Myboat(String name, int size) {
        super();
        this.name = name;
        setSize(size);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void directionChange() {  //Horizontal or Vertical
        setDirection();
    }

    @Override
    public void positionChange() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
