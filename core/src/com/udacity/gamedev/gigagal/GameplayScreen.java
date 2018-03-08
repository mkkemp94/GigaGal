package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

    private Level level;
    private SpriteBatch spriteBatch;
    private ShapeRenderer renderer;
    private ExtendViewport viewport;

    @Override
    public void show() {
        Assets.instance.init();

        level = new Level();

        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
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
        level.update(delta);

        viewport.apply();

        Color bgColor = Constants.BACKGROUND_COLOR;
        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        level.render(spriteBatch, renderer);
    }
}
