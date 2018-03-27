package com.udacity.gamedev.gigagal.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.udacity.gamedev.gigagal.Level;
import com.udacity.gamedev.gigagal.entities.Enemy;
import com.udacity.gamedev.gigagal.entities.ExitPortal;
import com.udacity.gamedev.gigagal.entities.GigaGal;
import com.udacity.gamedev.gigagal.entities.Platform;
import com.udacity.gamedev.gigagal.entities.Powerup;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.util.Comparator;

/**
 * Created by mkemp on 3/22/18.
 */

public class LevelLoader {

    public static final String TAG = LevelLoader.class.toString();

    public static Level load(String levelName) {

        String path = Constants.LEVEL_DIR + File.separator + levelName + "." + Constants.LEVEL_FILE_EXTENSION;
        Level level = new Level();

        FileHandle fileHandle = Gdx.files.internal(path);
        JSONParser parser = new JSONParser();

        try {

            JSONObject rootJsonObject = (JSONObject) parser.parse(fileHandle.reader());

            JSONObject composite = (JSONObject) rootJsonObject.get(Constants.LEVEL_COMPOSITE);

            JSONArray platforms = (JSONArray) composite.get(Constants.LEVEL_9PATCHES);
            loadPlatforms(platforms, level);

            JSONArray otherObjects = (JSONArray) composite.get(Constants.LEVEL_IMAGES);
            loadNonPlatformEntities(otherObjects, level);

        } catch (Exception ex) {
            Gdx.app.error(TAG, ex.getMessage());
            Gdx.app.error(TAG, Constants.LEVEL_ERROR_MESSAGE);
        }

        return level;
    }

    private static float safeGetFloat(JSONObject object, String key) {
        Number number = (Number) object.get(key);
        return (number == null) ? 0 : number.floatValue();
    }

    private static void loadPlatforms(JSONArray array, Level level) {

        Array<Platform> platformArray = new Array<Platform>();

        for (Object object : array) {
            final JSONObject platformObject = (JSONObject) object;

            final float x = safeGetFloat(platformObject, Constants.LEVEL_X_KEY);
            final float y = safeGetFloat(platformObject, Constants.LEVEL_Y_KEY);

            final float width = ((Number) platformObject.get(Constants.LEVEL_WIDTH_KEY)).floatValue();
            final float height = ((Number) platformObject.get(Constants.LEVEL_HEIGHT_KEY)).floatValue();

//            Gdx.app.log(TAG, "Location: " + x + ", " + y);
//            Gdx.app.log(TAG, "Dimensions: " + width + " x " + height);

            Platform platform = new Platform(x, y + height, width, height);
            platformArray.add(platform);

            final String identifier = (String) platformObject.get(Constants.LEVEL_IDENTIFIER_KEY);

            if (identifier != null && identifier.equals(Constants.LEVEL_ENEMY_TAG)) {
                Gdx.app.log(TAG, "Loaded an enemy on that platform");
                final Enemy enemy = new Enemy(platform);
                level.getEnemies().add(enemy);
            }
        }

        platformArray.sort(new Comparator<Platform>() {
            @Override
            public int compare(Platform o1, Platform o2) {
                if (o1.top < o2.top) {
                    return 1;
                } else if (o1.top > o2.top) {
                    return -1;
                }
                return 0;
            }
        });

        level.getPlatforms().addAll(platformArray);
    }

    private static void loadNonPlatformEntities(JSONArray array, Level level) {

        for (Object object : array) {
            JSONObject jsonObject = (JSONObject) object;

            Vector2 lowerLeftCorner = new Vector2();
            lowerLeftCorner.x = safeGetFloat(jsonObject, Constants.LEVEL_X_KEY);
            lowerLeftCorner.y = safeGetFloat(jsonObject, Constants.LEVEL_Y_KEY);

            if (jsonObject.get(Constants.LEVEL_IMAGENAME_KEY).equals(Constants.STANDING_RIGHT)) {

                Vector2 gigaGalPosition = lowerLeftCorner.add(Constants.GIGAGAL_EYE_POSITION);
                Gdx.app.log(TAG, "Loaded GigaGal at" + gigaGalPosition);
                level.setGigaGal(new GigaGal(gigaGalPosition, level));
            }

            else if (jsonObject.get(Constants.LEVEL_IMAGENAME_KEY).equals(Constants.EXIT_PORTAL_1)) {

                Vector2 exitPortalPosition = lowerLeftCorner.add(Constants.EXIT_PORTAL_CENTER);
                level.setExitPortal(new ExitPortal(exitPortalPosition));
            }

            else if (jsonObject.get(Constants.LEVEL_IMAGENAME_KEY).equals(Constants.POWERUP)) {

                Vector2 powerupPosition = lowerLeftCorner.add(Constants.POWERUP_CENTER);
                level.getPowerups().add(new Powerup(powerupPosition));
            }
        }

    }
}
