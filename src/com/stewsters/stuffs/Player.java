package com.stewsters.stuffs;

import com.stewsters.ai.Faction;
import com.stewsters.animation.AnimationFSM;
import com.stewsters.controls.ControllerState;
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

        //get controls updated.
        c.update();

        //pass controls into animation
        animationFSM.update(c,deltaTime);

        //take the animationState for the character.  This is used to generate movement



    }

}
