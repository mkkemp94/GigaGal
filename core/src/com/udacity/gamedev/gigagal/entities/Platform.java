package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.udacity.gamedev.gigagal.utilities.Assets;

/**
 * Created by mkemp on 3/6/18.
 * Holds position and dimensions of a platform.
 * Knows how to draw itself.
 */

public class Platform {

    public static final String TAG = Platform.class.getName();

    private float top;
    private float bottom;
    private float left;
    private float right;

    public Platform(float left, float top, float width, float height) {
        this.top = top;
        this.bottom = top - height;
        this.left = left;
        this.right = left + width;
    }

    public void render(SpriteBatch spriteBatch) {
        float width = right - left;
        float height = top - bottom;

        Assets.instance.platformAssets.ninePatch.draw(spriteBatch,
                left - 1,
                bottom - 1,
                width + 2,
                height + 2
        );
    }
}
