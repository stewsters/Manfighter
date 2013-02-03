package com.stewsters.animation;

import processing.core.PApplet;

/**
 * This is a state machine for keeping track of what a character is doing
 */

public class AnimationFSM {

    AnimationState currentState;
    long timeAnimationStarted = 0;

    //variable for how long we have been in our current state,
    // needed for frames and limited time operations

    public AnimationFSM() {
        currentState = AnimationState.STANDING;
    }

    public void setState(AnimationState newState){
	currentState = newState;
    }

    /**
     * This controls how the state moves
     */
    public void update() {


        boolean move = false;
        boolean attack = false;
        boolean roll = false;

        boolean lightHit = false;
        boolean heavyHit = false;


        if (heavyHit) {
            currentState = AnimationState.PRONE;
        } else if (lightHit && currentState != AnimationState.PRONE) {
            currentState = AnimationState.STUNNED;
        } else {

            switch (currentState) {
                case STANDING:

                    if(roll) currentState = AnimationState.ROLLING;

                    if(move) currentState = AnimationState.WALKING;

                    if(attack) currentState = AnimationState.ATTACK_SLASH;

                    break;
                case WALKING:
                    if (attack) currentState = AnimationState.ATTACK_THRUST;

                    if (!move) currentState = AnimationState.STANDING;

                    if (roll) currentState = AnimationState.ROLLING;

                    break;
                case ROLLING:
                    
                    break;
                case ATTACK_THRUST:

                    break;
                case ATTACK_SLASH:

                    break;
                case STUNNED:
                    //time requirement

                    break;
                case PRONE:
                    //time requirement

                    break;
                case GETTINGUP:
                    //time requirement

                    break;
                default:

                    break;
            }
        }

    }


    //we might want to pull this out
    public void render(PApplet context) {

    }

}
