package com.udacity.gamedev.gigagal.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by mkemp on 3/6/18.
 * A singleton that loads and holds on to the texture atlas with GigaGal's assets.
 */

public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();

    // Singleton
    public static final Assets instance = new Assets();

    public GigaGalAssets gigaGalAssets;
    private AssetManager assetManager;

    private Assets() {
        // This is private.
    }

    /**
     * Initialize this class.
     * Pass the texture atlas to a new GigaGalAssets object.
     */
    public void init() {
        this.assetManager = new AssetManager();
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);
        gigaGalAssets = new GigaGalAssets(atlas);
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.log(TAG, "Couldn't load asset: " + asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    public class GigaGalAssets {

        public final AtlasRegion standingRight;
        public final AtlasRegion standingLeft;

        private GigaGalAssets(TextureAtlas atlas) {
            standingRight = atlas.findRegion(Constants.SPRITE_NAME_STANDING_RIGHT);
            standingLeft = atlas.findRegion(Constants.SPRITE_NAME_STANDING_LEFT);
        }
    }
}
