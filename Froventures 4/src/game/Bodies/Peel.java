package game.Bodies;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.Color;
import java.io.IOException;

//Peel object which makes the frog fall down
public class Peel extends DynamicBody {
    private static SoundClip peelHitSound;
    public Peel(World world) {
        super(world);
        Shape shape = new PolygonShape(-0.615f,0.224f, 0.007f,0.321f, 0.701f,0.419f, 0.364f,-0.267f, -0.487f,0.044f);
        Fixture peelFixA = new SolidFixture(this, shape);
        Shape shape2 = new PolygonShape(0.067f,-0.215f, -0.345f,-0.428f, -0.765f,-0.335f, -0.214f,-0.143f, -0.082f,-0.113f);
        Fixture peelFixB = new SolidFixture(this, shape2);
    }
    //Adding a sound file to the object
    static {
        try {
            peelHitSound = new SoundClip("data/Audio/peel.wav");
            System.out.println("Loading peel sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    //Playing the sound if the object is destroyed
    @Override
    public void destroy()
    {
        peelHitSound.play();
        super.destroy();
    }

}
