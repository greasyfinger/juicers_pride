package com.juicerspride.game.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class tank extends Body implements Serializable {
    private String color;
    private Texture tex;
    private int hp;
    private ArrayList<bullets> projectiles = new ArrayList<>();
    private BodyDef bdef;
    int ID;
    tank(World world, long address, int ID){
        super(world, address);

    }

    public void saveGame(){

    }

    public boolean isHit(){
        boolean bool = true;

        return bool;
    }

    private void updateHP(boolean isHit){

    }

    public void shoot(){

    }

}

