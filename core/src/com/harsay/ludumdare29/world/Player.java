package com.harsay.ludumdare29.world;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.harsay.ludumdare29.MyGame;
import com.harsay.ludumdare29.assets.Graphic;
import com.harsay.ludumdare29.world.Level.Tile;

public class Player extends Entity {
	
	public static List<Vector2> reachableTiles = new ArrayList<Vector2>();
	
	public static boolean walkRight = false;
	public static boolean walkLeft = false;
	public static boolean isWalking = false;
	public static int reachTileX = 0;

	public Player() {
		super(15*MyGame.UNIT, 9*MyGame.UNIT, (int) Graphic.player.getWidth()/MyGame.UNIT, (int) Graphic.player.getHeight()/MyGame.UNIT);
	}
	
	public void update(float delta) {
		super.update(delta);
		
		if(World.level.getTile(tileX, tileY+height).equals(Tile.NOTHING)) {
			velocity.y += speed*delta;
		} else {
			velocity.y = 0;
		}
		
		if(isWalking) {
			if(walkRight) velocity.x += speed*delta;
			else if(walkLeft) velocity.x -= speed*delta;
			if(tileX == reachTileX) {
				isWalking = false;
				walkLeft = false;
				walkRight = false;
				velocity.x = 0;
				position.x = reachTileX*MyGame.UNIT;
			}
		}
		
		
		
		position.y += velocity.y;
		position.x += velocity.x;
		
		setReachableTiles();
		
		World.cam.position.lerp(position, 0.05f);
	}
	
	public static boolean canWalkTo(int toWalkX, int toWalkY) {
		return false;
	}
	
	public void setReachableTiles() {
		/*
		 * tileY+height
		 * tileY-1
		 * tileX+1
		 * tileX-1
		 * tileX+1 tileY+1
		 * tileX-1 tileY+1
		 */
		
		reachableTiles.clear();
		Level lvl = World.level;
		
		if(lvl.getTile(tileX, tileY+height).equals(Tile.ROCK)) reachableTiles.add(new Vector2(tileX, tileY+height));
		if(lvl.getTile(tileX, tileY-1).equals(Tile.ROCK)) reachableTiles.add(new Vector2(tileX, tileY-1));
		
		if(lvl.getTile(tileX+1, tileY).equals(Tile.ROCK)) reachableTiles.add(new Vector2(tileX+1, tileY));
		else if(lvl.getTile(tileX+1, tileY-1).equals(Tile.ROCK)) reachableTiles.add(new Vector2(tileX+1, tileY-1));
		
		if(lvl.getTile(tileX-1, tileY).equals(Tile.ROCK)) reachableTiles.add(new Vector2(tileX-1, tileY));
		else if(lvl.getTile(tileX-1, tileY-1).equals(Tile.ROCK)) reachableTiles.add(new Vector2(tileX-1, tileY-1));
		
		if(lvl.getTile(tileX+1, tileY+1).equals(Tile.ROCK)) reachableTiles.add(new Vector2(tileX+1, tileY+1));
		else if(lvl.getTile(tileX+1, tileY+2).equals(Tile.ROCK)) reachableTiles.add(new Vector2(tileX+1, tileY+2));
		
		if(lvl.getTile(tileX-1, tileY+1).equals(Tile.ROCK)) reachableTiles.add(new Vector2(tileX-1, tileY+1));
		else if(lvl.getTile(tileX-1, tileY+2).equals(Tile.ROCK)) reachableTiles.add(new Vector2(tileX-1, tileY+2));
		
		System.out.println(reachableTiles.size());
		
	}
	
	public void render(SpriteBatch sb) {
		Graphic.player.setPosition(position.x, position.y);
		Graphic.player.draw(sb);
	}

}
