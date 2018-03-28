package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.udacity.gamedev.gigagal.utilities.Assets;
import com.udacity.gamedev.gigagal.utilities.Constants;
import com.udacity.gamedev.gigagal.utilities.Utils;

/**
 * Created by mkemp on 3/20/18.
 */

public class Explosion {

    public Vector2 position;
    private long startTime;

    // Offset for time until this renders
    public float offset = 0;

    public Explosion(Vector2 position) {
        this.position = position;

        startTime = TimeUtils.nanoTime();
    }

    public void render(SpriteBatch spriteBatch) {
        if (!isFinished() && !yetToStart()) {
            Utils.drawTextureRegion(
                    spriteBatch,
                    Assets.instance.explosionAssets.explosion.
                            getKeyFrame(Utils.secondsSince(startTime) - offset),
                    position,
                    Constants.EXPLOSION_CENTER
            );
        }
    }

    public boolean yetToStart() {
        return Utils.secondsSince(startTime) - offset < 0;
    }

    public boolean isFinished() {
        return Assets.instance.explosionAssets.explosion.isAnimationFinished(
                Utils.secondsSince(startTime) - offset
        );
    }
}
