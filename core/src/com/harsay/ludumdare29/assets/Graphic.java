package com.harsay.ludumdare29.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Graphic {
	
	public static Sprite tile;
	public static Sprite player;
	public static Sprite darkerTile;
	public static Sprite lava;
	public static Sprite lavaRock;
	public static Sprite ind;
	
	public static BitmapFont font;
	public static BitmapFont worldFont;
	
	public void init() {
		tile = new Sprite(new Texture("tile.jpg"));
		darkerTile = new Sprite(new Texture("darkerTile.jpg"));
		player = new Sprite(new Texture("player.jpg"));
		lava = new Sprite(new Texture("lava.jpg"));
		lavaRock = new Sprite(new Texture("lavaRock.jpg"));
		ind = new Sprite(new Texture("ind.jpg"));
		
		tile.flip(false, true);
		darkerTile.flip(false, true);
		player.flip(false, true);
		lava.flip(false, true);
		lavaRock.flip(false, true);
		ind.flip(false, true);
		
		font = new BitmapFont(Gdx.files.internal("font.fnt"));
		font.setColor(1, 1, 1, 1);
		font.setScale(0.5f, 0.5f);
		
		worldFont = new BitmapFont(Gdx.files.internal("font.fnt"));
		worldFont.setColor(1, 1, 1, 1);
		worldFont.setScale(2, -2);
	}

}
