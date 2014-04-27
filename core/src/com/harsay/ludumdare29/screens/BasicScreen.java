package com.harsay.ludumdare29.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.harsay.ludumdare29.MyGame;
import com.harsay.ludumdare29.world.World;

public class BasicScreen implements Screen {
	
	MyGame game;
	
	public BasicScreen(MyGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		GL20 gl = Gdx.graphics.getGL20();
		gl.glClearColor(0, 0.5f, 1, 0);
	    gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
		game.world.update(delta);
		game.world.render();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
