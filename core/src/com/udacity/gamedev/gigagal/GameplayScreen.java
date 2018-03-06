package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.udacity.gamedev.gigagal.utilities.Assets;
import com.udacity.gamedev.gigagal.utilities.Constants;

/**
 * Created by mkemp on 3/6/18.
 * Set up drawing environment
 * Set up viewport
 * Initialize assets class
 */
public class GameplayScreen extends ScreenAdapter {

    public static final String TAG = GameplayScreen.class.getName();

    private SpriteBatch spriteBatch;
    private ExtendViewport viewport;

    @Override
    public void show() {
        Assets.instance.init();

        spriteBatch = new SpriteBatch();
        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        Assets.instance.dispose();
        spriteBatch.dispose();
    }

    @Override
    public void render(float delta) {

        // TODO : Call update on the current level

        viewport.apply();

        Color bgColor = Constants.BACKGROUND_COLOR;
        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        // Get the standing right atlas region
        TextureAtlas.AtlasRegion region = Assets.instance.gigaGalAssets.standingRight;
        spriteBatch.draw(region.getTexture(),
                50,
                50,
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

        // TODO : Call render on the current level

        spriteBatch.end();
    }
}
