package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public GigaGal gigaGal;
    private Array<Platform> platforms;

    public Level() {
        gigaGal = new GigaGal();
        platforms = new Array<Platform>();
        platforms.add(new Platform(70, 30, 20, 20));
        addDebugPlatforms();
    }

    public void update(float delta) {
        gigaGal.update(delta, platforms);
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        for (Platform platform : platforms) {
            platform.render(spriteBatch);
        }
        gigaGal.render(spriteBatch);
        spriteBatch.end();
    }

    private void addDebugPlatforms() {
        platforms.add(new Platform(100, 60, 20, 20));
        platforms.add(new Platform(70, 90, 20, 20));
//        platforms.add(new Platform(15, 100, 30, 20));
//        platforms.add(new Platform(75, 90, 100, 65));
//        platforms.add(new Platform(35, 55, 50, 20));
//        platforms.add(new Platform(10, 20, 20, 9));
    }
}
