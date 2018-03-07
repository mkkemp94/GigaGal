package com.udacity.gamedev.gigagal.utilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mkemp on 3/6/18.
 * Contains constants for sprite names, dimensions, speeds, accelerations, etc.
 */

public class Constants {

    public static final Color BACKGROUND_COLOR = Color.SKY;

    /**
     * Sprites in this game are drawn at their natural size.
     * This size denotes the number of pixels that can fit to a screen.
     * We're operating in landscape mode, using an extend viewport,
     * so this is really the height of the world.
     */
    public static final float WORLD_SIZE = 128;

    public static final String TEXTURE_ATLAS = "images/gigagal.pack.atlas";
    public static final String SPRITE_NAME_STANDING_RIGHT = "standing-right";
    public static final String SPRITE_NAME_STANDING_LEFT = "standing-left";

    public static final Vector2 GIGAGAL_EYE_POSITION = new Vector2(16, 24);
    public static final float GIGAGAL_EYE_HEIGHT = 16f;

    public static final float GIGAGAL_MOVEMENT_SPEED = 96f;

    public static final float GRAVITATIONAL_ACCELERATION = 60f;
    public static final float GIGAGAL_JUMP_HEIGHT = 60f;
}
