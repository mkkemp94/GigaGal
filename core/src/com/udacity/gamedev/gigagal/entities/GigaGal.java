package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.gigagal.utilities.Assets;
import com.udacity.gamedev.gigagal.utilities.Constants;

/**
 * Created by mkemp on 3/6/18.
 * Controls and draws GigaGal.
 * Keeps track of position, velocity, direction, etc.
 * Also handles user input.
 */

public class GigaGal {

    public static final String TAG = GigaGal.class.getName();

    private Vector2 position;
    private Facing facing;
    private JumpState jumpState;

    public GigaGal() {
        position = new Vector2(20, Constants.GIGAGAL_EYE_HEIGHT);
        facing = Facing.RIGHT;
        jumpState = JumpState.GROUNDED;
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveLeft(delta);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveRight(delta);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            jump(delta);
        }

        if (jumpState == JumpState.JUMPING) {
            position.y += delta * Constants.GIGAGAL_JUMP_HEIGHT;

            if (position.y >= Constants.GIGAGAL_JUMP_HEIGHT) {
                jumpState = JumpState.FALLING;
            }
        }

        if (jumpState == JumpState.FALLING) {
            position.y -= delta * Constants.GRAVITATIONAL_ACCELERATION;

            if (position.y <= Constants.GIGAGAL_EYE_HEIGHT) {
                position.y = Constants.GIGAGAL_EYE_HEIGHT;
                jumpState = JumpState.GROUNDED;
            }
        }
    }

    private void moveLeft(float delta) {
        facing = Facing.LEFT;
        position.x -= delta * Constants.GIGAGAL_MOVEMENT_SPEED;
    }

    private void moveRight(float delta) {
        facing = Facing.RIGHT;
        position.x += delta * Constants.GIGAGAL_MOVEMENT_SPEED;
    }

    private void jump(float delta) {
        if (jumpState == JumpState.GROUNDED) {
            jumpState = JumpState.JUMPING;
        }
    }

    public void render(SpriteBatch batch) {

        // Get the standing right atlas region
        TextureAtlas.AtlasRegion region = Assets.instance.gigaGalAssets.standingRight;
        if (facing == Facing.LEFT) {
            region = Assets.instance.gigaGalAssets.standingLeft;
        }

        batch.draw(region.getTexture(),
                position.x - Constants.GIGAGAL_EYE_POSITION.x,
                position.y - Constants.GIGAGAL_EYE_POSITION.y,
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
    }

    public enum Facing {
        RIGHT, LEFT
    }

    public enum JumpState {
        JUMPING, FALLING, GROUNDED
    }
}
