package com.harsay.ludumdare29.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.harsay.ludumdare29.MyGame;
import com.harsay.ludumdare29.assets.Graphic;
import com.harsay.ludumdare29.assets.Sounds;
import com.harsay.ludumdare29.other.Controller;
import com.harsay.ludumdare29.world.Level.Tile;

public class Player extends Entity {
	
	public List<Vector2> reachableTiles = new ArrayList<Vector2>();
	
	public boolean canPressDown = true;
	public boolean canPressLeft = true;
	public boolean canPressRight = true;
	public boolean canPressUp = true;
	
	public boolean goesLeft = true;
	
	public boolean isAlive = true;
	
	public int moveCounter = 0;
	public int moveCounterAll = 0;
	
	Random random = new Random();

	public Player(MyGame game) {
		super(game, 45*MyGame.UNIT, 10*MyGame.UNIT, (int) Graphic.playerLeft.getWidth()/MyGame.UNIT, (int) Graphic.playerLeft.getHeight()/MyGame.UNIT);
	}
	
	public void update(float delta) {
		super.update(delta);

		if(!isAlive) return;

		switch(moveCounterAll) {
			case 5: game.showHint(0); break;
			case 30: game.showHint(1); break;
			case 60: game.showHint(2); break;
			case 90: game.showHint(3); break;
		}

		
		if(!Controller.isDownPressed) canPressDown = true;
		if(!Controller.isLeftPressed) canPressLeft = true;
		if(!Controller.isRightPressed) canPressRight = true;
		if(!Controller.isUpPressed) canPressUp = true;
		
		if(Controller.isDownPressed && canPressDown) {
			if(game.world.level.getTile(tileX, tileY+1).equals(Tile.ROCK) ||
					game.world.level.getTile(tileX, tileY+1).equals(Tile.LAVAROCK)) {
				game.world.level.removeTile(tileX, tileY+1);
				game.world.level.clearLine();
				//position.y += MyGame.UNIT;
				canPressDown = false;
				game.world.shake(0.2f);
				game.score += 1;
				moveCounter += 1;
				moveCounterAll += 1;
				game.world.welcomeTextFix += 1;
				if(game.world.level.lavaCount > 0) game.world.level.lavaCount--;  
				playSound();
			}
			if(game.welcome) {
				game.welcome = false;
				game.world.level.fallLava();
			}
		}
		else if(Controller.isLeftPressed && canPressLeft && !game.welcome) {
			if(game.world.level.getTile(tileX-1, tileY).equals(Tile.ROCK) ||
					game.world.level.getTile(tileX-1, tileY).equals(Tile.LAVAROCK)) {
				game.world.level.removeTile(tileX-1, tileY);
				game.world.shake(0.2f);
				playSound();
			}
			if(!game.world.level.getTile(tileX-1, tileY).equals(Tile.INDESTRUCTIBLE)) {
				position.x -= MyGame.UNIT;
				moveCounterAll += 1;
			} 
			goesLeft = true;
			canPressLeft = false;
		}
		else if(Controller.isRightPressed && canPressRight && !game.welcome) {
			if(game.world.level.getTile(tileX+1, tileY).equals(Tile.ROCK) ||
					game.world.level.getTile(tileX+1, tileY).equals(Tile.LAVAROCK)) {
				game.world.level.removeTile(tileX+1, tileY);
				game.world.shake(0.2f);
				playSound();
			}
			if(!game.world.level.getTile(tileX+1, tileY).equals(Tile.INDESTRUCTIBLE)) {
				position.x +=MyGame.UNIT;
				moveCounterAll += 1;
			} 
			goesLeft = false;
			canPressRight = false;
		} 
		else if(Controller.isUpPressed && canPressUp && !game.welcome) {
			if(game.world.level.getTile(tileX, tileY-1).equals(Tile.ROCK) ||
					game.world.level.getTile(tileX, tileY-1).equals(Tile.LAVAROCK)) {
				game.world.level.removeTile(tileX, tileY-1);
				game.world.shake(0.2f);
				canPressUp = false;
				playSound();
			}
		}
		
		//setReachableTiles();
		
		float camSpeed = game.welcome ? 3.0f*delta : 10.0f*delta;	
		
		if(moveCounter == 30) {
			game.world.level.expand();
			moveCounter = 0;
		}
		
		if(tileX <= 12) {
			game.world.cam.position.lerp(new Vector3(12*game.UNIT, position.y, 0), camSpeed);
		} 
		else if(tileX >= 79) {
			game.world.cam.position.lerp(new Vector3(79*game.UNIT, position.y, 0), camSpeed);
		}
		else {
			game.world.cam.position.lerp(position, camSpeed);
		}
		
		if(game.world.level.getTile(tileX, tileY).equals(Tile.LAVA)) {
			isAlive = false;
			playDeath();
		}	
		else if(game.world.level.getTile(tileX, tileY+1).equals(Tile.LAVA)) {
			position.y += MyGame.UNIT;
		}
		
	}
	
	public void playSound() {
		switch(random.nextInt(2)) {
		case 0: Sounds.hit1.play(); break;
		case 1: Sounds.hit2.play(); break;
		}
	}
	
	public void playDeath() {
		switch(random.nextInt(3)) {
		case 0: Sounds.gameover1.play(); break;
		case 1: Sounds.gameover2.play(); break;
		case 2: Sounds.gameover3.play(); break;
		}
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
		if(goesLeft) {
			Graphic.playerLeft.setPosition(position.x, position.y);
			Graphic.playerLeft.draw(sb);			
		} else {
			Graphic.playerRight.setPosition(position.x, position.y);
			Graphic.playerRight.draw(sb);
		}
	}

}
