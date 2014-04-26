package com.harsay.ludumdare29.other;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.harsay.ludumdare29.MyGame;
import com.harsay.ludumdare29.world.World;

public class Controller extends InputAdapter {
	
	public static int mouseTileX;
	public static int mouseTileY;
	
	public static int mouseWorldTileX;
	public static int mouseWorldTileY;
	
	public static Vector3 mousePosition = new Vector3(0,0,0);
	public static Vector3 mouseWorldPosition = new Vector3(0,0,0);
	
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		calculateCoordinates(screenX, screenY);
		if(button == Input.Buttons.LEFT) {
			World.level.removeTile();
			return true;
		} else {
			World.cam.position.y += MyGame.UNIT;
		}
		return false;
	}

	public boolean mouseMoved(int screenX, int screenY) {
		calculateCoordinates(screenX, screenY);
		return false;
	}
	
	public void calculateCoordinates(int screenX, int screenY) {
		mousePosition.x = screenX;
		mousePosition.y = screenY;

		World.cam.unproject(mousePosition);
		
		mouseTileX = (int) Math.floor(mousePosition.x/MyGame.UNIT);
		mouseTileY = (int) Math.floor(mousePosition.y/MyGame.UNIT);
		
	}
	
	public static void drawCursor(ShapeRenderer sr) {
		sr.rect(Controller.mouseTileX*MyGame.UNIT, Controller.mouseTileY*MyGame.UNIT, MyGame.UNIT, MyGame.UNIT);
	}
}
