package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.gigagal.Level;
import com.udacity.gamedev.gigagal.utilities.Assets;
import com.udacity.gamedev.gigagal.utilities.Constants;
import com.udacity.gamedev.gigagal.utilities.Enums.Direction;
import com.udacity.gamedev.gigagal.utilities.Utils;

/**
 * Created by mkemp on 3/16/18.
 */

public class Bullet {

    private Level level;
    private Direction direction;
    public Vector2 position;
    public boolean active;

    public Bullet(Level level, Vector2 position, Direction direction) {
        this.level = level;
        this.position = position;
        this.direction = direction;

        this.active = true;
    }

    public void update(float delta) {

        // Move in direction
        switch (direction) {
            case LEFT:
                position.x -= delta * Constants.BULLET_MOVE_SPEED;
                break;
            default:
                position.x += delta * Constants.BULLET_MOVE_SPEED;
                break;
        }

        // Fly off screen
        float worldWidth = level.getViewport().getWorldWidth();
        float cameraX = level.getViewport().getCamera().position.x;
        if (position.x > cameraX + worldWidth / 2 ||
                position.x < cameraX - worldWidth / 2) {
            active = false;
        }

        // Hit enemies
        for (Enemy enemy : level.getEnemies()) {
            if (position.dst(enemy.position) < Constants.ENEMY_HIT_DETECTION_RADIUS) {
                level.spawnExplosion(position);
                active = false;
                enemy.health -= 1;
            }
        }
    }

    public void render(SpriteBatch batch) {
        TextureRegion region = Assets.instance.bulletAssets.bullet;
        Utils.drawTextureRegion(batch, region, position, Constants.BULLET_CENTER);
    }
}
