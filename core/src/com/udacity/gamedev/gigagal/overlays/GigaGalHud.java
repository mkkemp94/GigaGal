package com.udacity.gamedev.gigagal.overlays;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.gamedev.gigagal.utilities.Constants;

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

    public void render(SpriteBatch batch) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        font.draw(batch, "Hi",
                viewport.getWorldWidth() / 2,
                viewport.getWorldHeight() / 4,
                0, Align.center, false);
        batch.end();
    }
}
