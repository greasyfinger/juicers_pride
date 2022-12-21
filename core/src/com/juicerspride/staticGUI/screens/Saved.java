package com.juicerspride.staticGUI.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.juicerspride.staticGUI.staticGUI;

import java.util.ArrayList;

public class Saved implements Screen {

    private final staticGUI gui;
    private Texture background;
    private TextButton play,exit;

    private Skin uiskin;
    private Sound click;
    private Stage stage;
    private ArrayList<JuicersPride> saved_games;

    public Saved(staticGUI gui){
        this.gui = gui;
        stage = new Stage();
        saved_games = new ArrayList<JuicersPride>();
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        this.background = gui.assets.get("saved_bg.png",Texture.class);
        this.click = gui.assets.get("select_sound.mp3",Sound.class);
        this.uiskin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"),
                gui.assets.<TextureAtlas>get("skin/craftacular-ui.atlas",TextureAtlas.class));

        this.exit = new TextButton("EXIT",uiskin,"default");
        this.exit.setPosition( stage.getWidth()/2 + 50, 75 );
        this.exit.setSize(200,75);
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                click.play();
                gui.setScreen(gui.lobby);
            }
        });
        stage.addActor(this.exit);

        this.play = new TextButton("PLAY",uiskin,"default");
        this.play.setPosition( stage.getWidth()/2 - 175, 75 );
        this.play.setSize(200,75);
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                click.play();
                gui.setScreen(saved_games.get(0));
            }
        });
        stage.addActor(this.play);
    }

    @Override
    public void render(float delta) {

        gui.batch.begin();
        gui.batch.draw(background,0, 0, stage.getWidth(), stage.getHeight());
        gui.batch.end();
        stage.act(delta);
        stage.draw();

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
        click.dispose();
        stage.dispose();
        background.dispose();
        uiskin.dispose();

    }
}
