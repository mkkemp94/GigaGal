package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.udacity.gamedev.gigagal.utilities.Assets;
import com.udacity.gamedev.gigagal.utilities.Constants;
import com.udacity.gamedev.gigagal.utilities.Enums;
import com.udacity.gamedev.gigagal.utilities.Utils;

/**
 * Created by mkemp on 3/14/18.
 */

public class Enemy {

    private Platform platform;
    public Vector2 position;
    private Enums.Direction direction;
    private long startTime;

    // TODO : Add health to enemy.
    public float health;

    public Enemy(Platform platform) {
        this.platform = platform;
        this.position = new Vector2(platform.left, platform.top + Constants.ENEMY_CENTER.y);
        direction = Enums.Direction.LEFT;
        startTime = TimeUtils.nanoTime();
        health = Constants.ENEMY_HEALTH;
    }

    public void update(float delta) {
        if (position.x <= platform.left) {
            position.x = platform.left;
            direction = Enums.Direction.RIGHT;
        } else if (position.x >= platform.right) {
            position.x = platform.right;
            direction = Enums.Direction.LEFT;
        }

        moveEnemy(delta);
    }

    private void moveEnemy(float delta) {
        switch (direction) {
            case LEFT:
                position.x -= delta * Constants.ENEMY_MOVEMENT_SPEED;
                break;
            default:
                position.x += delta * Constants.ENEMY_MOVEMENT_SPEED;
                break;
        }

        float bobMultiplier = 1 + MathUtils.sin(MathUtils.PI2 * Utils.secondsSince(startTime) / Constants.ENEMY_BOB_PERIOD);
        position.y = platform.top + Constants.ENEMY_CENTER.y + Constants.ENEMY_BOB_AMPLITUDE * bobMultiplier;
    }

    public void render(SpriteBatch batch) {
        TextureAtlas.AtlasRegion region = Assets.instance.enemyAssets.enemy;
        Utils.drawTextureRegion(batch, region, position, Constants.ENEMY_CENTER);
    }
}
