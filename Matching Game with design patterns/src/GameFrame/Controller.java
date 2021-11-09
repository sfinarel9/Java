package GameFrame;

import Additionals.CountDown;

/**
 *
 * @author sfina
 */

// MVC time left controller
// ScorePanel = View
// CountDown = Model
public class Controller {

    String countdown;
    int time;
    private ScorePanel view = new ScorePanel();
    private CountDown model;

    public Controller(int time) {
        this.time = time;
        model = new CountDown(this, time);
    }

    public void updateView() {
        view.add(model);
    }

    public void stopTimer() {
        model.stopTimer();
    }

    public ScorePanel getScorePanel() {
        return view;
    }

}
