package game.ListenersUpdater;

import city.cs.engine.Body;
import city.cs.engine.StepEvent;
import city.cs.engine.WorldView;
//Class used to assign special tracking features
public class spTracker extends Tracker {
    private WorldView view;
    private Body body;

    //Constructor to initialise the objects.
    public spTracker(WorldView view, Body body) {
        super(view, body);
        this.view = view;
        this.body = body;
    }

    @Override
    public void postStep(StepEvent e) {
    }

}
