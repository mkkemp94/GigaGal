package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.udacity.gamedev.gigagal.Level;
import com.udacity.gamedev.gigagal.utilities.Assets;
import com.udacity.gamedev.gigagal.utilities.Constants;
import com.udacity.gamedev.gigagal.utilities.Enums.Direction;
import com.udacity.gamedev.gigagal.utilities.Enums.JumpState;
import com.udacity.gamedev.gigagal.utilities.Enums.WalkState;
import com.udacity.gamedev.gigagal.utilities.Utils;

/**
 * Created by mkemp on 3/6/18.
 * Controls and draws GigaGal.
 * Keeps track of position, velocity, direction, etc.
 * Also handles user input.
 */

public class GigaGal {

    public static final String TAG = GigaGal.class.getName();

    Vector2 position;

    Vector2 spawnLocation;

    Vector2 velocity;
    Vector2 lastFramePosition;

    Direction direction;
    JumpState jumpState;
    WalkState walkState;

    long jumpStartTime;
    long walkStartTime;

    Level level;

    public GigaGal(Vector2 spawnLocation, Level level) {
        this.spawnLocation = spawnLocation;
        this.level = level;
        position = new Vector2();
        lastFramePosition = new Vector2();
        velocity = new Vector2();
        init();
    }

    private void init() {
        position.set(spawnLocation);
        lastFramePosition.set(spawnLocation);
        velocity.setZero();

        direction = Direction.RIGHT;
        jumpState = JumpState.FALLING;
        walkState = WalkState.NOT_WALKING;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void update(float delta, Array<Platform> platforms) {
        lastFramePosition.set(position);
        velocity.y -= Constants.GRAVITY;
        position.mulAdd(velocity, delta);

        if (position.y < Constants.KILL_PLANE) {
            init();
        }

        if (jumpState != JumpState.JUMPING) {
            jumpState = JumpState.FALLING;

            // If she's on a platform, stop there.
            for (Platform platform : platforms) {
                if (landedOnPlatform(platform)) {
                    jumpState = JumpState.GROUNDED;
                    velocity.y = 0;
                    position.y = platform.top + Constants.GIGAGAL_EYE_HEIGHT;
                }
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveLeft(delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveRight(delta);
        } else {
            walkState = WalkState.NOT_WALKING;
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
    }

    private boolean landedOnPlatform(Platform platform) {
        boolean leftFootIn = false;
        boolean rightFootIn = false;
        boolean straddle = false;

        if (lastFramePosition.y - Constants.GIGAGAL_EYE_HEIGHT >= platform.top &&
                position.y - Constants.GIGAGAL_EYE_HEIGHT < platform.top) {

            float leftFoot = position.x - Constants.GIGAGAL_STANCE_WIDTH / 2;
            float rightFoot = position.x + Constants.GIGAGAL_STANCE_WIDTH / 2;

            leftFootIn = (leftFoot > platform.left && leftFoot < platform.right);
            rightFootIn = (rightFoot > platform.left && rightFoot < platform.right);
            straddle = (leftFoot < platform.left & rightFoot > platform.right);
        }
        return leftFootIn || rightFootIn || straddle;
    }

    private void moveLeft(float delta) {
        if (jumpState == JumpState.GROUNDED && walkState != WalkState.WALKING) {
            walkStartTime = TimeUtils.nanoTime();
        }
        walkState = WalkState.WALKING;
        direction = Direction.LEFT;
        position.x -= delta * Constants.GIGAGAL_MOVEMENT_SPEED;
    }

    private void moveRight(float delta) {
        if (jumpState == JumpState.GROUNDED && walkState != WalkState.WALKING) {
            walkStartTime = TimeUtils.nanoTime();
        }
        walkState = WalkState.WALKING;
        direction = Direction.RIGHT;
        position.x += delta * Constants.GIGAGAL_MOVEMENT_SPEED;
    }

    private void startJump() {
        jumpState = JumpState.JUMPING;
        jumpStartTime = TimeUtils.nanoTime();
        continueJump();
    }

    private void continueJump() {
        if (jumpState == JumpState.JUMPING) {
            if (Utils.secondsSince(jumpStartTime) < Constants.GIGAGAL_JUMP_MAX_DURATION) {
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

        if (direction == Direction.RIGHT && jumpState != JumpState.GROUNDED) {
            region = Assets.instance.gigaGalAssets.jumpingRight;
        } else if (direction == Direction.RIGHT && walkState == WalkState.NOT_WALKING) {
            region = Assets.instance.gigaGalAssets.standingRight;
        } else if (direction == Direction.RIGHT && walkState == WalkState.WALKING) {
            float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartTime);
            region = Assets.instance.gigaGalAssets.walkRightAnimation.getKeyFrame(walkTimeSeconds);
        } else if (direction == Direction.LEFT && jumpState != JumpState.GROUNDED) {
            region = Assets.instance.gigaGalAssets.jumpingLeft;
        } else if (direction == Direction.LEFT && walkState == WalkState.NOT_WALKING) {
            region = Assets.instance.gigaGalAssets.standingLeft;
        } else if (direction == Direction.LEFT && walkState == WalkState.WALKING) {
            float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartTime);
            region = Assets.instance.gigaGalAssets.walkLeftAnimation.getKeyFrame(walkTimeSeconds);
        }

        Utils.drawTextureRegion(batch, region,
                position.x - Constants.GIGAGAL_EYE_POSITION.x,
                position.y - Constants.GIGAGAL_EYE_POSITION.y);
    }
}
