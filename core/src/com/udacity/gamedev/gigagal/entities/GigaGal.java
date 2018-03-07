package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
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
    private Vector2 velocity;

    private Facing facing;
    private JumpState jumpState;
    private WalkState walkState;

    private long jumpStartTime;

    public GigaGal() {
        position = new Vector2(20, Constants.GIGAGAL_EYE_HEIGHT);
        velocity = new Vector2(0, 0);

        facing = Facing.RIGHT;
        jumpState = JumpState.FALLING;
        walkState = WalkState.STANDING;
    }

    public void update(float delta) {
        velocity.y -= delta * Constants.GRAVITY;
        position.mulAdd(velocity, delta);

        if (jumpState != JumpState.JUMPING) {
            jumpState = JumpState.FALLING;
        }

        // But if she's on the ground, stop there.
        if (position.y - Constants.GIGAGAL_EYE_HEIGHT <= 0) {
            velocity.y = 0;
            jumpState = JumpState.GROUNDED;
            position.y = Constants.GIGAGAL_EYE_HEIGHT;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            switch (jumpState) {
                case GROUNDED:
                    startJump();
                    break;
                case JUMPING:
                    continueJump();
                    break;
                case FALLING:
                    break;
            }
        } else {
            endJump();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveLeft(delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveRight(delta);
        } else {
            walkState = WalkState.STANDING;
        }
    }

    private void moveLeft(float delta) {
        walkState = WalkState.WALKING;
        facing = Facing.LEFT;
        position.x -= delta * Constants.GIGAGAL_MOVEMENT_SPEED;
    }

    private void moveRight(float delta) {
        walkState = WalkState.WALKING;
        facing = Facing.RIGHT;
        position.x += delta * Constants.GIGAGAL_MOVEMENT_SPEED;
    }

    private void startJump() {
        jumpState = JumpState.JUMPING;
        jumpStartTime = TimeUtils.nanoTime();
        continueJump();
    }

    private void continueJump() {
        if (jumpState == JumpState.JUMPING) {
            float jumpDuration = MathUtils.nanoToSec * (TimeUtils.nanoTime() - jumpStartTime);

            if (jumpDuration < Constants.GIGAGAL_JUMP_MAX_DURATION) {
                velocity.y = Constants.GIGAGAL_JUMP_SPEED;
            } else {
                endJump();
            }
        }
    }

    private void endJump() {
        if (jumpState == JumpState.JUMPING) {
            jumpState = JumpState.FALLING;
        }
    }

    public void render(SpriteBatch batch) {

        // Get the standing right atlas region
        TextureAtlas.AtlasRegion region = Assets.instance.gigaGalAssets.standingRight;

        if (facing == Facing.RIGHT && jumpState != JumpState.GROUNDED) {
            region = Assets.instance.gigaGalAssets.jumpingRight;
        } else if (facing == Facing.RIGHT && walkState == WalkState.STANDING) {
            region = Assets.instance.gigaGalAssets.standingRight;
        } else if (facing == Facing.RIGHT && walkState == WalkState.WALKING) {
            region = Assets.instance.gigaGalAssets.walkingRight;
        } else if (facing == Facing.LEFT && jumpState != JumpState.GROUNDED) {
            region = Assets.instance.gigaGalAssets.jumpingLeft;
        } else if (facing == Facing.LEFT && walkState == WalkState.STANDING) {
            region = Assets.instance.gigaGalAssets.standingLeft;
        } else if (facing == Facing.LEFT && walkState == WalkState.WALKING) {
            region = Assets.instance.gigaGalAssets.walkingLeft;
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

    public enum WalkState {
        STANDING, WALKING
    }
}
