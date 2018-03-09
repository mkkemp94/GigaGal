package com.udacity.gamedev.gigagal.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
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
    public PlatformAssets platformAssets;
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
        platformAssets = new PlatformAssets(atlas);
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
        public final AtlasRegion jumpingLeft;
        public final AtlasRegion jumpingRight;
        public final AtlasRegion walkingRight;
        public final AtlasRegion walkingLeft;

        public final Animation<AtlasRegion> walkLeftAnimation;
        public final Animation<AtlasRegion> walkRightAnimation;

        public GigaGalAssets(TextureAtlas atlas) {
            standingRight = atlas.findRegion(Constants.STANDING_RIGHT);
            standingLeft = atlas.findRegion(Constants.STANDING_LEFT);
            jumpingLeft = atlas.findRegion(Constants.JUMPING_LEFT);
            jumpingRight = atlas.findRegion(Constants.JUMPING_RIGHT);
            walkingRight = atlas.findRegion(Constants.WALKING_RIGHT_2);
            walkingLeft = atlas.findRegion(Constants.WALKING_LEFT_2);

            Array<AtlasRegion> walkingLeftFrames = new Array<AtlasRegion>();
            walkingLeftFrames.add(atlas.findRegion(Constants.WALKING_LEFT_2));
            walkingLeftFrames.add(atlas.findRegion(Constants.WALKING_LEFT_1));
            walkingLeftFrames.add(atlas.findRegion(Constants.WALKING_LEFT_2));
            walkingLeftFrames.add(atlas.findRegion(Constants.WALKING_LEFT_3));
            walkLeftAnimation = new Animation<AtlasRegion>(Constants.WALK_LOOP_FRAME_DURATION, walkingLeftFrames, Animation.PlayMode.LOOP);
            
            Array<AtlasRegion> walkingRightFrames = new Array<AtlasRegion>();
            walkingRightFrames.add(atlas.findRegion(Constants.WALKING_RIGHT_2));
            walkingRightFrames.add(atlas.findRegion(Constants.WALKING_RIGHT_1));
            walkingRightFrames.add(atlas.findRegion(Constants.WALKING_RIGHT_2));
            walkingRightFrames.add(atlas.findRegion(Constants.WALKING_RIGHT_3));
            walkRightAnimation = new Animation<AtlasRegion>(Constants.WALK_LOOP_FRAME_DURATION, walkingRightFrames, Animation.PlayMode.LOOP);
        }
    }

    public class PlatformAssets {

        public final NinePatch ninePatch;

        public PlatformAssets(TextureAtlas atlas) {
            AtlasRegion platform = atlas.findRegion(Constants.PLATFORM);
            ninePatch = new NinePatch(platform,
                    Constants.PLATFORM_EDGE,
                    Constants.PLATFORM_EDGE,
                    Constants.PLATFORM_EDGE,
                    Constants.PLATFORM_EDGE
            );
        }
    }
}
