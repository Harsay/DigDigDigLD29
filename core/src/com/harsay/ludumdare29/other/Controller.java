package com.harsay.ludumdare29.other;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class Controller implements InputProcessor {
	
	public static boolean isLeftPressed = false;
	
	public void checkInput() {
		isLeftPressed = Gdx.input.isKeyPressed(Keys.LEFT);	
	}

	@Override
	public boolean keyDown(int keycode) {
		checkInput();
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		checkInput();
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
