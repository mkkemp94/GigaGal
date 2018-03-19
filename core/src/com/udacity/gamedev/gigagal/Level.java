package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.gamedev.gigagal.entities.Bullet;
import com.udacity.gamedev.gigagal.entities.Enemy;
import com.udacity.gamedev.gigagal.entities.GigaGal;
import com.udacity.gamedev.gigagal.entities.Platform;
import com.udacity.gamedev.gigagal.utilities.Assets;
import com.udacity.gamedev.gigagal.utilities.Constants;
import com.udacity.gamedev.gigagal.utilities.Enums.Direction;
import com.udacity.gamedev.gigagal.utilities.Utils;

/**
 * Created by mkemp on 3/6/18.
 * Holds on to the entities that make up a level.
 * This includes giga gal, platforms, enemies, bullets, etc
 */
public class Level {

    public static final String TAG = Level.class.getName();

    private Viewport viewport;

    private GigaGal gigaGal;
    private Array<Platform> platforms;
    private DelayedRemovalArray<Enemy> enemies;
    private DelayedRemovalArray<Bullet> bullets;

    public Level(Viewport viewport) {
        this.viewport = viewport;
        initializeDebugLevel();
    }

    public void update(float delta) {
        gigaGal.update(delta, platforms);

//        Direction direction;
//        if (MathUtils.randomBoolean()) {
//            direction = Direction.RIGHT;
//        } else {
//            direction = Direction.LEFT;
//        }
//
//        float x = (MathUtils.random(viewport.getWorldWidth()));
//        float y = (MathUtils.random(viewport.getWorldHeight()));
//
//        Vector2 position = new Vector2(x, y);
//        spawnBullet(position, direction);

        for (Bullet bullet : bullets) {
            bullet.update(delta);
        }

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

        for (Bullet bullet : bullets) {
            bullet.render(spriteBatch);
        }

        Utils.drawTextureRegion(
                spriteBatch,
                Assets.instance.powerupAssets.powerup,
                new Vector2(50, 0),
                Constants.POWERUP_CENTER
        );

        Utils.drawTextureRegion(
                spriteBatch,
                Assets.instance.explosionAssets.animation.getKeyFrame(0),
                new Vector2(0, 50),
                Constants.EXPLOSION_CENTER
        );

        Utils.drawTextureRegion(
                spriteBatch,
                Assets.instance.explosionAssets.animation.getKeyFrame(Constants.EXPLOSION_DURATION * 0.5f),
                new Vector2(50, 50),
                Constants.EXPLOSION_CENTER
        );

        Utils.drawTextureRegion(
                spriteBatch,
                Assets.instance.explosionAssets.animation.getKeyFrame(Constants.EXPLOSION_DURATION * 0.75f),
                new Vector2(100, 50),
                Constants.EXPLOSION_CENTER
        );
    }

    private void initializeDebugLevel() {

        gigaGal = new GigaGal(new Vector2(15, 40), this);
        platforms = new Array<Platform>();
        enemies = new DelayedRemovalArray<Enemy>();
        bullets = new DelayedRemovalArray<Bullet>();

        platforms.add(new Platform(15, 100, 30, 20));

        Platform enemyPlatform = new Platform(75, 90, 100, 65);
        platforms.add(enemyPlatform);

        enemies.add(new Enemy(enemyPlatform));
        platforms.add(new Platform(35, 55, 50, 20));
        platforms.add(new Platform(10, 20, 20, 9));
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

    public void spawnBullet(Vector2 position, Direction direction) {
        bullets.add(new Bullet(position, direction));
    }
}
