package com.juicerspride.staticGUI.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.juicerspride.staticGUI.staticGUI;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import java.io.IOException;
import java.util.ArrayList;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

public class lobby implements Screen
{
    private final staticGUI gui;
    private Stage stage;
    private Texture background;
    static Sound select_tank;
    private ArrayList<Image> tank_image = new ArrayList<Image>();
    private Image player1,player2;
    private Music bg_music;
    private Skin uiskin;
    private TextButton back,forward,play,exit,load;

    private int tnk_pst = 1;

    public lobby(final staticGUI gui) {
        this.gui = gui;
        this.stage = new Stage(new FitViewport(staticGUI.width,staticGUI.height,gui.camera));
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        this.uiskin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
        select_tank = gui.assets.get("select_sound.mp3",Sound.class);
        background = gui.assets.get("lobby.png",Texture.class);
        bg_music = gui.assets.get("lobby_music.mp3",Music.class);
        bg_music.setLooping(true);
        player1 = new Image(gui.assets.get(("player1.png"), Texture.class));
        player2 = new Image(gui.assets.get(("player2.png"), Texture.class));
        player1.setScale(0.5F);
        player2.setScale(0.5F);
        player1.setPosition(90,stage.getHeight() -150);
        player2.setPosition(stage.getWidth() - 200, stage.getHeight() -150);
        stage.addActor(player1);
        stage.addActor(player2);
        for (int i = 1; i < 4; i++) {
            Texture tank = gui.assets.get(("tank" + i + ".png"), Texture.class);
            tank_image.add(new Image(tank));
            int k = 0;
            if (i == 2) k = 10;
            tank_image.get(i - 1).setOrigin(stage.getWidth() / 3 - k * 3 - 50, stage.getHeight() / 4 - 170 + k);
            stage.addActor(tank_image.get(i-1));
        }
        int left_clck = 0;
        int right_clck = 3;
        bg_music.play();

        for (int i = 0; i < 3; i++) {
            Image tank = tank_image.get(i);
            tank.setPosition(tank.getOriginX() - 1000*(i), tank.getOriginY());
        }
        button_initalise(left_clck,right_clck);
        stage.addActor(tank_image.get(0));
        stage.addActor(tank_image.get(1));
        stage.addActor(tank_image.get(2));
    }

    private void tank_move(int direction){
        if((this.tnk_pst==-1 && direction>0 )|| (this.tnk_pst == 1 && direction<0 ))return;
        tank_image.get(0).addAction(sequence(parallel(moveBy(1000*direction,0, 0.25f))));
        tank_image.get(1).addAction(sequence(parallel(moveBy(1000*direction,0, 0.25f))));
        tank_image.get(2).addAction(sequence(parallel(moveBy(1000*direction,0, 0.25f))));
        this.tnk_pst-=direction;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear (GL20.GL_COLOR_BUFFER_BIT);
        gui.batch.begin();
        gui.batch.draw(background, 0,0, stage.getWidth(), stage.getHeight());
        gui.batch.end();
        stage.act(delta);
        stage.draw();
    }

    private void button_initalise(int left, int right) {
        this.forward = new TextButton(">",uiskin,"default");
        this.forward.setPosition(stage.getWidth()- 150, stage.getHeight()/2 - 50 );
        this.forward.setSize(50,50);
        forward.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                select_tank.play();
                tank_move(1);
            }

        });
        stage.addActor(this.forward);

        this.back = new TextButton("<",uiskin,"default");
        this.back.setPosition( 100, stage.getHeight()/2 - 50 );
        this.back.setSize(50,50);
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                select_tank.play();
                tank_move(-1);
                }

            });
        stage.addActor(this.back);

        this.play = new TextButton("START",uiskin,"default");
        this.play.setPosition( stage.getWidth()/2 - 400, 75 );
        this.play.setSize(200,75);
        play.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                select_tank.play();
                bg_music.pause();
                tnk_pst = 1;
                Screen JuicersPride = new JuicersPride(gui);
                gui.setScreen(JuicersPride);
            }
        });

        stage.addActor(this.play);

        this.exit = new TextButton("EXIT",uiskin,"default");
        this.exit.setPosition( stage.getWidth()/2 + 200, 75 );
        this.exit.setSize(200,75);
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                select_tank.play();
                Gdx.app.exit();
            }
        });
        stage.addActor(this.exit);

        this.load = new TextButton("LOAD",uiskin,"default");
        this.load.setPosition( stage.getWidth()/2 - 100, 75 );
        this.load.setSize(200,75);
        load.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
              select_tank.play();
                bg_music.pause();
                tnk_pst = 1;
              gui.setScreen(gui.saved);
            }
        });
        stage.addActor(this.load);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);
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
        select_tank.dispose();
        bg_music.dispose();
        uiskin.dispose();
    }
}
