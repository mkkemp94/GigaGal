package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.gigagal.utilities.Assets;
import com.udacity.gamedev.gigagal.utilities.Constants;
import com.udacity.gamedev.gigagal.utilities.Utils;

/**
 * Created by mkemp on 3/14/18.
 */

public class Enemy {

    private Platform platform;
    private Vector2 position;

    public Enemy(Platform platform) {
        this.platform = platform;
        this.position = new Vector2(platform.left, platform.top + Constants.ENEMY_CENTER.y);
    }

    public void update(float delta) {

    }

    public void render(SpriteBatch batch) {
        TextureAtlas.AtlasRegion region = Assets.instance.enemyAssets.enemy;
        Utils.drawTextureRegion(batch, region, position, Constants.ENEMY_CENTER);
    }
}
