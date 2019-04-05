package game.Bodies;

import city.cs.engine.*;


public class Bird extends Walker {
    //Constructor to create a bird object which doesn't collide with anything.
    public Bird(World world) {
        super(world);
        Shape shapeBird = new PolygonShape(-0.595f, -0.001f, -0.289f, 0.394f, 0.102f, 0.447f, 0.542f, -0.054f, 0.067f, -0.436f, -0.528f, -0.13f);
        Fixture birdFix = new GhostlyFixture(this, shapeBird);

    }



}