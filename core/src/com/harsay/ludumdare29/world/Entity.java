package com.harsay.ludumdare29.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Entity {
	
	public Vector2 position;
	public int width;
	public int height;
	
	public Entity(float x, float y, int width, int height) {
		position = new Vector2(x, y);
		this.width = width;
		this.height = height;
	}
	
	public void update(float delta) {
		
	}
	
	public void render(SpriteBatch sb) {
		
	}

}
