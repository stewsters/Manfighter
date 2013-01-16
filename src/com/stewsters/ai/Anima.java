package com.stewsters.ai;


import com.stewsters.stuffs.Man;

public class Anima {

    public Man body;
    public Group group;
    public Man groupLeader = null;

    public Anima(Man body) {
        this.body = body;
    }


    public void react(Man closest_friend, float min_friend_distance, Man closest_opponent, float min_oppenent_distance) {

        if (group != null) {
            groupLeader = group.getLeader();
        }

        if (closest_friend != null && (min_friend_distance < body.girth)) {
            body.flee(closest_friend, 1f);
        }
        // move toward him. If the distance is less than 1, attack
        else if (closest_opponent != null) {
            if (min_oppenent_distance < body.girth) {
                //back off of them
                body.flee(closest_opponent, 1f);
            } else if (body.life < 5) {
                if (min_oppenent_distance < 10) {
                    body.flee(closest_opponent, 0.6f);
                } else {
                    body.advance(groupLeader, 0.6f);
                }
            } else if (min_oppenent_distance < body.range) {
                // attack
                closest_opponent.life -= 1;
            } else {

                //we are the leader, advance.
                if (groupLeader == body) {
                    //hold
                    body.advance(closest_opponent, .5f);
                } else {
                    //move toward the leader
                    body.advance(groupLeader, 1f);
                }
            }
        }

    }


}
