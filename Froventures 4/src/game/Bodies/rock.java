package game.Bodies;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

//A rock object which the frog can throw
public class rock extends DynamicBody {

    private static final float radius = 0.25f;
    private static final Shape rockShape = new CircleShape(radius);
    private static final BodyImage rockImage = new BodyImage("data/rock.png", 2 * radius);
    private static SoundClip rock;

    //Constructor to create the rock object
    public rock (World world) {
        super(world, rockShape);
        addImage(rockImage);

    }
    //Adding a sound file to the object
    static {
        try {
            rock = new SoundClip("data/Audio/rock.wav");
            System.out.println("Loading rock sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    //Playing a sound if the object is destroyed
    @Override
    public void destroy()
    {
        rock.play();
        super.destroy();
    }
}
