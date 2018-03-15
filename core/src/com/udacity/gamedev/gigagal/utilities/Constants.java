package com.udacity.gamedev.gigagal.utilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mkemp on 3/6/18.
 * Contains constants for sprite names, dimensions, speeds, accelerations, etc.
 */

public class Constants {

    // World/Camera
    public static final Color BACKGROUND_COLOR = Color.SKY;
    public static final float WORLD_SIZE = 160;
    public static final float KILL_PLANE = -100;
    public static final float GRAVITY = 10;
    public static final float CHASE_CAM_MOVE_SPEED = WORLD_SIZE;

    public static final String TEXTURE_ATLAS = "images/gigagal.pack.atlas";

    // Giga Gal
    public static final Vector2 GIGAGAL_EYE_POSITION = new Vector2(16, 24);
    public static final float GIGAGAL_EYE_HEIGHT = 16f;
    public static final float GIGAGAL_STANCE_WIDTH = 21f;
    public static final float GIGAGAL_HEIGHT = 23f;
    public static final float GIGAGAL_MOVEMENT_SPEED = 100;

    public static final float GIGAGAL_JUMP_SPEED = 200;
    public static final float GIGAGAL_JUMP_MAX_DURATION = 0.1f;

    public static final String STANDING_RIGHT   = "standing-right";
    public static final String STANDING_LEFT    = "standing-left";
    public static final String JUMPING_LEFT     = "jumping-left";
    public static final String JUMPING_RIGHT    = "jumping-right";
    public static final String WALKING_RIGHT_1  = "walk-1-right";
    public static final String WALKING_LEFT_1   = "walk-1-left";
    public static final String WALKING_RIGHT_2  = "walk-2-right";
    public static final String WALKING_LEFT_2   = "walk-2-left";
    public static final String WALKING_RIGHT_3  = "walk-3-right";
    public static final String WALKING_LEFT_3   = "walk-3-left";
    public static final float WALK_LOOP_FRAME_DURATION = 0.25f;

    // Platform
    public static final String PLATFORM         = "platform";
    public static final int PLATFORM_EDGE = 8;

    // Enemy
    public static final String ENEMY_SPRITE     = "enemy";
    public static final Vector2 ENEMY_CENTER = new Vector2(14, 22);
    public static final float ENEMY_MOVEMENT_SPEED = 30;
}
