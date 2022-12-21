package com.juicerspride.staticGUI.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.juicerspride.staticGUI.TankScreen;
import com.juicerspride.staticGUI.staticGUI;

public class popup extends TankScreen {

    TextButton resume, exit, save;
    Screen JP_nadda;

    public popup(staticGUI gui, Screen JP) {
        super(gui);
        JP_nadda = JP;
    }

    @Override
    public void show_more() {
        super.show_more();
        init_post_loader("popup.png","skin/craftacular-ui.json","skin/craftacular-ui.atlas");
        this.resume = button_create(this.resume,"RESUME",stage.getHeight()/2 - 35 ,stage.getWidth()/2-110);
        resume.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click.play();
                gui.setScreen(JP_nadda);
            }});
        stage.addActor(resume);
        this.exit = button_create(this.exit,"EXIT",stage.getHeight()/2 + 150,stage.getWidth()/2-110);
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click.play();
                gui.setScreen(gui.lobby);
            }});
        stage.addActor(exit);
        this.save = button_create(this.save,"SAVE",stage.getHeight()/2 - 220,stage.getWidth()/2-110);
        stage.addActor(save);
        save.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click.play();
                gui.setScreen(gui.lobby);
            }});
    }
}
