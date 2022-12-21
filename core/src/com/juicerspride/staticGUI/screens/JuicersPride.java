package com.juicerspride.staticGUI.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.juicerspride.game.utils.NoLoadFileFoundException;
import com.juicerspride.game.utils.TiledObjectUtil;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.juicerspride.staticGUI.staticGUI;

import java.io.IOException;

import java.io.Serializable;

import static com.juicerspride.game.utils.Constants.PPM;
public class JuicersPride extends ApplicationAdapter implements Serializable, Screen {
//	SpriteBatch batch;
	Texture img;

	private boolean DEBUG = false;
	private final float SCALE = 2.0f;

	private static int game_id = 0;
	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer tmr;
	private TiledMap map;
	private Box2DDebugRenderer b2dr;
	private World world;
	private Body player1, platform;
	private SpriteBatch batch;
	private Texture tex;

	private int id_game;
	private final staticGUI gui;
	private Stage stage;
	private TextButton menu;
	private Skin uiskin;
	private Music ingame_music;
	private Screen popup;
	private Sound click;

	public JuicersPride(staticGUI gui) {

		this.gui = gui;
		stage = new Stage();
		popup = new popup(gui, this);
		this.id_game = game_id;
		game_id +=1;
		create();
	}
	
	@Override
	public void create (){
//		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false,w/SCALE,h/SCALE);

		world = new World(new Vector2(0,-9.8f), false);
		b2dr = new Box2DDebugRenderer();

		player1 = createBox(155,155,32,48,false);
		platform = createBox(145,65,64,32,true);

		batch = new SpriteBatch();
		tex = new Texture("Mark_I copy.png");

		map = new TmxMapLoader().load("tex_map3.tmx");
		tmr = new OrthogonalTiledMapRenderer(map);

		TiledObjectUtil.parseTiledObject(world, map.getLayers().get("collision-layer").getObjects());

	}

	@Override
	public void render () {
		update(Gdx.graphics.getDeltaTime());
		ScreenUtils.clear(1, 0, 0, 1);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
		Gdx.gl.glClearColor(0f,0f,0f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		tmr.render();

		batch.begin();
		batch.draw(tex, player1.getPosition().x * PPM - tex.getWidth()/2, player1.getPosition().y * PPM - tex.getHeight()/2);
		batch.end();
		b2dr.render(world, camera.combined.scl(PPM));
	}


	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		ingame_music = gui.assets.get("ingame_music.mp3",Music.class);
		click = gui.assets.get("select_sound.mp3",Sound.class);
		this.uiskin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"),
				gui.assets.<TextureAtlas>get("skin/craftacular-ui.atlas",TextureAtlas.class));
		ingame_music.play();
		this.menu = new TextButton("MENU",uiskin);
		this.menu.setPosition( 100 ,stage.getHeight() - 100);
		this.menu.setSize(150,75);
		menu.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				click.play();
				ingame_music.pause();
				gui.setScreen(popup);
			}
		});
		stage.addActor(this.menu);


	}

	@Override
	public void render(float delta) {
		stage.act(delta);
		render();
		stage.draw();
	}

	@Override
	public void resize(int height, int width){
		camera.setToOrtho(false, width/SCALE, height/SCALE);
	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose () {
//		batch.dispose();
//		img.dispose();
		world.dispose();
		b2dr.dispose();
		batch.dispose();
		tmr.dispose();
		map.dispose();
		click.dispose();
		stage.dispose();
		ingame_music.dispose();
	}

	public void update(float delta){
		world.step(1/60f, 6, 2);

		inputUpdate(delta);

		cameraUpdate(delta);

		tmr.setView(camera);
		batch.setProjectionMatrix(camera.combined);

	}

	private void inputUpdate(float delta){
		int horizontalForce = 0;

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			horizontalForce -= 1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			horizontalForce += 1;
		}
//		if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
//			player1.applyForceToCenter(0, 300, false);
////			code for projectile motion; remember to have a separate updation as this sends y - velocity to 0 every frame
////			Vector2 startingVelocity =new Vector2(10,10);
////			startingVelocity.rotateDeg((float) 60 - 45);
////
////			player1.setLinearVelocity(startingVelocity);
//		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			player1.applyForceToCenter(0, -300, false);
		}

		player1.setLinearVelocity(horizontalForce * 5, player1.getLinearVelocity().y);

	}
	private void cameraUpdate(float delta){
		Vector3 position = camera.position;
		position.x = player1.getPosition().x * PPM; //when you get stuff, multiply; setting stuff divide
		position.y = 5 * PPM;

		camera.position.set(position);
		camera.update();

	}
	public Body createBox(int x, int y, int width, int height, boolean isStatic){
		Body pbody;
		BodyDef def = new BodyDef();
		if (isStatic) def.type = BodyDef.BodyType.StaticBody;
		else def.type = BodyDef.BodyType.DynamicBody;//check what works for us
		def.position.set(x/PPM,y/PPM);
		def.fixedRotation = false;

		pbody = world.createBody(def);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width/2/PPM, height/2/PPM);

		pbody.createFixture(shape, 10.0f);
		shape.dispose();

		return pbody;
	}

	void loadGame() throws IOException, NoLoadFileFoundException {

	}

	void newgame() throws ClassNotFoundException, ClassCastException{

	}




}