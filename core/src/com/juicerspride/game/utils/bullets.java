package com.juicerspride.game.utils;

import com.badlogic.gdx.math.Vector2;

abstract class bullets {
    private int damage;
    public Vector2 position = new Vector2();
    public Vector2 direction = new Vector2();

    public bullets(Vector2 pos, Vector2 dir){
        this.position.set(pos);
        this.direction.set(dir);
    }

    public void update(float delta){
        float speed = 16.0f;
        position.add(direction.x * delta * speed, direction.y*delta*speed);

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

class bigone extends bullets{
    public bigone(Vector2 pos, Vector2 dir) {
        super(pos, dir);
    }

    public void hitTank(tank Tank){

    }
}

class rain extends  bullets{


    public rain(Vector2 pos, Vector2 dir) {
        super(pos, dir);
    }

    public void hitTank(tank Tank){

    }
}