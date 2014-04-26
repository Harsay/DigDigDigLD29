package com.harsay.ludumdare29.other;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.harsay.ludumdare29.MyGame;

public class Controller extends InputAdapter {
	
	public static int mouseTileX;
	public static int mouseTileY;
	public static float mouseX;
	public static float mouseY;

	public boolean mouseMoved(int screenX, int screenY) {
		mouseX = ( (float) MyGame.WIDTH / (float) Gdx.graphics.getWidth() )*screenX;
		mouseY = ( (float) MyGame.HEIGHT / (float) Gdx.graphics.getHeight() )*screenY;
		mouseTileX = (int) Math.floor(mouseX/MyGame.UNIT);
		mouseTileY = (int) Math.floor(mouseY/MyGame.UNIT);
		System.out.println(mouseTileY);
		return false;
	}

}
