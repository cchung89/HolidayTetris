/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.block;

import com.mygdx.game.InputHandler;
/**
 *
 * @author Brenan
 */
public class Square extends Shape {

    /*private Block top_left; // top left
    private Block top_right;
    private Block bot_left;
    private Block bot_right; // REMOVED THESE INSTANCE VARIABLES*/
    
    /**
     * TWO CONSTRUCTORS
     * TWO CONSTRUCTORS
     * 
     * @param x
     * @param y
     */
    /*public Square(int x, int y) {
        super(InputHandler input, new Block(x, y), new Block(x + 1, y), 
                new Block(x, y -1),
                new Block(x + 1, y - 1));*/
        /**
         * b_1 is top left
         * b_2 is top right
         * b_3 is bot_left
         * b_4 is bot_right
         */
    // }
    
    public Square(InputHandler input, Block top_left, Block top_right, Block bot_left,
            Block bot_right) {
        super(input, top_left, top_right, bot_left, bot_right);
    }
    
    @Override
    public void rotate() {}
    
    public void rotation() {}
    
}
