package com.stewsters;

import com.stewsters.ai.Faction;
import com.stewsters.ai.Group;
import com.stewsters.controls.ControllerState;
import com.stewsters.stuffs.Man;
import com.stewsters.stuffs.Obstacle;
import com.stewsters.stuffs.Player;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.LinkedHashMap;
import java.util.Random;

public class MotionTest extends PApplet {

    private static final long serialVersionUID = -6056361700394447127L;
    private Long lastNano = null;
    private PImage ground;
    Player player;
    ControllerState c;

    @Override
    public void setup() {
        World.x = 512;
        World.y = 512;

        ground = createImage(World.x, World.y, RGB);
        ground.loadPixels();
        for (int i = 0; i < ground.pixels.length; i++) {
            ground.pixels[i] = color(255, 255, 255);
        }
        ground.updatePixels();

        this.setSize(World.x, World.y);
//        this.scale(10);

        Faction.RED.image = loadImage("/home/bloodred/privatecode/Manfighter/assets/red_fighter.png");
        Faction.BLUE.image = loadImage("/home/bloodred/privatecode/Manfighter/assets/blue_fighter.png");
        Faction.YELLOW.image = loadImage("/home/bloodred/privatecode/Manfighter/assets/yellow_fighter.png");
        World.casualty = loadImage("/home/bloodred/privatecode/Manfighter/assets/casualty.png");
        World.tree = loadImage("/home/bloodred/privatecode/Manfighter/assets/tree.png");

        // smooth();
        World.dudes = new LinkedHashMap<Integer, Man>();
        World.obstacles = new LinkedHashMap<Integer, Obstacle>();

        Random r = new Random();

        Group blueGroup = new Group();
        Group yellowGroup = new Group();


        for (int i = 0; i < 100; i++) {
            Obstacle obstacle = new Obstacle(new PVector(r.nextInt(World.x), r.nextInt(World.y)));
            World.obstacles.put(obstacle.id, obstacle);
        }

//        for (int i = 0; i < 200; i++) {
//            PVector starting;
//            Man newMan;
//            if (i % 2 == 0) {
//                starting = new PVector(r.nextInt(World.x / 4), r.nextInt(World.y / 2));
//                newMan = new Man(Faction.YELLOW, starting);
//
//                yellowGroup.join(newMan);
//            } else {
//                starting = new PVector(r.nextInt(World.x / 4) + (3 * World.x / 4), r.nextInt(World.y / 2));
//                newMan = new Man(Faction.BLUE, starting);
//                blueGroup.join(newMan);
//            }
//
//            World.dudes.put(newMan.id, newMan);
//
//        }
        c = new ControllerState();
        player = new Player(Faction.RED, new PVector(World.x / 2, World.y / 2), c);

    }

    //get controls updated.
    @Override
    public void keyPressed() {
        c.push(key);
    }

    @Override
    public void keyReleased(){
        c.release(key);
    }

    @Override
    public void draw() {

        if (lastNano == null) {
            lastNano = System.nanoTime() - 1;
        }

        long thisNano = System.nanoTime();
        long timePassed = thisNano - lastNano;
        float deltaTime = timePassed / 1000000000f;
        lastNano = thisNano;

        background(0, 128, 0);
        image(ground, 0, 0);
//        for (Man m : World.dudes.values()) {
//            m.act(deltaTime);
//        }
//        for (Man m : World.dudes.values()) {
//            m.display(this);
//        }

        for (Obstacle obstacle : World.obstacles.values()) {
            obstacle.display(this);
        }

        player.act(deltaTime);
        player.display(this);

    }

}
