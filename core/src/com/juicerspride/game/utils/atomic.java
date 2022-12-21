package com.juicerspride.game.utils;

import com.badlogic.gdx.physics.box2d.World;

public class atomic extends tank {
    private int cost;
    private String name;

    atomic(World world, long address, int ID, String name, int cost) {
        super(world, address, ID);
        this.setName(name);
        this.setCost(cost);
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
