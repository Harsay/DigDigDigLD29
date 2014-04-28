package com.harsay.ludumdare29.other;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class Controller extends InputAdapter {
	
	public static boolean isLeftPressed = false;
	public static boolean isRightPressed = false;
	public static boolean isDownPressed = false;
	public static boolean isUpPressed = false;
	public static boolean isEnterPressed = false;
	
	
	public void checkInput() {
		isLeftPressed = Gdx.input.isKeyPressed(Keys.LEFT);
		isRightPressed = Gdx.input.isKeyPressed(Keys.RIGHT);
		isDownPressed = Gdx.input.isKeyPressed(Keys.DOWN);
		isUpPressed = Gdx.input.isKeyPressed(Keys.UP);
		isEnterPressed = Gdx.input.isKeyPressed(Keys.ENTER);
	}
	
	public boolean keyDown(int keycode) {
		checkInput();
		return false;
	}
	
	public boolean keyUp(int keycode) {
		checkInput();
		return false;
	}
	
}
