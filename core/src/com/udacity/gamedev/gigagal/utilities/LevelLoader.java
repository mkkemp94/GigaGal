package com.udacity.gamedev.gigagal.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.gamedev.gigagal.Level;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;

/**
 * Created by mkemp on 3/22/18.
 */

public class LevelLoader {

    public static final String TAG = LevelLoader.class.toString();

    public static Level load(String levelName, Viewport viewport) {

        Gdx.app.log(TAG, "Loading level...");

        Level level = new Level(viewport);

        // TODO: Construct the path to the level file
        // Use the LEVEL_DIR constant, File.separator, the level name, and LEVEL_FILE_EXTENSION
        String path = Constants.LEVEL_DIR + File.separator + levelName + "." + Constants.LEVEL_FILE_EXTENSION;

        try {

            // TODO: Get the level FileHandle object using Gdx.files.internal
            FileHandle fileHandle = Gdx.files.internal(path);

            // TODO: Create a new JSONParser
            JSONParser jsonParser = new JSONParser();

            // TODO: get the root JSONObject by parsing the level file
            // Use file.reader() to pass a file reader into parse() on the parser, then cast the result to a JSONObject
            JSONObject rootJsonObject = (JSONObject) jsonParser.parse(fileHandle.reader());

            // TODO: Log rootJsonObject.keySet().toString() to see the keys available in this JSONObject
            Gdx.app.log(TAG, "All Keys: " + rootJsonObject.keySet().toString());

            // TODO: Get the 'composite' object within the rootJsonObject
            JSONObject compositeObject = (JSONObject) rootJsonObject.get(Constants.LEVEL_COMPOSITE);

            // TODO: Log the keys available in the composite object
            Gdx.app.log(TAG, "Keys in composite object: " + compositeObject.keySet().toString());

            // TODO: Get the JSONArray behind the LEVEL_9PATCHES key
            JSONArray ninePatches = (JSONArray) compositeObject.get(Constants.LEVEL_9PATCHES);

            // TODO: Get the first platform in the array
            JSONObject firstPlatform = (JSONObject) ninePatches.get(0);

            // TODO: Log the keys available in the platform object
            Gdx.app.log(TAG, "Keys in platform object: " + firstPlatform.keySet().toString());


        } catch (Exception ex) {

            // TODO: If there's an error, log the message using ex.getMessage()
            // Be sure to log the error at the appropriate log level
            Gdx.app.error(TAG, ex.getMessage());

            // TODO: Then log the error message defined in LEVEL_ERROR_MESSAGE
            Gdx.app.error(TAG, Constants.LEVEL_ERROR_MESSAGE);
        }

        return level;
    }
}
