package com.harsay.ludumdare29.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.harsay.ludumdare29.MyGame;
import com.harsay.ludumdare29.assets.Graphic;
import com.harsay.ludumdare29.other.Controller;

public class Level {
	
	public static enum Tile {
		NOTHING, ROCK
	}
	
	public List<ArrayList<Tile>> map = new ArrayList<ArrayList<Tile>>();
	
	public Level() {
		
	}
	
	public void generate() {
		for(int x=0; x<=30; x++) {
			ArrayList<Tile> list = new ArrayList<Tile>();
			for(int y=0; y<=32; y++) {
				if(y > 10) 
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
				Graphic.tile.setPosition(x*MyGame.UNIT, y*MyGame.UNIT);
				if(t.equals(Tile.ROCK)) {
					Graphic.tile.draw(sb);
				}
				// TODO: moar tiles
			}
		}
	}
	
	public void checkTile() {
		int tileX = Controller.mouseTileX;
		int tileY = Controller.mouseTileY;
		
		if(/*Player.reachableTiles.contains(new Vector2(tileX, tileY))*/ getTile(tileX, tileY).equals(Tile.ROCK)) {
			Tile toRemove = map.get(tileX).get(tileY);
			if(!toRemove.equals(Tile.NOTHING)) map.get(tileX).set(tileY, Tile.NOTHING);
		} else if(getTile(tileX, tileY).equals(Tile.NOTHING) && getTile(tileX, tileY-1).equals(Tile.NOTHING)) {
			if(Player.tileY < tileY) {
				if(Player.tileX > tileX) Player.walkLeft = true;
				else Player.walkRight = true;
				Player.reachTileX = tileX;
				Player.isWalking = true;
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		return map.get(x).get(y);
	}
	
	public int getWidth() {
		return map.size()*MyGame.UNIT;
	}
	
	public int getHeight() {
		return map.get(0).size()*MyGame.UNIT;
	}

}
