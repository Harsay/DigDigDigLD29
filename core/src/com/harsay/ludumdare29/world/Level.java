package com.harsay.ludumdare29.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level {
	
	public static enum Tile {
		NOTHING, ROCK
	}
	
	public List<ArrayList<Tile>> map = new ArrayList<ArrayList<Tile>>();
	
	//tile test
	public static Texture texture = new Texture("tile.jpg");
	public static Sprite sprite = new Sprite(texture);
	
	public Level() {
		
	}
	
	public void generate() {
		for(int x=0; x<=30; x++) {
			ArrayList<Tile> list = new ArrayList<Tile>();
			for(int y=0; y<=16; y++) {
				if(y > 3) 
					list.add(Tile.ROCK);
				else
					list.add(Tile.NOTHING);
			}
			map.add(list);
		}
		Collections.reverse(map);
	}
	
	public void render(SpriteBatch sb) {
		for(int x=0; x<map.size(); x++) {
			for(int y=0; y<map.get(x).size(); y++) {
				Tile t = map.get(x).get(y);
				sprite.setPosition(x*32, y*32);
				if(t.equals(Tile.ROCK)) {
					sprite.draw(sb);
				}
				// TODO: moar tiles
			}
		}
	}

}
