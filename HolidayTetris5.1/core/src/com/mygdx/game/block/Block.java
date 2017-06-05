/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.block;

/**
 *
 * @author Brenan
 */
public class Block {
    
    private int x, y;
    
    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void moveLeft() {
        x = x - 1;
    }
    
    public void moveRight() {
        x = x + 1;
    }
    
    public void moveDown() {
        y = y - 1;
    }
    
    public void moveUp() {
        y = y + 1;
    }
    
    public void setX(int dx) {
        this.x += dx;
    }
    
    public void setY(int dy) {
        this.y += dy;
    }
    
    public void setPosition(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    
    public boolean equal(Block other) {
        return this.x == other.x && this.y == other.y;
    }
    
    public Block self() {
        return this;
    }

}
