package com.harsay.ludumdare29.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.harsay.ludumdare29.MyGame;
import com.harsay.ludumdare29.assets.Graphic;

public class Player extends Entity {

	public Player() {
		super(14*MyGame.UNIT, 9*MyGame.UNIT, (int) Graphic.player.getWidth()/MyGame.UNIT, (int) Graphic.player.getHeight()/MyGame.UNIT);
	}
	
	public void update(float delta) {
		super.update(delta);
		
	}
	
	public void render(SpriteBatch sb) {
		Graphic.player.setPosition(position.x, position.y);
		Graphic.player.draw(sb);
	}

}
