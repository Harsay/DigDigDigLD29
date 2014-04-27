package com.harsay.ludumdare29.screens;

import com.harsay.ludumdare29.MyGame;
import com.harsay.ludumdare29.other.Controller;
import com.harsay.ludumdare29.world.Player;
import com.harsay.ludumdare29.world.World;

public class GameScreen extends BasicScreen {
	
	public float endZoomSpeed = 0.01f;
	public float endRotationSpeed = 0.5f;
	public float endRotation = 0;
	public float endRotationMax = 30f;
	
	public GameScreen(MyGame game) {
		super(game);
		
		game.world.cam.position.y += 2000;
		game.world.cam.position.x += 30*MyGame.UNIT;
	}
	
	public void render(float delta) {
		super.render(delta);
		
		if(!game.player.isAlive) {
			
			if(game.world.cam.zoom > 0.2f) {
				game.world.cam.zoom -= endZoomSpeed*delta;
			} else {
				game.world.cam.zoom = 0.2f;
			}
			
			if(endRotation <= endRotationMax) {
				endRotation += endRotationSpeed*delta;
				game.world.cam.rotate(endRotationSpeed*delta);
				System.out.println("rotating");
			}
			
			game.world.cam.position.lerp(game.player.position, 0.01f);
			
			// display text stuff here
			
			if(Controller.isUpPressed) {
				game.world = new World(game);
				game.player = new Player(game);
				game.setScreen(new GameScreen(game));
			}
		}
	} 
}
