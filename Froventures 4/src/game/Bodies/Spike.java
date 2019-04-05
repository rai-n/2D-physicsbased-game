package game.Bodies;

import city.cs.engine.*;
import city.cs.engine.Shape;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

//A spike object which can damage the frog it touches it
public class Spike extends DynamicBody {
    //Constructor to create the object
    public Spike(World world) {
        super(world);
        //Creating the shape
        Shape shape = new PolygonShape(0.246f,0.615f, -0.231f,0.565f, -0.249f,-0.372f, -0.049f,-0.59f, 0.186f,-0.39f, 0.249f,0.548f);
        Fixture spikeFix = new SolidFixture(this, shape);
    }

}
