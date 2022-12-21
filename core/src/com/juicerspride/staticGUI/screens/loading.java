package com.juicerspride.staticGUI.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.juicerspride.staticGUI.staticGUI;

public class loading implements Screen {

    private final staticGUI gui;
    private Stage stage;
    private Texture background;

    public loading(final staticGUI gui) {
        this.gui = gui;
        this.stage = new Stage();
        background = new Texture(Gdx.files.internal("loading_bg.png"));
    }

    private void load_assets() {
        gui.assets.load("loading_bg.png", Texture.class);
        gui.assets.load("ingame_music.mp3", Music.class);
        gui.assets.load("lobby.png", Texture.class);
        gui.assets.load("select_sound.mp3", Sound.class);
        gui.assets.load("lobby_music.mp3",Music.class);
        gui.assets.load("skin/craftacular-ui.atlas", TextureAtlas.class);
        gui.assets.load("skin/craftacular-ui.png",Texture.class);
        gui.assets.load("newgame_bg.png",Texture.class);
        gui.assets.load("newgame_fg.png",Texture.class);
        gui.assets.load("player1.png",Texture.class);
        gui.assets.load("player2.png",Texture.class);
        gui.assets.load("popup.png",Texture.class);
        gui.assets.load("saved_bg.png",Texture.class);
        for (int i = 1; i < 4; i++) {
            gui.assets.load("tank" + i + ".png",Texture.class);
        }

    }


    @Override
    public void show() {
        load_assets();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear (GL20.GL_COLOR_BUFFER_BIT);
        gui.batch.begin();
        gui.batch.draw(background, 0,0, stage.getWidth(), stage.getHeight());
        gui.batch.end();
        if(gui.assets.update()){
            gui.setScreen(gui.lobby);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
    }
}
