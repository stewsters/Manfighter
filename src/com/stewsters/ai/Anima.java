package com.stewsters.ai;


import com.stewsters.stuffs.Man;

public class Anima {

    public Man body;
    public Group group;
    public Man groupLeader = null;

    public float injuredLife = 3f;
    public float safeDistance = 30f;
    public float spacing = 20f;

    public Anima(Man body) {
        this.body = body;
    }


    /* Perhaps this should return a PVector with intended speed.*/
    public void react(Float deltaTime, Man closest_friend, float min_friend_distance, Man closest_opponent, float min_oppenent_distance) {

        if (group != null) {
            groupLeader = group.getLeader();
        }

        // move toward him. If the distance is less than 1, attack
        if (closest_opponent != null) {

            if (min_oppenent_distance < body.range) {
                closest_opponent.life -= (body.damage *deltaTime); // attack
            } else if (body.life < injuredLife) {
                if (min_oppenent_distance < safeDistance) {
                    body.flee(closest_opponent, body.speed * deltaTime);
                } else {
                    body.advance(groupLeader, body.speed * deltaTime);
                }
            } else {

                //if closest ally is too close, get some more room.  this may need to get weighted depending on distance.

                //we are the leader, advance.
                if (groupLeader == body) {
                    //hold
                    body.advance(closest_opponent, body.speed * deltaTime / 2);
                } else if(groupLeader.pos.dist(body.pos) > spacing){
                    //move toward the leader
                    body.advance(groupLeader, body.speed * deltaTime);
                }
            }
        }

    }


}
