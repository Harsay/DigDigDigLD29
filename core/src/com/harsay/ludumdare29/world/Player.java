package com.harsay.ludumdare29.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.harsay.ludumdare29.MyGame;
import com.harsay.ludumdare29.assets.Graphic;

public class Player extends Entity {

	public Player() {
		super(13*MyGame.UNIT, MyGame.UNIT, 3*MyGame.UNIT, 3*MyGame.UNIT);
	}
	
	public void render(SpriteBatch sb) {
		Graphic.player.setPosition(position.x, position.y);
		Graphic.player.draw(sb);
	}

}
