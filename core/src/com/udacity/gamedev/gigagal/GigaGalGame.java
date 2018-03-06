package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.Game;

/**
 * Application listener.
 * Delegate callbacks to a new GameplayScreen
 */
public class GigaGalGame extends Game {
	
	@Override
	public void create () {
		setScreen(new GameplayScreen());
	}
}
