package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.gigagal.utilities.Assets;
import com.udacity.gamedev.gigagal.utilities.Constants;
import com.udacity.gamedev.gigagal.utilities.Utils;

/**
 * Created by mkemp on 3/20/18.
 */

public class Powerup {

    public Vector2 position;

    public Powerup(Vector2 position) {
        this.position = position;
    }

    public void render(SpriteBatch batch) {
        TextureRegion region = Assets.instance.powerupAssets.powerup;
        Utils.drawTextureRegion(batch, region, position, Constants.POWERUP_CENTER);
    }
}
