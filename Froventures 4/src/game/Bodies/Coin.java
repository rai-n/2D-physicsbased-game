package game.Bodies;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.Color;
import java.io.IOException;



//A coin object that can move around and collide with objects
public class Coin extends DynamicBody {
    private static final Shape shape = new CircleShape(0.9f);
    private static SoundClip pointGetSound;

    //Constructor to create a coin object.
    public Coin(World world) {
        super(world, shape);
        setFillColor(Color.yellow);
    }

    //Adding a sound file to the object
    static {
        try {
            pointGetSound = new SoundClip("data/Audio/coin.wav");
            System.out.println("Loading orange sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    //Playing the sound if the object is destroyed
    @Override
    public void destroy()
    {
        pointGetSound.play();
        super.destroy();
    }
}
