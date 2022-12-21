package com.juicerspride.staticGUI;


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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class TankScreen implements Screen{

    protected final staticGUI gui;
    protected Texture background;
    protected Skin uiskin;
    protected Sound click;
    protected Stage stage;

    public TankScreen(staticGUI gui) {
        this.gui = gui;
        stage = new Stage();
    }

    public void init_post_loader(String bg,  String json,String atlas){
        this.background =  gui.assets.get(bg,Texture.class);
        this.uiskin=new Skin(Gdx.files.internal(json), gui.assets.<TextureAtlas>get(atlas,TextureAtlas.class));
    }

    protected TextButton button_create(TextButton button, String task,float height, float width, float size_x, float size_y ){
        button = new TextButton(task,this.uiskin,"default");
        button.setPosition( width, height);
        button.setSize(size_x,size_y);
        return  button;
    }

    protected TextButton button_create(TextButton button, String task,float height, float width){
        button = new TextButton(task, this.uiskin, "default");
        button.setPosition( width, height);
        button.setSize(200,75);

        return button;
    }

    public void show_more(){

    }

    public void dispose_more(){

    }

    public void render_more(){

    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        this.click = gui.assets.get("select_sound.mp3",Sound.class);

        show_more();

    }

    @Override
    public void render(float delta) {

        gui.batch.begin();
        gui.batch.draw(background,0,0,stage.getWidth(),stage.getHeight());
        gui.batch.end();
        stage.act(delta);
        stage.draw();

        render_more();

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
        background.dispose();
        uiskin.dispose();
        click.dispose();
        stage.dispose();
        uiskin.dispose();
        dispose_more();

    }
}

