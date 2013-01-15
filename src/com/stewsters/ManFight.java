package com.stewsters;

import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Random;

import com.stewsters.ai.Faction;
import processing.core.PApplet;
import processing.core.PVector;

public class ManFight extends PApplet {

    private static final long serialVersionUID = -6056361700394447127L;

    public void setup() {
        World.x = 1000;
        World.y = 1000;

        this.setSize(World.x, World.y);
        this.scale(10);

        Faction.RED.image = loadImage("/home/bloodred/privatecode/Manfighter/assets/red_fighter.png");
        Faction.BLUE.image = loadImage("/home/bloodred/privatecode/Manfighter/assets/blue_fighter.png");
        Faction.YELLOW.image = loadImage("/home/bloodred/privatecode/Manfighter/assets/yellow_fighter.png");
        World.casualty = loadImage("/home/bloodred/privatecode/Manfighter/assets/casualty.png");

        // smooth();
        World.dudes = new LinkedHashMap<Integer, Man>();

        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            Faction faction;
            PVector starting;
            if (i % 2 == 0) {
                faction = Faction.YELLOW;
                starting = new PVector(r.nextInt(World.x / 4), r.nextInt(World.y));
            } else {
                faction = Faction.BLUE;
                starting = new PVector(r.nextInt(World.x / 4) + (3 * World.x / 4), r.nextInt(World.y));
            }
            Man newMan = new Man(faction, starting);
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
