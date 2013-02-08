package com.stewsters.stuffs;

import com.stewsters.ai.Faction;
import com.stewsters.animation.AnimationFSM;
import com.stewsters.animation.AnimationState;
import com.stewsters.controls.ControllerState;
import com.stewsters.controls.Direction;
import processing.core.PVector;

public class Player extends Man{

    AnimationFSM animationFSM;
    ControllerState c;

    public Player(Faction faction, PVector pos, ControllerState c){
        super( faction,  pos);
        this.c = c;
        this.animationFSM = new AnimationFSM();
    }

    @Override
    public void act(float deltaTime) {

        System.out.println(animationFSM.currentState.name() + " " + animationFSM.facing.name());

        //pass controls into animation
        animationFSM.update(c,deltaTime);

        //take the animationState for the character.  This is used to generate movement
        if(animationFSM.currentState == AnimationState.WALKING){
            move(animationFSM.facing,deltaTime);
        }

    }
    private void move(Direction d, float deltaTime){
        PVector travel = d.unitVector.get();
        travel.normalize();
        travel.mult(deltaTime * speed);
        pos.add(travel);
    }

    //TODO: display will need to call animationFSM.render()

}
