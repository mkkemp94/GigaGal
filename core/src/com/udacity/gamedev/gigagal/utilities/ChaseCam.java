package com.udacity.gamedev.gigagal.utilities;

import com.badlogic.gdx.graphics.Camera;
import com.udacity.gamedev.gigagal.entities.GigaGal;

/**
 * Created by mkemp on 3/12/18.
 */

public class ChaseCam {

    public static final String TAG = ChaseCam.class.getName();

    private Camera camera;
    private GigaGal gigaGal;

    public ChaseCam(Camera camera, GigaGal gigaGal) {
        this.camera = camera;
        this.gigaGal = gigaGal;
    }

    public void update() {
//        camera.position.set(new Vector3(gigaGal.position.x, gigaGal.position.y, 0));
        camera.position.x = gigaGal.position.x;
        camera.position.y = gigaGal.position.y;
    }
}
