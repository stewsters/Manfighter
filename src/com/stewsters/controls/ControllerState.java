package com.stewsters.controls;

public class ControllerState {
    //TODO: we probably should have some kinda direction enum
    public boolean move;
    public boolean attack;
    public boolean roll;

    public boolean lightHit;
    public boolean heavyHit;

    public ControllerState() {
        move = false;
        attack = false;
        roll = false;

        //This doesn't really make sense coming from here
        lightHit = false;
        heavyHit = false;
    }

    /**
     * This takes input from the keyboard and maps it to controller booleans.
     */
    public void update() {

    }
}
