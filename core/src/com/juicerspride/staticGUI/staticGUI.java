package com.juicerspride.staticGUI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.juicerspride.staticGUI.screens.Saved;
import com.juicerspride.staticGUI.screens.JuicersPride;
import com.juicerspride.staticGUI.screens.loading;
import com.juicerspride.staticGUI.screens.lobby;

public class staticGUI extends Game {

	public static final int width = 1261 ;
	public static final int height = 850;
	public OrthographicCamera camera;
	public SpriteBatch batch;
	public AssetManager assets;

	public Screen loading,lobby,saved;

	@Override
	public void create () {
		assets =new AssetManager();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,width,height);
		batch = new SpriteBatch();
		loading = new loading(this);
		lobby = new lobby(this);
		saved = new Saved(this);
		this.setScreen(loading);

	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

		batch.dispose();
		assets.dispose();
	}

}
