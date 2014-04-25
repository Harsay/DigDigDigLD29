package com.harsay.ludumdare29;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.harsay.ludumdare29.assets.Graphic;
import com.harsay.ludumdare29.assets.Sound;
import com.harsay.ludumdare29.other.Controller;

/*
 * Hello.
 * I made this game for Ludum Dare 29 in 48 hours!
 * ...
 * 
 */

public class MyGame extends Game {
	
	public static Controller controller = new Controller();
	public static Sound sound = new Sound();
	public static Graphic graphic = new Graphic();

	@Override
	public void create() {
		Gdx.input.setInputProcessor(controller);
		
	}
	
}
