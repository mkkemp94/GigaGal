package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.udacity.gamedev.gigagal.utilities.Assets;
import com.udacity.gamedev.gigagal.utilities.ChaseCam;
import com.udacity.gamedev.gigagal.utilities.Constants;
import com.udacity.gamedev.gigagal.utilities.LevelLoader;

/**
 * Created by mkemp on 3/6/18.
 * Set up drawing environment
 * Set up gameplayViewport
 * Initialize assets class
 */
public class GameplayScreen extends ScreenAdapter {

    public static final String TAG = GameplayScreen.class.getName();

    private Level level;
    private SpriteBatch spriteBatch;
    private ExtendViewport gameplayViewport;
    private ChaseCam chaseCam;

    @Override
    public void show() {
        AssetManager am = new AssetManager();
        Assets.instance.init(am);

        spriteBatch = new SpriteBatch();
        gameplayViewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);

//        level = new Level(gameplayViewport);
//        level.initializeDebugLevel();

        level = LevelLoader.load("Level1", gameplayViewport);
        chaseCam = new ChaseCam(gameplayViewport.getCamera(), level.getGigaGal());
    }

    @Override
    public void resize(int width, int height) {
        gameplayViewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        Assets.instance.dispose();
        spriteBatch.dispose();
    }

    @Override
    public void render(float delta) {
        level.update(delta);
        chaseCam.update(delta);

        gameplayViewport.apply();

        Color bgColor = Constants.BACKGROUND_COLOR;
        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.setProjectionMatrix(gameplayViewport.getCamera().combined);
        spriteBatch.begin();
        level.render(spriteBatch);
        spriteBatch.end();
    }
}
