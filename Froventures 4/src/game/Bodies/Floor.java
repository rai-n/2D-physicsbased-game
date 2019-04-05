package game.Bodies;

import city.cs.engine.*;

//Making a general type floor object
public class Floor extends Walker {
    public Floor(World world) {
        super(world);
        //Creating the shape
        Shape boxShape = new BoxShape(4, 0.5f);
    }


}


