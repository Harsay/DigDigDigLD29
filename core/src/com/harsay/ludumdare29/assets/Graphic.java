package com.harsay.ludumdare29.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Graphic {
	
	public static Sprite tile;
	public static Sprite player;
	public static Sprite darkerTile;
	public static Sprite lava;
	public static Sprite lavaRock;
	
	public void init() {
		tile = new Sprite(new Texture("tile.jpg"));
		darkerTile = new Sprite(new Texture("darkerTile.jpg"));
		player = new Sprite(new Texture("player.jpg"));
		lava = new Sprite(new Texture("lava.jpg"));
		lavaRock = new Sprite(new Texture("lavaRock.jpg"));
		
		tile.flip(false, true);
		darkerTile.flip(false, true);
		player.flip(false, true);
		lava.flip(false, true);
		lavaRock.flip(false, true);
	}

}
