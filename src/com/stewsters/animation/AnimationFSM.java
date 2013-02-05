package com.stewsters.animation;

import com.stewsters.controls.ControllerState;
import processing.core.PApplet;

/**
 * This is a state machine for keeping track of what a character is doing
 */

public class AnimationFSM {

    AnimationState currentState;
    float timeAnimationStarted = 0f; //time in seconds since this animation started

    //variable for how long we have been in our current state,
    // needed for frames and limited time operations

    public AnimationFSM() {
        currentState = AnimationState.STANDING;
    }

    // This will set the animation and clock for that animation
    private void setState(AnimationState newState, float deltaTime) {
        currentState = newState;
        timeAnimationStarted = deltaTime;
    }

    /**
     * This controls how the state moves
     */
    public void update(ControllerState c, float deltaTime) {

        if (c.heavyHit) {
            currentState = AnimationState.PRONE;
        } else if (c.lightHit && currentState != AnimationState.PRONE) {
            setState(AnimationState.STUNNED, deltaTime);
        } else {

            switch (currentState) {
                case STANDING:

                    if (c.roll)
                        setState(AnimationState.ROLLING, deltaTime);
                    else if (c.attack)
                        setState(AnimationState.ATTACK_SLASH, deltaTime);
                    else if (c.move)
                        setState(AnimationState.WALKING, deltaTime);

                    break;
                case WALKING:
                    if (c.attack) setState(AnimationState.ATTACK_THRUST, deltaTime);

                    if (!c.move) setState(AnimationState.STANDING, deltaTime);

                    if (c.roll) setState(AnimationState.ROLLING, deltaTime);

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


    //we might want to pull this out, so we can render multiple types of Animation
    public void render(PApplet context) {


    }

}
