/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Brenan
 */
public class Pixels {
    
    SpriteBatch batch;
    
    Texture candyCane;
    Texture pumpkin;
    Texture present;
    Texture turkey;
    
    BitmapFont fonty;
    
    public Pixels(SpriteBatch batch) {
        this.batch = batch;
        candyCane = new Texture("candyCane.png");
        pumpkin = new Texture("pumpkin.png");
        present = new Texture("Present.png");
        turkey = new Texture("Turkey.png");
        
        fonty = new BitmapFont();
    }
    
    public void draw(char[][] map, int score, int goal, boolean gameover) {
        if(!gameover) {
   
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                switch(map[i][j]) {
                    case 's':
                        batch.draw(candyCane, i * 24, j * 24, 24, 24);
                        break;
                    case 'b':
                        batch.draw(pumpkin, i * 24, j * 24, 24, 24);
                        break;
                    case 't':
                        batch.draw(present, i * 24, j * 24, 24, 24);
                        break;
                    case 'l':
                        batch.draw(turkey, i * 24, j * 24, 24, 24);
                        break;
                    default:
                        break;
                }
            }
        }
        fonty.setColor(1, 0, 0, 1);
        String scoreString = "points: " + score;
        String goalString = "goal: " + goal + " in a row";
        fonty.draw(batch, scoreString, 25, 25 * 19);
        fonty.draw(batch, goalString, 25, 25 * 18);
        
        } else {
            fonty.draw(batch, "game over. git gud", 25 * 2, 25 * 14);
        }
    }
}
