package com.juicerspride.game.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class bullets {
    private int damage;
    private static Texture texture;
    public boolean remove = false;
    public Vector2 position = new Vector2();
    public Vector2 direction = new Vector2();

    Body player;
    public CollisionRect rect;

    public bullets(Body player, Vector2 pos, Vector2 dir){
        this.player = player;
        this.position.set(pos);
        this.direction.set(dir);

        rect = new CollisionRect(pos.x, pos.y, 8,8);//8,8 bullet image specific

        if (texture == null){
            texture = new Texture("bullet.png");
        }
    }

    public void update(float delta){
        float speed = 100.0f;
        this.position.add(direction.x * delta * speed,  direction.y*delta*speed);
        this.direction.sub(0, 0.98f);

        rect.move(position.x, position.y);
//        this.position.

    }
    public void render(SpriteBatch sprite){
        sprite.draw(texture, this.position.x, this.position.y);
    }

    public boolean didHit(tank tank){
        return false;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}

//class bigone extends bullets{
//    public bigone(Vector2 pos, Vector2 dir) {
//        super(pos, dir);
//    }
//
//    public void hitTank(tank Tank){
//
//    }
//}

//class rain extends  bullets{
//
//
//    public rain(Vector2 pos, Vector2 dir) {
//        super(pos, dir);
//    }
//
//    public void hitTank(tank Tank){
//
//    }
//}