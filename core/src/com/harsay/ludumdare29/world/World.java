package com.harsay.ludumdare29.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.harsay.ludumdare29.MyGame;
import com.harsay.ludumdare29.assets.Graphic;

public class World {
	
	public SpriteBatch sb = new SpriteBatch();
	public ShapeRenderer sr = new ShapeRenderer();
	public List<Entity> entities = new ArrayList<Entity>();
	
	public OrthographicCamera cam = new OrthographicCamera(MyGame.WIDTH, MyGame.HEIGHT);
	
	public Level level = new Level();
	
	public Random random = new Random();
	
	public float shakeDuration = 0;
	public float shakePower = 0;
	public float shakeMaximumDuration = 300;
	public boolean isShaking = false;
	
	MyGame game;
	
	public World(MyGame game) {
		this.game = game;
		add(game.player);
		System.out.println("l");
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
		Vector3 pos = new Vector3(0, 32, 0);
		cam.unproject(pos);
		Graphic.font.draw(sb, "Score: 0", pos.x, pos.y);
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
