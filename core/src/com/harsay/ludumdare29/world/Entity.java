package com.harsay.ludumdare29.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.harsay.ludumdare29.MyGame;

public class Entity {
	
	public Vector2 position;
	public int width;
	public int height;
	public int tileX;
	public int tileY;
	
	public Entity(float x, float y, int width, int height) {
		position = new Vector2(x, y);
		this.width = width;
		this.height = height;
	}
	
	public void update(float delta) {
		tileX = (int) Math.floor(position.x/MyGame.UNIT);
		tileY = (int) Math.floor(position.y/MyGame.UNIT);
		//System.out.println(tileX+","+tileY);
		
	}
	
	public void render(SpriteBatch sb) {
		
	}

}
