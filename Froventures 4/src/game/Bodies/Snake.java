package game.Bodies;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

//A general type snake object is created to attack the frog
public class Snake extends Walker {
    private Fixture snakefix;
    private Fixture snakeFixB;
    private static SoundClip hiss;

    //Constructor to create the object
    public Snake(World world) {
        super(world);
        Shape shapeSnakeB = new PolygonShape(-1.73f,-1.26f, -1.47f,-1.52f, -0.65f,-1.76f, 0.84f,-1.7f, 1.14f,-1.2f, 0.87f,-0.58f, -1.06f,-0.44f, -1.64f,-0.89f);
        Fixture snakeFix = new SolidFixture(this, shapeSnakeB);
        Shape shapeSnakeH = new PolygonShape(0.37f,-0.42f, 0.09f,1.08f, -0.88f,1.07f, -0.72f,-0.4f, -0.25f,-0.46f);
        Fixture snakeFixH = new SolidFixture(this, shapeSnakeH);

        this.snakefix = snakeFixH;
        this.snakeFixB = snakeFix;
    }
    //Adding a sound file to the object
    static {
        try {
            hiss= new SoundClip("data/Audio/hiss.wav");
            System.out.println("Loading peel sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    //Playing a sound if the object is destroyed
    @Override
    public void destroy()
    {
        hiss.play();
        super.destroy();
    }


    public Fixture getSnakeFix(){
        return this.snakefix;
    }

    public Fixture getFBody(){
        return this.snakeFixB;
    }


}


