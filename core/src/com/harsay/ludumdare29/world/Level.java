package com.harsay.ludumdare29.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.harsay.ludumdare29.MyGame;
import com.harsay.ludumdare29.assets.Graphic;

public class Level {
	
	public static enum Tile {
		NOTHING, ROCK, BACKGROUND, LAVA, LAVAROCK, INDESTRUCTIBLE
	}
	
	public Random random = new Random();
	
	public List<ArrayList<Tile>> map = new ArrayList<ArrayList<Tile>>();
	
	public int tileNumY = 0;
	public int fixNumY = 12;
	public int lavaToGenerate = 10;
	
	public Level() {
		
	}
	
	public void generate() {
		for(int x=0; x<=90; x++) {
			ArrayList<Tile> list = new ArrayList<Tile>();
			for(int y=0; y<=100; y++) {
				if(y > 10) 
					list.add(Tile.ROCK);
				else
					list.add(Tile.NOTHING);
			}
			map.add(list);
		}
		
		tileNumY += 100;
		
		Collections.reverse(map);
		
		generateLava();
		
	}
	
	public void expand() {
		fixNumY = 0;
		for(int x=0; x<=90; x++) {
			ArrayList<Tile> list = map.get(x);
			for(int y=0; y<=100; y++) {
				list.add(Tile.ROCK);
			}
		}
		tileNumY += 100;
		lavaToGenerate += 5;
		
		generateLava();
	}
	
	public void generateLava() {
		// LAVA GENERATION
		
		for(int i=0; i<=lavaToGenerate + random.nextInt((int)Math.floor(lavaToGenerate/2)); i++) { // SHOULD DEPEND OF DIFFICULTY + random
			int lavaBeginX = random.nextInt(75);
			int lavaBeginY = (tileNumY-100) + random.nextInt(78) + fixNumY;
			int lavaEndX = lavaBeginX + random.nextInt(15) + 1;
			int lavaEndY = lavaBeginY + random.nextInt(10) + 1;
			
			for(int x=lavaBeginX; x<=lavaEndX; x++) {
				for(int y=lavaBeginY; y<=lavaEndY; y++) {
					map.get(x).set(y, Tile.LAVA);
					System.out.println("MADE LAVA at ("+lavaBeginY+")");
				}
			}
			
			for(int x=lavaBeginX-1; x<=lavaEndX+1; x++) {
				for(int y=lavaBeginY-1; y<=lavaEndY+1; y++) {
					if( Math.random() < 0.99f &&
							x >= 0 && x <= 90 &&
								y >= (tileNumY-100)+fixNumY && y <= tileNumY &&
								!getTile(x, y).equals(Tile.LAVA)) 
						map.get(x).set(y, Tile.LAVAROCK);
				}
			}
		}
	}
	
	public void render(SpriteBatch sb) {
		for(int x=0; x<map.size(); x++) {
			for(int y=0; y<map.get(x).size(); y++) {
				Tile t = map.get(x).get(y);
				if(t.equals(Tile.ROCK)) {
					Graphic.tile.setPosition(x*MyGame.UNIT, y*MyGame.UNIT);
					Graphic.tile.draw(sb);
				}
				else if(t.equals(Tile.LAVA))  {
					Graphic.lava.setPosition(x*MyGame.UNIT, y*MyGame.UNIT);
					Graphic.lava.draw(sb);
				}
				else if(t.equals(Tile.LAVAROCK))  {
					Graphic.lavaRock.setPosition(x*MyGame.UNIT, y*MyGame.UNIT);
					Graphic.lavaRock.draw(sb);
				}
				else if(t.equals(Tile.BACKGROUND))  {
					Graphic.darkerTile.setPosition(x*MyGame.UNIT, y*MyGame.UNIT);
					Graphic.darkerTile.draw(sb);
				}
			}
		}
	}
	
	public void checkTile() {

	}
	
	public void removeTile(int x, int y) {
		map.get(x).set(y, Tile.BACKGROUND);
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
