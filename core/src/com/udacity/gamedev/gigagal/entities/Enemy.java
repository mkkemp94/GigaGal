package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.gigagal.utilities.Assets;

/**
 * Created by mkemp on 3/14/18.
 */

public class Enemy {

    private Vector2 position;

    public Enemy(Vector2 position) {
        this.position = position;
    }

    public void render(SpriteBatch batch) {
        TextureAtlas.AtlasRegion region = Assets.instance.enemyAssets.enemy;
        batch.draw(
                region.getTexture(),
                position.x,
                position.y,
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                1,
                1,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false
        );
    }
}
