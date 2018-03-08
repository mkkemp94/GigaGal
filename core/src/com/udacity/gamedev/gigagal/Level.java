package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.udacity.gamedev.gigagal.entities.GigaGal;
import com.udacity.gamedev.gigagal.entities.Platform;

/**
 * Created by mkemp on 3/6/18.
 * Holds on to the entities that make up a level.
 * This includes giga gal, platforms, enemies, bullets, etc
 */
public class Level {

    public static final String TAG = Level.class.getName();

    private GigaGal gigaGal;
    private Array<Platform> platforms;

    public Level() {
        gigaGal = new GigaGal();
        platforms = new Array<Platform>();
        platforms.add(new Platform(70, 30, 20, 20));
    }

    public void update(float delta) {
        gigaGal.update(delta);
    }

    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Platform platform : platforms) {
            platform.render(shapeRenderer);
        }
        shapeRenderer.end();

        spriteBatch.begin();
        gigaGal.render(spriteBatch);
        spriteBatch.end();
    }
}
