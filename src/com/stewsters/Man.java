package com.stewsters;

import java.awt.Color;

import com.stewsters.ai.Faction;
import processing.core.PApplet;
import processing.core.PVector;

public class Man {
    static int mancounter = 0;

    Faction faction;
    public PVector pos;
    public int id;

    public int life = 10;
    public int speed = 1;

    public int girth = 8;
    public int range = 16;
    public Order order;


    Man(Faction faction, PVector pos) {
        id = mancounter++;

        this.faction = faction;
        this.pos = pos;
    }

    void display(PApplet context) {
        context.image(faction.image, pos.x - girth, pos.y - girth);
    }

    void act() {
        if (life < 1) {
//			c = c.darker();
            return;
            // World.dudes.remove(id);
            // return;
        }
        // find nearest enemy man
        Man closest_opponent = null;
        Man closest_friend = null;
        float min_oppenent_distance = Float.MAX_VALUE;
        float min_friend_distance = Float.MAX_VALUE;
        for (Man m : World.dudes.values()) {
            if (m.id != id && m.life > 0) {
                float dist = pos.dist(m.pos);
                if (m.faction == faction) {
                    if (dist < min_friend_distance) {
                        closest_friend = m;
                        min_friend_distance = dist;
                    }
                } else {
                    if (dist < min_oppenent_distance) {
                        closest_opponent = m;
                        min_oppenent_distance = dist;
                    }
                }

            }
        }
        // move toward him. If the distance is less than 1, attack

        if (closest_friend != null) {
            if (min_friend_distance < girth) {
                flee(closest_friend);
            }
        }

        if (closest_opponent != null) {
            if (min_oppenent_distance < girth || (min_oppenent_distance < 100 && life < 5)) {
                //back off of them
                flee(closest_opponent);
            } else if (min_oppenent_distance < range) {
                // attack
                closest_opponent.life -= 1;
            } else {
                advance(closest_opponent);
            }
        }

        //worldwrap
        if (pos.x > World.x)
            pos.x -= World.x;
        else if (pos.x < 0)
            pos.x += World.x;

        if (pos.y > World.y)
            pos.y += World.y;
        else if (pos.y < 0)
            pos.y += World.y;
    }

    private void advance(Man m) {
        PVector travel = PVector.sub(m.pos, pos);
        travel.normalize();
        travel.mult(speed);
        pos.add(travel);
    }

    private void flee(Man m) {
        PVector travel = PVector.sub(m.pos, pos);
        travel.normalize();
        travel.mult((-.9f) * speed);
        pos.add(travel);
    }
}
