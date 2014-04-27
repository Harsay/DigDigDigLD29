package com.harsay.ludumdare29.screens;

import com.harsay.ludumdare29.MyGame;
import com.harsay.ludumdare29.world.Player;
import com.harsay.ludumdare29.world.World;

public class GameScreen extends BasicScreen {
	
	public GameScreen() {
		World.cam.position.y += 2000;
		World.cam.position.x += 30*MyGame.UNIT;
		World.add(new Player());
	}
	
	public void render(float delta) {
		super.render(delta);
	} 
}
