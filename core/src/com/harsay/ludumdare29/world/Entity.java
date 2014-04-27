package com.harsay.ludumdare29.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.harsay.ludumdare29.MyGame;

public class Entity {
	
	public static Vector3 position;
	
	public int width;
	public int height;
	public static int tileX;
	public static int tileY;
	
	public Vector2 velocity = new Vector2(0,0);
	public float maxVelocity;
	public float speed = 10;
	
	public Entity(float x, float y, int width, int height) {
		position = new Vector3(x, y, 0);
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
