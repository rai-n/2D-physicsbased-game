package game.Bodies;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
//An object which is used to represent the boss and is damaged by snakes
public class CthuluHand extends Walker {

    public CthuluHand(World w) {
        super(w);
        //Shape is built
        Shape hand = new PolygonShape(-0.84f,-2.43f, -0.76f,2.35f, 0.41f,2.35f, 0.56f,-2.47f);
        Fixture handFix = new SolidFixture(this, hand);

    }

}