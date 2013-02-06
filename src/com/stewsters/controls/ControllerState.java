package com.stewsters.controls;

public class ControllerState {
    public boolean attack;
    public boolean roll;

    public boolean lightHit;
    public boolean heavyHit;

    public ControllerState() {
        move = MoveDirection.none;
        attack = false;
        roll = false;

        //This doesn't really make sense coming from here
        lightHit = false;
        heavyHit = false;
    }

    /**
     * Everything below here has to do with movement
     */
    public MoveDirection move;

    private boolean move_up = false;
    private boolean move_right = false;
    private boolean move_down = false;
    private boolean move_left = false;

    private void updateMove(){
        //given current state of move, come up with a new desired movement

    }

    /**
     * This takes input from the keyboard and maps it to controller booleans.
     * @param key
     */
    public void push(char key) {
        switch (key){
            case('w'):case('W'):move_up = true ;break;
            case('d'):case('D'):move_right = true ;break;
            case('s'):case('S'):move_down = true ;break;
            case('a'):case('A'):move_left = true ;break;
            case('k'):case('K'):attack = true ;break;
            case('l'):case('L'):roll = true ;break;
        }

    }

    public void release(char key) {
        switch (key){
            case('w'):case('W'):move_up = false ;break;
            case('d'):case('D'):move_right = false ;break;
            case('s'):case('S'):move_down = false ;break;
            case('a'):case('A'):move_left = false ;break;
            case('k'):case('K'):attack = false ;break;
            case('l'):case('L'):roll = false ;break;
        }
    }
}
