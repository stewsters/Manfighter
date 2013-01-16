package com.stewsters;

import com.stewsters.ai.Faction;
import com.stewsters.ai.Group;
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
    public Group group;


    Man(Faction faction, PVector pos) {
        id = mancounter++;

        this.faction = faction;
        this.pos = pos;
    }

    void display(PApplet context) {
        if (life < 1) {
            context.image(World.casualty, pos.x - girth, pos.y - girth);
        } else
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
        Man groupLeader = null;
        if (group != null) {
            groupLeader = group.getLeader();
        }


        if (closest_friend != null) {
            if (min_friend_distance < girth) {
                flee(closest_friend,1f);
            }
        }

        // move toward him. If the distance is less than 1, attack
        if (closest_opponent != null) {
            if (min_oppenent_distance < girth) {
                //back off of them
                flee(closest_opponent,1f);
            } else if (life < 5) {
                if (min_oppenent_distance < 10) {
                    flee(closest_opponent,0.6f);
                } else {
                    advance(groupLeader,0.6f);
                }
            } else if (min_oppenent_distance < range) {
                // attack
                closest_opponent.life -= 1;
            } else {

                //we are the leader, advance.
                if (groupLeader == this) {
                    advance(closest_opponent,.5f);
                } else {
                    //move toward the leader
                    advance(groupLeader,1f);
                }
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

    private void advance(Man m,float percent) {
        PVector travel = PVector.sub(m.pos, pos);
        travel.normalize();
        travel.mult(percent * speed);
        pos.add(travel);
    }

    private void flee(Man m,float percent) {
        PVector travel = PVector.sub(m.pos, pos);
        travel.normalize();
        travel.mult((-percent) * speed);
        pos.add(travel);
    }
}
