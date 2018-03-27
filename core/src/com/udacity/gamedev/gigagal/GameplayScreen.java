package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.udacity.gamedev.gigagal.overlays.GigaGalHud;
import com.udacity.gamedev.gigagal.utilities.Assets;
import com.udacity.gamedev.gigagal.utilities.ChaseCam;
import com.udacity.gamedev.gigagal.utilities.Constants;

/**
 * Created by mkemp on 3/6/18.
 * Set up drawing environment
 * Set up gameplayViewport
 * Initialize assets class
 */
public class GameplayScreen extends ScreenAdapter {

    public static final String TAG = GameplayScreen.class.getName();

    private Level level;
    private SpriteBatch batch;
    private ChaseCam chaseCam;
    private GigaGalHud hud;

    @Override
    public void show() {
        AssetManager am = new AssetManager();
        Assets.instance.init(am);

        batch = new SpriteBatch();
        chaseCam = new ChaseCam();
        hud = new GigaGalHud();

        startNewLevel();
    }

    @Override
    public void resize(int width, int height) {
        hud.viewport.update(width, height, true);
        level.viewport.update(width, height, true);
        chaseCam.camera = level.viewport.getCamera();
    }

    @Override
    public void dispose() {
        Assets.instance.dispose();
        batch.dispose();
    }

    @Override
    public void render(float delta) {
        level.update(delta);
        chaseCam.update(delta);

        Color bgColor = Constants.BACKGROUND_COLOR;
        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        level.render(batch);
        hud.render(batch);
    }

    private void startNewLevel() {

        level = Level.debugLevel();

//        String levelName = Constants.LEVELS[MathUtils.random(Constants.LEVELS.length - 1)];
//        level = LevelLoader.load(levelName);

        chaseCam.camera = level.viewport.getCamera();
        chaseCam.target = level.getGigaGal();
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
