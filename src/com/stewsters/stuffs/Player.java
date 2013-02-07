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

        //pass controls into animation
        animationFSM.update(c,deltaTime);

        //take the animationState for the character.  This is used to generate movement
        if(animationFSM.currentState == AnimationState.WALKING){
            switch (animationFSM.facing){
                case N: ; break;
                case E: ; break;
                case S: ; break;
                case W: ; break;
            }
        }

    }
    private void move(float x, float y, float deltaTime){
        PVector travel = PVector.sub(new PVector(x,y), pos); //todo: this could be static, or from the direction
        travel.normalize();
        travel.mult(deltaTime * speed);
        pos.add(travel);
    }

    //TODO: display will need to call animationFSM.render()

}
