package com.harsay.ludumdare29.world;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.harsay.ludumdare29.MyGame;
import com.harsay.ludumdare29.assets.Graphic;
import com.harsay.ludumdare29.other.Controller;
import com.harsay.ludumdare29.world.Level.Tile;

public class Player extends Entity {
	
	public static List<Vector2> reachableTiles = new ArrayList<Vector2>();
	
	public boolean canPressDown = true;
	public boolean canPressLeft = true;
	public boolean canPressRight = true;
	public boolean canPressUp = true;

	public Player() {
		super(45*MyGame.UNIT, 10*MyGame.UNIT, (int) Graphic.player.getWidth()/MyGame.UNIT, (int) Graphic.player.getHeight()/MyGame.UNIT);
	}
	
	public void update(float delta) {
		super.update(delta);

		
		if(World.level.getTile(tileX, tileY).equals(Tile.LAVA)) {
			System.out.println("DEAD");
		}	
		else if(World.level.getTile(tileX, tileY+1).equals(Tile.LAVA)) {
			position.y += MyGame.UNIT;
		}

		
		if(!Controller.isDownPressed) canPressDown = true;
		if(!Controller.isLeftPressed) canPressLeft = true;
		if(!Controller.isRightPressed) canPressRight = true;
		if(!Controller.isUpPressed) canPressUp = true;
		
		if(Controller.isDownPressed && canPressDown) {
			if(World.level.getTile(tileX, tileY+1).equals(Tile.ROCK) ||
					World.level.getTile(tileX, tileY+1).equals(Tile.LAVAROCK)) {
				World.level.removeTile(tileX, tileY+1);
				position.y += MyGame.UNIT;
				canPressDown = false;
				World.shake(0.2f);
			}
		}
		else if(Controller.isLeftPressed && canPressLeft) {
			if(World.level.getTile(tileX-1, tileY).equals(Tile.ROCK) ||
					World.level.getTile(tileX-1, tileY).equals(Tile.LAVAROCK)) {
				World.level.removeTile(tileX-1, tileY);
				World.shake(0.2f);
			}
			if(!World.level.getTile(tileX-1, tileY).equals(Tile.INDESTRUCTIBLE)) {
				position.x -= MyGame.UNIT;
			}
			canPressLeft = false;
		}
		else if(Controller.isRightPressed && canPressRight) {
			if(World.level.getTile(tileX+1, tileY).equals(Tile.ROCK) ||
					World.level.getTile(tileX+1, tileY).equals(Tile.LAVAROCK)) {
				World.level.removeTile(tileX+1, tileY);
				World.shake(0.2f);
			}
			if(!World.level.getTile(tileX+1, tileY).equals(Tile.INDESTRUCTIBLE)) {
				position.x +=MyGame.UNIT;
			}
			canPressRight = false;
		} 
		else if(Controller.isUpPressed && canPressUp) {
			if(World.level.getTile(tileX, tileY-1).equals(Tile.ROCK) ||
					World.level.getTile(tileX, tileY-1).equals(Tile.LAVAROCK)) {
				World.level.removeTile(tileX, tileY-1);
				World.shake(0.2f);
				canPressUp = false;
			}
		}
		
		//setReachableTiles();
		
		
		if(tileY == World.level.tileNumY-9) {
			World.level.expand();
		}
		
		World.cam.position.lerp(position, 0.05f);
	}
	
	public static boolean canWalkTo(int toWalkX, int toWalkY) {
		return false;
	}
	
	public void setReachableTiles() {
		
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
