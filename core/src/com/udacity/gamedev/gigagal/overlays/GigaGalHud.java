package com.udacity.gamedev.gigagal.overlays;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.gamedev.gigagal.utilities.Assets;
import com.udacity.gamedev.gigagal.utilities.Constants;
import com.udacity.gamedev.gigagal.utilities.Utils;

/**
 * Created by mkemp on 3/27/18.
 */

public class GigaGalHud {

    public Viewport viewport;
    BitmapFont font;

    public GigaGalHud() {
        viewport = new ExtendViewport(Constants.HUD_VIEWPORT_SIZE, Constants.HUD_VIEWPORT_SIZE);
        font = new BitmapFont();
        font.getData().setScale(1);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public void render(SpriteBatch batch, int lives, int ammo, int score) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        // Draw text
        String hudString =
                Constants.HUD_SCORE_LABEL + score + "\n" +
                Constants.HUD_AMMO_LABEL + ammo;
        font.draw(batch,
                hudString,
                Constants.HUD_MARGIN,
                viewport.getWorldHeight() - Constants.HUD_MARGIN
        );

        // Draw lives
        TextureRegion region = Assets.instance.gigaGalAssets.standingRight;
        for (int i = 1; i <= lives; i++) {
            Vector2 drawPosition = new Vector2(
                    viewport.getWorldWidth() - i * (Constants.HUD_MARGIN / 2 + region.getRegionWidth()),
                    viewport.getWorldHeight() - Constants.HUD_MARGIN - region.getRegionHeight()
            );
            Utils.drawTextureRegion(batch, region, drawPosition);
        }

        batch.end();
    }
}
