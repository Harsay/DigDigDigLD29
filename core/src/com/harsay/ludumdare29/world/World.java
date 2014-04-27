package com.harsay.ludumdare29.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.harsay.ludumdare29.MyGame;

public class World {
	
	public static SpriteBatch sb = new SpriteBatch();
	public static ShapeRenderer sr = new ShapeRenderer();
	public static List<Entity> entities = new ArrayList<Entity>();
	
	public static OrthographicCamera cam = new OrthographicCamera(MyGame.WIDTH, MyGame.HEIGHT);
	
	public static Level level = new Level();
	
	public static Random random = new Random();
	
	public static int shakeCounter = 0;
	public static float shakeDuration = 0;
	public static float shakePower = 0;
	public static float shakeMaximumDuration = 300;
	public static boolean isShaking = false;
	
	public World() {
		cam.setToOrtho(true, MyGame.WIDTH, MyGame.HEIGHT);
		cam.zoom = 0.7f;
		level.generate();
	}
	
	public static void update(float delta) {
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
	}
	
	public static void render() {

		cam.update();
		sb.begin();
		sb.setProjectionMatrix(cam.combined);
		level.render(sb);
		for(int i = 0; i < entities.size(); i++) {
			Entity ent = entities.get(i);
			ent.render(sb);
		}
		sb.end();

	}
	
	public static void shake(float dur) {
		shakeCounter = 0;
		shakeMaximumDuration = dur;
		isShaking = true;
	}
	
	public static void clear() {
		
	}
	
	public static void add(Entity ent) {
		entities.add(ent);
	}
	
}
