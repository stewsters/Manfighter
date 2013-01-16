package com.stewsters.stuffs;

import com.stewsters.World;
import processing.core.PApplet;
import processing.core.PVector;

public class Obstacle {

    static int obstacleCounter = 0;

    public int id;

    public int girth = 8;
    public PVector pos;

    public Obstacle(PVector pos) {
        id = obstacleCounter++;
        this.pos = pos;
    }

    public void display(PApplet context) {
        context.image(World.tree, pos.x - girth, pos.y - girth);
    }
}
