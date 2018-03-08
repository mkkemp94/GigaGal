package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

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

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.BLACK);

        float width = right - left;
        float height = top - bottom;

        shapeRenderer.rect(left, bottom, width, height);
    }
}
