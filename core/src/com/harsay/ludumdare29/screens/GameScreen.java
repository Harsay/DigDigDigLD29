package com.harsay.ludumdare29.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.harsay.ludumdare29.MyGame;
import com.harsay.ludumdare29.assets.Graphic;
import com.harsay.ludumdare29.other.Controller;
import com.harsay.ludumdare29.world.Player;
import com.harsay.ludumdare29.world.World;

public class GameScreen extends BasicScreen {
	
	public float endZoomSpeed = 0.01f;
	public float endRotationSpeed = 0.5f;
	public float endRotation = 0;
	public float endRotationMax = 30f;
	
	public SpriteBatch sb = new SpriteBatch();
	public ShapeRenderer sr = new ShapeRenderer();
	
	public OrthographicCamera cam = new OrthographicCamera(MyGame.WIDTH, MyGame.HEIGHT);
	
	public boolean welcome = true;
	
	public GameScreen(MyGame game) {
		super(game);
		
		cam.setToOrtho(false, MyGame.WIDTH, MyGame.HEIGHT);
		
		game.world.cam.position.y += 2000;
		game.world.cam.position.x += 30*MyGame.UNIT;
	}
	
	public void render(float delta) {
		super.render(delta);
		
		cam.update();
		sr.setProjectionMatrix(cam.combined);
		sb.setProjectionMatrix(cam.combined);
		
		
	    Gdx.gl.glEnable(GL20.GL_BLEND);
	    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		sr.begin(ShapeType.Filled);
			if(!game.player.isAlive) {
				sr.setColor(0, 0, 0, 0.3f);
				sr.rect(0, 0, MyGame.WIDTH, MyGame.HEIGHT);
			}
		sr.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
		
		sb.begin();
		
		if(game.welcome) {
			Graphic.font.draw(sb, "Game made for Ludum Dare 29 in 48 hours by Harsay! (@HarsayHD)", 16, 32);
		} else if(!game.player.isAlive) {
			Graphic.font.setScale(2, 2);
			Graphic.font.draw(sb, "Game Over!", 210, 400);
			Graphic.font.setScale(0.5f, 0.5f);
			Graphic.font.draw(sb, "Press ENTER to try again!", 340, 300);
			Graphic.font.setScale(1, 1);
			Graphic.font.draw(sb, "Your score: "+game.score, 330, 180);
		} else {
			
			if(game.displayHint) {
				game.hintDuration += delta;
				if(game.hintDuration <= game.hintDurationMax && !game.shownHint[game.hintNum]) {
					String display = game.hint[game.hintNum];
					TextBounds bounds = Graphic.font.getBounds(display);
					Graphic.font.draw(sb, display, MyGame.WIDTH/2-bounds.width/2, MyGame.HEIGHT-100);
				} else {	
					game.hintDuration = 0;
					game.shownHint[game.hintNum] = true;
					game.displayHint = false;
				}
			}
			
			
			Graphic.font.draw(sb, "Score: "+game.score, 16, 32);
		}
		
		if(!game.player.isAlive) {
			
			if(game.world.cam.zoom > 0.2f) {
				game.world.cam.zoom -= endZoomSpeed*delta;
			} else {
				game.world.cam.zoom = 0.2f;
			}
			
			if(endRotation <= endRotationMax) {
				endRotation += endRotationSpeed*delta;
				game.world.cam.rotate(endRotationSpeed*delta);
			}
			
			if(game.player.tileX <= 12) {
				game.world.cam.position.lerp(new Vector3(12*game.UNIT, game.player.position.y, 0), 0.01f);
			} 
			else if(game.player.tileX >= 79) {
				game.world.cam.position.lerp(new Vector3(79*game.UNIT, game.player.position.y, 0), 0.01f);
			}
			else {
				game.world.cam.position.lerp(game.player.position, 0.01f);
			}
			
			
			if(Controller.isEnterPressed) {
				game.player = new Player(game);
				game.world = new World(game);
				Graphic.font.setScale(0.5f, 0.5f);
				game.welcome = true;
				game.score = 0;
				game.setScreen(new GameScreen(game));
			}
		}
		
		sb.end();
	} 
}
