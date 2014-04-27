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
	
	public List<Vector2> reachableTiles = new ArrayList<Vector2>();
	
	public boolean canPressDown = true;
	public boolean canPressLeft = true;
	public boolean canPressRight = true;
	public boolean canPressUp = true;
	
	public int score = 0;
	public boolean isAlive = true;

	public Player(MyGame game) {
		super(game, 45*MyGame.UNIT, 10*MyGame.UNIT, (int) Graphic.player.getWidth()/MyGame.UNIT, (int) Graphic.player.getHeight()/MyGame.UNIT);
	}
	
	public void update(float delta) {
		super.update(delta);

		if(!isAlive) return;
		
		if(game.world.level.getTile(tileX, tileY).equals(Tile.LAVA)) {
			isAlive = false;
		}	
		else if(game.world.level.getTile(tileX, tileY+1).equals(Tile.LAVA)) {
			position.y += MyGame.UNIT;
		}

		
		if(!Controller.isDownPressed) canPressDown = true;
		if(!Controller.isLeftPressed) canPressLeft = true;
		if(!Controller.isRightPressed) canPressRight = true;
		if(!Controller.isUpPressed) canPressUp = true;
		
		if(Controller.isDownPressed && canPressDown) {
			if(game.world.level.getTile(tileX, tileY+1).equals(Tile.ROCK) ||
					game.world.level.getTile(tileX, tileY+1).equals(Tile.LAVAROCK)) {
				game.world.level.removeTile(tileX, tileY+1);
				position.y += MyGame.UNIT;
				canPressDown = false;
				game.world.shake(0.2f);
				score += 1;
			}
		}
		else if(Controller.isLeftPressed && canPressLeft) {
			if(game.world.level.getTile(tileX-1, tileY).equals(Tile.ROCK) ||
					game.world.level.getTile(tileX-1, tileY).equals(Tile.LAVAROCK)) {
				game.world.level.removeTile(tileX-1, tileY);
				game.world.shake(0.2f);
			}
			if(!game.world.level.getTile(tileX-1, tileY).equals(Tile.INDESTRUCTIBLE)) {
				position.x -= MyGame.UNIT;
			}
			canPressLeft = false;
		}
		else if(Controller.isRightPressed && canPressRight) {
			if(game.world.level.getTile(tileX+1, tileY).equals(Tile.ROCK) ||
					game.world.level.getTile(tileX+1, tileY).equals(Tile.LAVAROCK)) {
				game.world.level.removeTile(tileX+1, tileY);
				game.world.shake(0.2f);
			}
			if(!game.world.level.getTile(tileX+1, tileY).equals(Tile.INDESTRUCTIBLE)) {
				position.x +=MyGame.UNIT;
			}
			canPressRight = false;
		} 
		else if(Controller.isUpPressed && canPressUp) {
			if(game.world.level.getTile(tileX, tileY-1).equals(Tile.ROCK) ||
					game.world.level.getTile(tileX, tileY-1).equals(Tile.LAVAROCK)) {
				game.world.level.removeTile(tileX, tileY-1);
				game.world.shake(0.2f);
				canPressUp = false;
			}
		}
		
		//setReachableTiles();
		
		
		if(tileY == game.world.level.tileNumY-9) {
			game.world.level.expand();
		}
		
		game.world.cam.position.lerp(position, 0.05f);
	}
	
	public void setReachableTiles() {
		
		reachableTiles.clear();
		Level lvl = game.world.level;
		
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
