package com.juicerspride.game.utils;

public class CollisionRect {
    float x, y;
    int height, width;

    public CollisionRect(float x, float y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public void move(float x, float y){
        this.x = x;
        this.y = y;
    }

    public boolean collidesWith(CollisionRect col2){
        return x < col2.x + col2.width && y < col2.y + col2.height && x + width > col2.x && y + height > col2.y;
    }


}
