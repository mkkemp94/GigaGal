package com.udacity.gamedev.gigagal.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.udacity.gamedev.gigagal.entities.GigaGal;

/**
 * Created by mkemp on 3/12/18.
 */

public class ChaseCam {

    public static final String TAG = ChaseCam.class.getName();

    public Camera camera;
    public GigaGal target;
    private Boolean following;

    public ChaseCam() {
        following = true;
    }

    public void update(float delta) {

        // Switch camera mode
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            following = ! following;
        }

        // Normal camera
        if (following) {
            camera.position.x = target.getPosition().x;
            camera.position.y = target.getPosition().y;
        }

        // Debug camera
        else {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                camera.position.x -= delta * Constants.CHASE_CAM_MOVE_SPEED;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                camera.position.x += delta * Constants.CHASE_CAM_MOVE_SPEED;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                camera.position.y += delta * Constants.CHASE_CAM_MOVE_SPEED;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                camera.position.y -= delta * Constants.CHASE_CAM_MOVE_SPEED;
            }
        }
    }
}
