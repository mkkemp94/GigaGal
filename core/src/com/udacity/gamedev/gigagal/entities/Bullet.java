package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.gigagal.utilities.Assets;
import com.udacity.gamedev.gigagal.utilities.Constants;
import com.udacity.gamedev.gigagal.utilities.Enums.Direction;
import com.udacity.gamedev.gigagal.utilities.Utils;

/**
 * Created by mkemp on 3/16/18.
 */

public class Bullet {

    private Direction direction;
    private Vector2 position;

    public Bullet(Vector2 position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public void update(float delta) {
        switch (direction) {
            case LEFT:
                position.x -= delta * Constants.BULLET_MOVE_SPEED;
                break;
            default:
                position.x += delta * Constants.BULLET_MOVE_SPEED;
                break;
        }
    }

    public void render(SpriteBatch batch) {
        TextureRegion region = Assets.instance.bulletAssets.bullet;
        Utils.drawTextureRegion(batch, region, position, Constants.BULLET_CENTER);
    }
}
