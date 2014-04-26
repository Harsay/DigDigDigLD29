package com.harsay.ludumdare29.screens;

import com.harsay.ludumdare29.world.Player;
import com.harsay.ludumdare29.world.World;

public class GameScreen extends BasicScreen {
	
	public GameScreen() {
		World.add(new Player());
	}
	
	public void render(float delta) {
		super.render(delta);
	} 
}
