package game.GameController;

import city.cs.engine.SoundClip;
import game.Bodies.Frog;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;


//Key handler for Walker class
public class Controller extends KeyAdapter {
    private float JUMPING_SPEED = 7.5f;
    private static final float WALKING_SPEED = 10;
    private static SoundClip jump;
    private Frog body;
    private static boolean lookingRight = true;

    //Used to change directions
    private void ChangeDirection(){
            body.getImages().get(0).flipHorizontal();
    }
    public void setJumpSpeed(float x){
        this.JUMPING_SPEED = x;
    }

    //Constructor to create body
    public Controller(Frog body) {
        super();
        this.body = body;
    }

    //Used to load the sound for jumping
    static {
        try {
            jump = new SoundClip("data/Audio/jump.wav");
            System.out.println("Loading jump sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    //Handle key press events for walking and jumping.
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Q) { // Q = quit
            System.exit(0);
        } else if (code == KeyEvent.VK_SPACE) { // J = jump
            jump.play();
            Vec2 v = body.getLinearVelocity();
          // This is to ensure that the player cannot double jump.
            // Y component is only used because x component is a non constant variable
            if (Math.abs(v.y) > 0.1) {
                body.setAngle(0);
                Boolean boost = true;
                if (boost) {
                    body.setAngularVelocity(0);
                } else {
                    body.setAngularVelocity(0.4f);
                }
            }
            // Otherwise, jump
            if (Math.abs(v.y) < 0.5) {
                body.jump(JUMPING_SPEED);
            }
        } else if (code == KeyEvent.VK_A) {
         //   System.out.println("Pressed A");
            body.setAngularVelocity(6f);
            if (lookingRight){
                ChangeDirection();
                lookingRight = false;
           //     System.out.println("I'm flipping sides");
            }
            body.startWalking(-WALKING_SPEED * 1f); // 1 = walk left
            body.setAngularVelocity(1.6f);
            //Pressing D event
        } if (code == KeyEvent.VK_D) {

            body.setAngularVelocity(6f);
            if (!lookingRight){
                ChangeDirection();
            //    System.out.println("I'm flipping sides");
                lookingRight = true;

            }
            //More force for right component of travel to allow player to progress
            body.startWalking(WALKING_SPEED * 1f); // 2 = walk right
            body.setAngularVelocity(-0.69f);

        }
    }

    public void setBody(Frog body) {
        this.body = body;
    }

   //Handle key release events (stop walking).
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
           //something
        } else if (code == KeyEvent.VK_D) {
           //something
        }
    }



}
