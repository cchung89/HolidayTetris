/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.block;

import com.mygdx.game.InputHandler;

/**
 *
 * @author chin
 */
public class Thin extends Shape {
    
    private int state;
    
    public Thin(InputHandler input, Block b_1, Block b_2, Block b_3, Block b_4) {
        super(input, b_1, b_2, b_3, b_4);
        this.state = 1;
    }
    
    public void rotation() { // how to implement rotate
        System.out.println(count);
        Block b_1 = getBlockOne();
        Block b_2 = getBlockTwo();
        Block b_3 = getBlockThree();
        Block b_4 = getBlockFour();
        if (state == 1) {
            b_1.setPosition(1, 1);
            b_3.setPosition(-1, -1);
            b_4.setPosition(-2, -2);
            state++;
        } else if (state == 2) {
            b_1.setPosition(1, -1);
            b_3.setPosition(-1, 1);
            b_4.setPosition(-2, 2);
            state++;
        } else if (state == 3) {
            b_1.setPosition(-1, -1);
            b_3.setPosition(1, 1);
            b_4.setPosition(2, 2);
            state++;
        } else if (state == 4) {
            b_1.setPosition(-1, 1);
            b_3.setPosition(1, -1);
            b_4.setPosition(2, -2);
            state = 1;
        }
    }
    
    public void rotate() {
        if (count > 24) {
            if (getInput().up.down) {
                rotation();
                count = 0;
            }
        }
        count++;
    }
    
}
