package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HolidayTetris extends ApplicationAdapter { // tile size is 24 * 24
                                                        // (x, y)
	SpriteBatch batch;
	Texture img;
        
        TetrisMap map;
        
        Pixels pixel;
        
        private InputHandler input;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("CandyCane.PNG");
                this.input = new InputHandler();
                Gdx.input.setInputProcessor(input);
                map = new TetrisMap(input);
                pixel = new Pixels(this.batch);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		// batch.draw(img, 0, 0, 24, 48);
                map.update();
                pixel.draw(map.getMap(), map.score, map.goal, map.gameover);
		batch.end();
	}
}
