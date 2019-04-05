package game.GameController;

import city.cs.engine.Walker;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//A class which is used to drop a rock at a given mouse position
public class BodyMove extends MouseAdapter {
    private Walker body;
    //Constructor to initialise the body field
    public BodyMove(Walker body) {
        this.body = body;
    }

    //Mouse movement is checked
    @Override
    public void mouseEntered(MouseEvent e) {
        PointerInfo cursor = MouseInfo.getPointerInfo();
        Point point = new Point(cursor.getLocation());
        Point procket = new Point(100, 100);
        //Throws the rock in a diagonal arc
        double angle = (Math.atan2(point.getY() - procket.getY(), point.getX() - procket.getX()) + (Math.PI / 2));
        float f = (float) angle;
        body.rotateDegrees(f);
    }

}