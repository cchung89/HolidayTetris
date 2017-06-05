/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.block;

import com.mygdx.game.InputHandler;

/**
 *
 * @author Brenan and chin
 */
public abstract class Shape {
    private Block b_1;
    private Block b_2;
    private Block b_3;
    private Block b_4;
    
    private boolean moveable;
    
    protected int count;
    
    private String direction;
    
    private InputHandler input;
    
    public Shape(InputHandler input, Block b_1, Block b_2, Block b_3, Block b_4) {
        this.input = input;
        
        this.b_1 = b_1;
        this.b_2 = b_2;
        this.b_3 = b_3;
        this.b_4 = b_4;
        
        moveable = true;
        
        this.count = 0;
    }
    
    public String move() {
        direction = "None";
        if (this.count > 10) {
            if (input.left.down) {
                moveLeft();
                count = 0;
                direction = "LEFT";
            }
            if (input.right.down) {
                moveRight();
                count = 0;
                direction = "RIGHT";
            }
            if (input.down.down) {
                moveDown();
                count = 0;
                direction = "DOWN";
            }
        }
        this.count++;
        return direction;
    }
    
    public void unmoveable() {
        moveable = false;
    }
    
    public void moveLeft() {
        b_1.moveLeft();
        b_2.moveLeft();
        b_3.moveLeft();
        b_4.moveLeft();
    }
    
    public void moveRight() {
        b_1.moveRight();
        b_2.moveRight();
        b_3.moveRight();
        b_4.moveRight();
    }
    
    public void moveUp() {
        b_1.moveUp();
        b_2.moveUp();
        b_3.moveUp();
        b_4.moveUp();
    }
    
    public void moveDown() {
        b_1.moveDown();
        b_2.moveDown();
        b_3.moveDown();
        b_4.moveDown();
    }
    
    public int[] getB1Coordinates() { // ADDED SOME NEW METHODS
        return new int[] {b_1.getX(), b_1.getY()};
    }
    
    public int[] getB2Coordinates() {
        return new int[] {b_2.getX(), b_2.getY()};
    }
    
    public int[] getB3Coordinates() {
        return new int[] {b_3.getX(), b_3.getY()};
    }
    
    public int[] getB4Coordinates() {
        return new int[] {b_4.getX(), b_4.getY()};
    }
    
    public Block getBlockOne() {
        return this.b_1;
    }
    
    public Block getBlockTwo() {
        return this.b_2;
    }
    
    public Block getBlockThree() {
        return this.b_3;
    }
    
    public Block getBlockFour() {
        return this.b_4;
    }
    
    public void setBlockOne(int dx, int dy) {
        this.b_1.setX(dx);
        this.b_1.setY(dy);
    }
    
    public void setBlockTwo(int dx, int dy) {
        this.b_2.setX(dx);
        this.b_2.setY(dy);
    }
    
    public void setBlockThree(int dx, int dy) {
        this.b_3.setX(dx);
        this.b_3.setY(dy);
    }
    
    public void setBlockFour(int dx, int dy) {
        this.b_4.setX(dx);
        this.b_4.setY(dy);
    }
    
    public boolean equal(Shape other) {
        return this.b_1.equal(other.getBlockOne()) &&
               this.b_2.equal(other.getBlockTwo()) &&
               this.b_3.equal(other.getBlockThree()) &&
               this.b_4.equal(other.getBlockFour());
    }
    
    public boolean overlap(Shape other) {
        return this.b_1.equal(other.getBlockOne()) ||
               this.b_2.equal(other.getBlockTwo()) ||
               this.b_3.equal(other.getBlockThree()) ||
               this.b_4.equal(other.getBlockFour());
    }
    
    public boolean moveable() {
        return moveable;
    }
    
    public void immobilize() {
        this.moveable = false;
    }
    
    public InputHandler getInput() {
        return this.input;
    }
    
    public int getCount() {
        return this.count;
    }
    
    public abstract void rotate();
    
    public abstract void rotation();

}
