package com.udacity.gamedev.gigagal.utilities;

/**
 * Created by mkemp on 3/15/18.
 */

public class Enums {

    public enum Direction {
        RIGHT,
        LEFT
    }

    public enum JumpState {
        JUMPING,
        FALLING,
        GROUNDED
    }

    public enum WalkState {
        NOT_WALKING,
        WALKING
    }
}
