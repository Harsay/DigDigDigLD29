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
	public int fixNumY = 17;
	public int lavaToGenerate = 5;
	public int indToGenerate = 1;
	public float indAppearChance = 0.13f;
	public float lavaRockAppearChance = 1.00f;
	
	public int lavaCount = 0;
	
	MyGame game;
	
	public Level(MyGame game) {
		this.game = game;
	}
	
	public void generate() {
		for(int x=0; x<=90; x++) {
			ArrayList<Tile> list = new ArrayList<Tile>();
			for(int y=0; y<=50; y++) {
				if(y > 10) {
					if(x > 0 && x < 90) {
						list.add(Tile.ROCK);
					}
					else {
						list.add(Tile.INDESTRUCTIBLE);
					}
				}	
				else {
					list.add(Tile.BACKGROUND);
				}	
			}
			map.add(list);
		}
		
		tileNumY += 100;
		
		Collections.reverse(map);
		
		generateLava();
		generateIndestructible();
		
	}
	
	public void expand() {
		fixNumY = 20;
		for(int x=0; x<=90; x++) {
			ArrayList<Tile> list = map.get(x);
			for(int y=0; y<=game.player.moveCounter; y++) {
				if(x > 0 && x < 90) {
					list.add(Tile.ROCK);
				}
				else {
					list.add(Tile.INDESTRUCTIBLE);
				}
			}
		}
		//tileNumY += 100;
		lavaToGenerate += 1;
		indToGenerate += 1;
		indAppearChance += 0.10f;
		if(indAppearChance > 1.0f) indAppearChance = 1.0f;
		lavaRockAppearChance -= 0.10f;
		if(lavaRockAppearChance < 0) lavaRockAppearChance = 0;
		
		generateLava();
		generateIndestructible();
	}
	
	public void generateIndestructible() {
		for(int i=0; i<=indToGenerate; i++) { 
			int genBeginX = random.nextInt(75);
			int genY = /*(tileNumY-100) +*/ random.nextInt(30) + fixNumY;
			int genEndX = genBeginX + random.nextInt(15) + 1;
			
			//check x to avoid crashes
			if(genEndX > 90) genEndX = 90;
			
			for(int x=genBeginX; x<=genEndX; x++) {
				/*if(Math.random() < indAppearChance)*/ map.get(x).set(genY, Tile.INDESTRUCTIBLE);
			}
		}
	}
	
	public void generateLava() {
		// LAVA GENERATION
		
		for(int i=0; i<=lavaToGenerate + random.nextInt((int)Math.floor(lavaToGenerate/2)); i++) { 
			int lavaBeginX = random.nextInt(90);
			int lavaBeginY = /*(tileNumY-100) +*/ random.nextInt(20) + fixNumY;
			int lavaEndX = lavaBeginX + random.nextInt(15) + 1;
			int lavaEndY = lavaBeginY + random.nextInt(10) /*+1*/;
			
			//check X to avoid crashes
			if(lavaEndX > 90) lavaEndX = 90;
			if(lavaEndY >= map.get(0).size()) lavaEndY = map.get(0).size()-1;
			
			for(int x=lavaBeginX; x<=lavaEndX; x++) {
				for(int y=lavaBeginY; y<=lavaEndY; y++) {
					System.out.println(map.get(x).size()+"|"+y);
					if(!getTile(x, y).equals(Tile.INDESTRUCTIBLE)) map.get(x).set(y, Tile.LAVA);
				}
			}
			
			for(int x=lavaBeginX-1; x<=lavaEndX+1; x++) {
				for(int y=lavaBeginY-1; y<=lavaEndY+1; y++) {
					if( Math.random() < lavaRockAppearChance &&
							x >= 0 && x <= 90 &&
								y >= /*(tileNumY-100)+*/ fixNumY && y <= tileNumY &&
								!getTile(x, y).equals(Tile.LAVA) &&
								!getTile(x, y).equals(Tile.INDESTRUCTIBLE)) 
						map.get(x).set(y, Tile.LAVAROCK);
				}
			}
		}
	}
	
	public void render(SpriteBatch sb) {
		int elements = map.size() * map.get(0).size();
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
				else if(t.equals(Tile.INDESTRUCTIBLE))  {
					Graphic.ind.setPosition(x*MyGame.UNIT, y*MyGame.UNIT);
					Graphic.ind.draw(sb);
				}
				else if(t.equals(Tile.BACKGROUND))  {
					Graphic.darkerTile.setPosition(x*MyGame.UNIT, y*MyGame.UNIT);
					Graphic.darkerTile.draw(sb);
				}
			}
		}
		System.out.println("Map size: "+elements);
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

	public void fallLava() {
		//if(game.player.tileY - z > 10) z = game.player.tileY-10; 
		for(int x=0; x<=90; x++) map.get(x).set(lavaCount, Tile.LAVA);
		lavaCount++;
	}
	
	public void clearLine() {
		for(int x=0; x<map.size(); x++) {
			map.get(x).remove(0);
		}
	}

}
