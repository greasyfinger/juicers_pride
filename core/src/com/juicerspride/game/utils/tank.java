package com.juicerspride.game.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

import static com.juicerspride.game.utils.Constants.PPM;

public class tank{
    Body player;
    public CollisionRect rect;
    public int health = 100;

    public tank(Body player, Texture tex){
        this.player = player;
        rect = new CollisionRect(player.getPosition().x * PPM - tex.getWidth()/2, player.getPosition().y * PPM - tex.getHeight()/2, tex.getHeight(), tex.getWidth());
    }


}

