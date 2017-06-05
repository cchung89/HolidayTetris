/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import java.util.ArrayList;

/**
 *
 * @author Brenan
 */
public class InputHandler extends InputAdapter {
    
    public class Key {
        public int presses;
        public int absorbs;
        public boolean down;
        public boolean clicked;
        
        public Key() {
            keys.add(this);
        }
        
        public void toggle(boolean pressed) {
            // Toggle key between pressed/non-pressed
            // based on received key event
            if (pressed != down) {
                down = pressed;
            }
            if (pressed) {
                presses ++;
            }
        }
        
        public void tick() {
            // Absorbs allows for clicked to only register once per key press
            if (absorbs < presses) {
                absorbs ++;
                clicked = true;
            } else {
                clicked = false;
            }
        }        
    }
    
    public ArrayList<Key> keys = new ArrayList<Key>();
        
    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    
    public Key space = new Key();
    
    public Key z = new Key();
    public Key x = new Key();
    public Key e = new Key();
    public Key r = new Key();
    
    public Key esc = new Key();
    
    public Key m = new Key();
    
    // Should be called when game loses focus
    public void releaseAll() {
        for (int i = 0; i < keys.size(); i ++) {
            keys.get(i).down = false;
        }
    }
    
    public void tick() {
        for (int i = 0; i < keys.size(); i ++) {
            keys.get(i).tick();
        }
    }

    // Gets key event when key is lifted
    public boolean keyUp(int keyCode) {
        toggle(keyCode, false);
        return false;
    }

    // Gets key event when key is pressed
    public boolean keyDown(int keyCode) {
        toggle(keyCode, true);
        return true;
    }
    
    // Toggle appropriate key based on key event
    public void toggle(int keyCode, boolean pressed) {
        if (keyCode == Input.Keys.UP) up.toggle(pressed);
        if (keyCode == Input.Keys.DOWN) down.toggle(pressed);
        if (keyCode == Input.Keys.LEFT) left.toggle(pressed);
        if (keyCode == Input.Keys.RIGHT) right.toggle(pressed);
       
        if (keyCode == Input.Keys.W) up.toggle(pressed);
        if (keyCode == Input.Keys.S) down.toggle(pressed);
        if (keyCode == Input.Keys.A) left.toggle(pressed);
        if (keyCode == Input.Keys.D) right.toggle(pressed);
        
        if (keyCode == Input.Keys.SPACE) space.toggle(pressed);
        
        if (keyCode == Input.Keys.Z) z.toggle(pressed);
        if (keyCode == Input.Keys.X) x.toggle(pressed);
        if (keyCode == Input.Keys.E) e.toggle(pressed);
        if (keyCode == Input.Keys.R) r.toggle(pressed);
        
        if (keyCode == Input.Keys.ESCAPE) esc.toggle(pressed);
        
        if (keyCode == Input.Keys.M) m.toggle(pressed);
    }
}
