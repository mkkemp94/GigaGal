package com.udacity.gamedev.gigagal.utilities;

import com.badlogic.gdx.graphics.Color;

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
}
