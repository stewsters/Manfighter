package com.stewsters;

import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Random;

import com.stewsters.ai.Faction;
import com.stewsters.ai.Group;
import processing.core.PApplet;
import processing.core.PVector;

public class ManFight extends PApplet {

    private static final long serialVersionUID = -6056361700394447127L;

    public void setup() {
        World.x = 1000;
        World.y = 1000;

        this.setSize(World.x, World.y);
        this.scale(10);

        Faction.RED.image = loadImage("/home/bloodred/code/Manfighter/assets/red_fighter.png");
        Faction.BLUE.image = loadImage("/home/bloodred/code/Manfighter/assets/blue_fighter.png");
        Faction.YELLOW.image = loadImage("/home/bloodred/code/Manfighter/assets/yellow_fighter.png");
        World.casualty = loadImage("/home/bloodred/code/Manfighter/assets/casualty.png");

        // smooth();
        World.dudes = new LinkedHashMap<Integer, Man>();

        Random r = new Random();

        Group blueGroup = new Group();
        Group yellowGroup = new Group();

        for (int i = 0; i < 2000; i++) {
            Faction faction;
            PVector starting;
            Man newMan;
            if (i % 2 == 0) {
                starting = new PVector(r.nextInt(World.x / 4), r.nextInt(World.y));
                newMan = new Man(Faction.YELLOW, starting);

                yellowGroup.join(newMan);
            } else {
                starting = new PVector(r.nextInt(World.x / 4) + (3 * World.x / 4), r.nextInt(World.y));
                newMan = new Man(Faction.BLUE, starting);
                blueGroup.join(newMan);
            }

            World.dudes.put(newMan.id, newMan);

        }
    }

    public void draw() {

        background(0,128,0);
        for (Man m : World.dudes.values()) {
            m.act();
        }
        for (Man m : World.dudes.values()) {
            m.display(this);
        }

    }
    /*
	 * if(mousePressed) { fill(0); } else { fill(255); } ellipse(mouseX, mouseY,
	 * 80, 80); }
	 */

}
