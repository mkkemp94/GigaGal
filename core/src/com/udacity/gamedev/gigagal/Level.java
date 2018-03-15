package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.udacity.gamedev.gigagal.entities.Enemy;
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
    public Enemy enemy;
    private Array<Platform> platforms;

    public Level() {
        platforms = new Array<Platform>();
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
        enemy.render(spriteBatch);
        spriteBatch.end();
    }

    private void addDebugPlatforms() {
//        platforms.add(new Platform(100, 60, 20, 20));
//        platforms.add(new Platform(70, 90, 20, 20));
//        platforms.add(new Platform(70, 30, 20, 20));
//        platforms.add(new Platform(165, 55, 70, 10));
//        platforms.add(new Platform(210, 90, 30, 20));
//        platforms.add(new Platform(250, 70, 20, 10));

        platforms.add(new Platform(15, 100, 30, 20));
        platforms.add(new Platform(75, 90, 100, 65));
        platforms.add(new Platform(35, 55, 50, 20));
        platforms.add(new Platform(10, 20, 20, 9));
        platforms.add(new Platform(100, 110, 30, 9));
        platforms.add(new Platform(200, 130, 30, 40));
        platforms.add(new Platform(150, 150, 30, 9));
        platforms.add(new Platform(150, 180, 30, 9));
        platforms.add(new Platform(200, 200, 9, 9));
        platforms.add(new Platform(280, 100, 30, 9));

        platforms.add(new Platform(10, 20, 20, 9));

        gigaGal = new GigaGal(new Vector2(15, 40));
        enemy = new Enemy(new Vector2(110, 110));
    }
}
