package com.stewsters.controls;

public class ControllerState {
    public boolean attack;
    public boolean roll;

    public boolean lightHit;
    public boolean heavyHit;

    public ControllerState() {
        move = Direction.none;
        attack = false;
        roll = false;

        //This doesn't really make sense coming from here
        lightHit = false;
        heavyHit = false;
    }

    /**
     * Everything below here has to do with movement
     */
    public Direction move;

    private boolean move_n = false;
    private boolean move_e = false;
    private boolean move_s = false;
    private boolean move_w = false;

    private void setMoveDir(Direction direction, Boolean pushed){
        switch (direction){
            case N: move_n = pushed; break;
            case E: move_e = pushed; break;
            case S: move_s = pushed; break;
            case W: move_w = pushed; break;
        }
    }


    private void updatePushMove(Direction direction){
        //given current state of move, come up with a new desired movement
        move = direction;
        setMoveDir(direction,true);
    }

    private void updateReleaseMove(Direction direction){

        if (direction == move){
            setMoveDir(direction,false);

            //see what is still pushed
            if(move_n){
                move = Direction.N;
            }else if(move_e){
                move = Direction.E;
            }else if(move_s){
                move = Direction.S;
            }else if(move_w){
                move = Direction.W;
            }else
                move = Direction.none;

        }else{
            setMoveDir(direction,false);
        }
    }

    /**
     * This takes input from the keyboard and maps it to controller booleans.
     * @param key
     */
    public void push(char key) {
        switch (key){
            case('w'):case('W'):updatePushMove(Direction.N); break;
            case('d'):case('D'):updatePushMove(Direction.E); break;
            case('s'):case('S'):updatePushMove(Direction.S); break;
            case('a'):case('A'):updatePushMove(Direction.W); break;
            case('k'):case('K'):attack = true ;break;
            case('l'):case('L'):roll = true ;break;
        }

    }

    public void release(char key) {
        switch (key){
            case('w'):case('W'):updateReleaseMove(Direction.N); break;
            case('d'):case('D'):updateReleaseMove(Direction.E); break;
            case('s'):case('S'):updateReleaseMove(Direction.S); break;
            case('a'):case('A'):updateReleaseMove(Direction.W); break;
            case('k'):case('K'):attack = false ;break;
            case('l'):case('L'):roll = false ;break;
        }
    }
}
