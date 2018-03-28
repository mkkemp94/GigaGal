package com.udacity.gamedev.gigagal.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.gamedev.gigagal.entities.Enemy;
import com.udacity.gamedev.gigagal.entities.Platform;
import com.udacity.gamedev.gigagal.utilities.Constants;
import com.udacity.gamedev.gigagal.utilities.Utils;

/**
 * Created by mkemp on 3/27/18.
 */

public class GameOverOverlay {

    public final static String TAG = GameOverOverlay.class.getName();
    public Viewport viewport;
    BitmapFont font;
    Array<Enemy> enemies;
    long startTime;

    public GameOverOverlay() {
        this.viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        font = new BitmapFont(Gdx.files.internal(Constants.FONT_FILE));
        font.getData().setScale(1);
    }

    public void init() {
        startTime = TimeUtils.nanoTime();

        enemies = new Array<Enemy>(Constants.ENEMY_COUNT);

        for (int i = 0; i < Constants.ENEMY_COUNT; i++) {

            Platform platform = new Platform(
                    MathUtils.random(viewport.getWorldWidth()),
                    MathUtils.random(-Constants.ENEMY_CENTER.y, viewport.getWorldHeight()),
                    0, 0
            );
            Enemy enemy = new Enemy(platform);
            enemies.add(enemy);
        }
    }

    public void render(SpriteBatch batch) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        float timeElapsed = Utils.secondsSince(startTime);
        int enemiesToShow = (int) (Constants.ENEMY_COUNT * (timeElapsed / Constants.LEVEL_END_DURATION));

        for (int i = 0; i < enemiesToShow; i++) {
            Enemy enemy = enemies.get(i);
            enemy.update(0);
            enemy.render(batch);
        }

        // Draw text
        String victory = Constants.GAME_OVER_MESSAGE;
        font.draw(batch, victory,
                viewport.getWorldWidth() / 2,
                viewport.getWorldHeight()/ 2.5f, 0, Align.center, false
        );

        batch.end();
    }
}
