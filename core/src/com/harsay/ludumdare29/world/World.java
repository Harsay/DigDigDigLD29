package com.harsay.ludumdare29.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.harsay.ludumdare29.MyGame;
import com.harsay.ludumdare29.assets.Graphic;

public class World {
	
	public SpriteBatch sb = new SpriteBatch();
	public ShapeRenderer sr = new ShapeRenderer();
	public List<Entity> entities = new ArrayList<Entity>();
	
	public OrthographicCamera cam = new OrthographicCamera(MyGame.WIDTH, MyGame.HEIGHT);
	
	public Level level;
	
	public Random random = new Random();
	
	public float shakeDuration = 0;
	public float shakePower = 0;
	public float shakeMaximumDuration = 300;
	public boolean isShaking = false;
	
	public float lavaFallTime = 0;
	public float lavaFallTimeMax = 0.3f;
	public int lavaFalls = 0;
	
	public int welcomeTextFix = 0;
	
	MyGame game;
	
	public World(MyGame game) {
		this.game = game;
		level = new Level(game);
		add(game.player);
		cam.setToOrtho(true, MyGame.WIDTH, MyGame.HEIGHT);
		cam.zoom = 0.7f;
		level.generate();
	}
	
	public void update(float delta) {
		if(isShaking) {
			if(shakeDuration <= shakeMaximumDuration) {
				cam.translate(random.nextInt(11) - 5, random.nextInt(11) - 5);
				shakeDuration += delta;
			} else {
				shakeDuration = 0;
				isShaking = false;
			}
		}
		for(int i = 0; i < entities.size(); i++) {
			Entity ent = entities.get(i);
			ent.update(delta);
		}
		
		if(game.player.isAlive && !game.welcome) {
			lavaFallTime += delta;
			if(lavaFallTime >= lavaFallTimeMax) {
				level.fallLava();
				lavaFallTime = 0;
			}
		}
		
		lavaFallTimeMax = (float) (0.3f - (0.05f * (Math.floor(game.score / 30)))); 
		if(lavaFallTimeMax < 0.15f) lavaFallTimeMax = 0.15f;
	
	}
	
	public void render() {

		cam.update();
		sb.begin();
		sb.setProjectionMatrix(cam.combined);
		level.render(sb);
		for(int i = 0; i < entities.size(); i++) {
			Entity ent = entities.get(i);
			ent.render(sb);
		}
		
		int fix = welcomeTextFix*MyGame.UNIT;
		
		Graphic.worldFont.setScale(2, -2);
		Graphic.worldFont.draw(sb, "DIG! DIG! DIG!", 45*MyGame.UNIT-260, 10*MyGame.UNIT-150-fix);
		Graphic.worldFont.setScale(0.4f, -0.4f);
		Graphic.worldFont.draw(sb, "Press down arrow to start digging!", 45*MyGame.UNIT-150, 10*MyGame.UNIT-50-fix);

		
		sb.end();

	}
	
	public void shake(float dur) {
		shakeMaximumDuration = dur;
		isShaking = true;
	}
	
	public void add(Entity ent) {
		entities.add(ent);
	}
}
