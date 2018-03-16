package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
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

    private Viewport viewport;

    public GigaGal gigaGal;
    private Array<Platform> platforms;
    private DelayedRemovalArray<Enemy> enemies;

    public Level(Viewport viewport) {
        this.viewport = viewport;
        initializeDebugLevel();
    }

    public void update(float delta) {
        gigaGal.update(delta, platforms);
        for (int i = 0; i < enemies.size; i++) {
            Enemy enemy = enemies.get(i);
            enemy.update(delta);
        }
    }

    public void render(SpriteBatch spriteBatch) {
        for (Platform platform : platforms) {
            platform.render(spriteBatch);
        }

        for (Enemy enemy : enemies) {
            enemy.render(spriteBatch);
        }

        gigaGal.render(spriteBatch);
    }

    private void initializeDebugLevel() {
//        platforms.add(new Platform(100, 60, 20, 20));
//        platforms.add(new Platform(70, 90, 20, 20));
//        platforms.add(new Platform(70, 30, 20, 20));
//        platforms.add(new Platform(165, 55, 70, 10));
//        platforms.add(new Platform(210, 90, 30, 20));
//        platforms.add(new Platform(250, 70, 20, 10));

        gigaGal = new GigaGal(new Vector2(15, 40), this);
        platforms = new Array<Platform>();
        enemies = new DelayedRemovalArray<Enemy>();

        platforms.add(new Platform(15, 100, 30, 20));

        Platform enemyPlatform = new Platform(75, 90, 100, 65);
        platforms.add(enemyPlatform);

        enemies.add(new Enemy(enemyPlatform));

        platforms.add(new Platform(35, 55, 50, 20));
        platforms.add(new Platform(10, 20, 20, 9));

//        platforms.add(new Platform(100, 110, 30, 9));
//        platforms.add(new Platform(150, 150, 30, 9));
//        platforms.add(new Platform(150, 180, 30, 9));
//        platforms.add(new Platform(200, 200, 9, 9));
//        platforms.add(new Platform(280, 100, 30, 9));
    }

    public Array<Platform> getPlatforms() {
        return platforms;
    }

    public DelayedRemovalArray<Enemy> getEnemies() {
        return enemies;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public GigaGal getGigaGal() {
        return gigaGal;
    }

    public void setGigaGal(GigaGal gigaGal) {
        this.gigaGal = gigaGal;
    }
}
