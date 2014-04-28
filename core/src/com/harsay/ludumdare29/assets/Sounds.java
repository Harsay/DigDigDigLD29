package com.harsay.ludumdare29.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
	
	public static Sound hit1;
	public static Sound hit2;
	public static Sound hit3;
	public static Sound hit4;
	public static Sound hit5;

	public static Sound gameover1;
	public static Sound gameover2;
	public static Sound gameover3;
	
	public static void init() {
		
		hit1 = Gdx.audio.newSound(Gdx.files.internal("sound/hit1.wav"));
		hit2 = Gdx.audio.newSound(Gdx.files.internal("sound/hit2.wav"));
		
		gameover1 = Gdx.audio.newSound(Gdx.files.internal("sound/gameover1.wav"));
		gameover2 = Gdx.audio.newSound(Gdx.files.internal("sound/gameover2.wav"));
		gameover3 = Gdx.audio.newSound(Gdx.files.internal("sound/gameover3.wav"));
		
	}
	
}
