package com.harsay.ludumdare29.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Graphic {
	
	public static Sprite tile;
	public static Sprite player;
	
	public void init() {
		tile = new Sprite(new Texture("tile.jpg"));
		player = new Sprite(new Texture("player.jpg"));
		
		tile.flip(false, true);
		player.flip(false, true);
	}

}
