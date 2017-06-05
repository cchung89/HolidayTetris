/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import com.mygdx.game.block.Block;
import com.mygdx.game.block.LShape;
import com.mygdx.game.block.LShape2;
import com.mygdx.game.block.Shape;
import com.mygdx.game.block.Square;
import com.mygdx.game.block.TShape;
import com.mygdx.game.block.Thin;

/**
 *
 * @author Brenan
 */
public class TetrisMap {
    
    public char[][] map; // blocks are represented by chars
    private int count;
    private Shape current; // ADDED A CONTROLLED SHAPE THING
    boolean gameover = false;
    private String direction;
    private InputHandler input;
    private int interval;
    int score;
    int goal;
    
    public TetrisMap(InputHandler input) {
        goal = 4;
        score = 0;
        this.input = input;
        this.interval = 25;
        map = new char[10][20]; // (x, y)
        count = 0; 
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                map[i][j] = '-';
            }
        }
        addShape();
    }
    
    public void update() {
        if(!gameover) {
            if(count > 25) {
                for(int i = 0; i < map.length; i++) {
                    for(int j = 0; j < map[i].length; j++) {
                        System.out.print(map[i][j]);
                    }
                    System.out.println();
                }
                System.out.print("\n\n");
            }
            moveBlocks(); // >>>>>>>>>>>>>>>>
            rotateBlocks(); // >>>>>>>>>>>>>>>>
            if(count > interval) {
                // dropBlocks();
                dropShape();
                count = 0;
            }
            count++;
            if(input.down.down && current.getB1Coordinates()[1] < 17) {
                dropShape();
            }
        }
        /*for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.print("\n\n");
        if(count > 50) {
            // dropBlocks();
            dropShape();
            count = 0;
        }
        count++;*/
    }
    
    public void addSquare() {
        int middle = map.length / 2 - 1;
        int top = map[0].length - 1;
        Block b1 = new Block(middle, top);
        Block b2 = new Block(middle + 1, top);
        Block b3 = new Block(middle, top - 1);
        Block b4 = new Block(middle + 1, top - 1);
        Shape square = new Square(input, b1, b2, b3,b4);
        current = square;
    }
    
    public void addLShape() {
        int middle = map.length / 2 - 1;
        int top = map[0].length - 1;
        Block b1 = new Block(middle, top - 1);
        Block b2 = new Block(middle, top);
        Block b3 = new Block(middle + 1, top);
        Block b4 = new Block(middle + 2, top);
        Shape L = new LShape(input, b1, b2, b3, b4);
        current = L;
    }
    
    public void addLShape2() {
        int middle = map.length / 2 - 1;
        int top = map[0].length - 1;
        Block b1 = new Block(middle, top - 1);
        Block b2 = new Block(middle, top);
        Block b3 = new Block(middle + 1, top - 1);
        Block b4 = new Block(middle + 2, top - 1);
        Shape L2 = new LShape2(input, b1, b2, b3, b4);
        current = L2;
    }
    
    public void addThin() {
        int middle = map.length / 2 - 1;
        int top = map[0].length - 1;
        Block b1 = new Block(middle - 1, top);
        Block b2 = new Block(middle, top);
        Block b3 = new Block(middle + 1, top);
        Block b4 = new Block(middle + 2, top);
        Shape thin = new Thin(input, b1, b2, b3, b4);
        current = thin;
    }
    
    public void addTShape() {
        int middle = map.length / 2 - 1;
        int top = map[0].length - 1;
        Block b1 = new Block(middle - 1, top);
        Block b2 = new Block(middle, top);
        Block b3 = new Block(middle + 1, top);
        Block b4 = new Block(middle, top - 1);
        Shape T = new TShape(input, b1, b2, b3, b4);
        current = T;
    }
    
    public void addShape() {
        char type;
        int random = (int) (Math.random() * 125);
        if (random < 25) {
            addSquare();
            type = 's';
        }
        else if (random < 50) {
            addThin();
            type = 'b'; // b for BAR
        }
        else if (random < 75) {
            addTShape();
            type = 't';
        }
        else if (random < 100) {
            addLShape2();
            type = 'l';
        }
        else {
            addLShape();
            type = 'l';
        }
        if (map[current.getBlockOne().getX()][current.getBlockOne().getY()] != '-' ||
            map[current.getBlockTwo().getX()][current.getBlockTwo().getY()] != '-' ||
            map[current.getBlockThree().getX()][current.getBlockThree().getY()] != '-' ||
            map[current.getBlockFour().getX()][current.getBlockFour().getY()] != '-') {
            gameover = true;
            System.out.println("GAME OVER!!!");
        }
        else {
            map[current.getBlockOne().getX()][current.getBlockOne().getY()] = type;
            map[current.getBlockTwo().getX()][current.getBlockTwo().getY()] = type;
            map[current.getBlockThree().getX()][current.getBlockThree().getY()] = type;
            map[current.getBlockFour().getX()][current.getBlockFour().getY()] = type;
        }
    }
    
    /*public void addSquareBlock() {
        int middle = map.length / 2 - 1;
        int top = map[0].length - 1;
        map[middle][top] = 'o';
        map[middle][top - 1] = 'o';
        map[middle + 1][top] = 'o';
        map[middle + 1][top - 1] = 'o';
    }*/
    
    private boolean movable(Shape shape) {
        boolean movable = true;
        /*if (shape.getBlockOne().getX() < 0 ||
                shape.getBlockTwo().getX() < 0 ||
                shape.getBlockThree().getX() < 0 ||
                shape.getBlockFour().getX() < 0)
            movable = false;
        else if (shape.getBlockOne().getX() > map[0].length - 1 ||
                shape.getBlockTwo().getX() > map[0].length - 1 ||
                shape.getBlockThree().getX() > map[0].length - 1 ||
                shape.getBlockFour().getX() > map[0].length - 1) 
            movable = false;
        else if (shape.getBlockOne().getY() < 0 ||
                shape.getBlockTwo().getY() < 0 ||
                shape.getBlockThree().getY() < 0 ||
                shape.getBlockFour().getY() < 0) 
            movable = false;
        else if (map[shape.getBlockOne().getX()][shape.getBlockOne().getY()] == 'o' ||
                map[shape.getBlockTwo().getX()][shape.getBlockTwo().getY()] == 'o' ||
                map[shape.getBlockThree().getX()][shape.getBlockThree().getY()] == 'o' ||
                map[shape.getBlockFour().getX()][shape.getBlockFour().getY()] == 'o')
            movable = false;*/
        
        if (withinbound(shape)) {
            try {
            if (map[shape.getBlockOne().getX()][shape.getBlockOne().getY()] != '-' || // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                map[shape.getBlockTwo().getX()][shape.getBlockTwo().getY()] != '-' ||
                map[shape.getBlockThree().getX()][shape.getBlockThree().getY()] != '-' ||
                map[shape.getBlockFour().getX()][shape.getBlockFour().getY()] != '-')
                return false;
                }
            catch (ArrayIndexOutOfBoundsException e) {
                movable = false;
            }
        }
        else
            movable = false;
        return movable;
    }
    
    private boolean withinbound(Shape shape) {
        boolean movable = true;
        if (shape.getBlockOne().getX() < 0 ||
                shape.getBlockTwo().getX() < 0 ||
                shape.getBlockThree().getX() < 0 ||
                shape.getBlockFour().getX() < 0)
            movable = false;
        if (shape.getBlockOne().getX() > map[0].length - 1 ||
                shape.getBlockTwo().getX() > map[0].length - 1 ||
                shape.getBlockThree().getX() > map[0].length - 1 ||
                shape.getBlockFour().getX() > map[0].length - 1) 
            movable = false;
        if (shape.getBlockOne().getY() < 0 ||
                shape.getBlockTwo().getY() < 0 ||
                shape.getBlockThree().getY() < 0 ||
                shape.getBlockFour().getY() < 0) 
            movable = false;
        return movable;
    }
    
    public void dropShape() { // NEW METHOD
        // the coordinates of the current shape
        char type;
        
        int[][] prevCoordinates = new int[4][2];
        prevCoordinates[0] = current.getB1Coordinates();
        prevCoordinates[1] = current.getB2Coordinates();
        prevCoordinates[2] = current.getB3Coordinates();
        prevCoordinates[3] = current.getB4Coordinates();
        
        int x = prevCoordinates[0][0];
        int y = prevCoordinates[0][1];
        
        type = map[x][y];
        
        // prev coordinates are set to empty
        for(int i = 0; i < prevCoordinates.length; i++) {
            int xCoord = prevCoordinates[i][0];
            int yCoord = prevCoordinates[i][1];
            map[xCoord][yCoord] = '-';
        }
        
        int[][] nextCoordinates = new int[4][2];
        current.moveDown();
        nextCoordinates[0] = current.getB1Coordinates();
        nextCoordinates[1] = current.getB2Coordinates();
        nextCoordinates[2] = current.getB3Coordinates();
        nextCoordinates[3] = current.getB4Coordinates();
        
        boolean move = true;
        
        for(int i = 0; i < nextCoordinates.length; i++) {
            int xCoord = nextCoordinates[i][0];
            int yCoord = nextCoordinates[i][1];
            if(yCoord < 0) {
                move = false;
                break;
            }
            if(map[xCoord][yCoord] != '-') {
                move = false;
                break;
            }
        }
        
        if(move) {
            for(int i = 0; i < nextCoordinates.length; i++) {
                int xCoord = nextCoordinates[i][0];
                int yCoord = nextCoordinates[i][1];
                map[xCoord][yCoord] = type;
            }
        } else {
            for(int i = 0; i < prevCoordinates.length; i++) {
                int xCoord = prevCoordinates[i][0];
                int yCoord = prevCoordinates[i][1];
                map[xCoord][yCoord] = type;
            }
            // current = new Square(5, 19);
            current = null;
            
            deleteCompleteRows();
            
            addShape();
            // check for row
        }
        
    }
    
    public void deleteCompleteRows() {
        int deletedRowCount = 0;
        char[] temp = new char[10];
        for(int j = map[0].length - 1; j >= 0; j--) {
            for(int i = map.length - 1; i >= 0; i--) {
                temp[i] = map[i][j];
            }
            if(rowComplete(temp)) {
                deleteRow(j);
                deletedRowCount++;
            }
        }
        if(deletedRowCount == goal) {
            score++;
            goal++;
            if(goal > 4) {
                goal = 1;
            }
        } else if(deletedRowCount != 0) {
            score--;
            if(score < -9) {
                gameover = true;
            }
        }
    }
    
    public boolean rowComplete(char[] row) {
        for(int i = 0; i < row.length; i++) {
            if(row[i] == '-')
                return false;
        }
        return true;
    }
    
    public void deleteRow(int num) {
        for(int i = 0; i < map.length; i++) {
            map[i][num] = '-';
        }
        for(int j = num + 1; j < map[0].length; j++) {
            for(int i = 0; i < map.length; i++) {
                map[i][j - 1] = map[i][j];
                map[i][j] = '-';
            }
        }
    }
    
    public void moveBlocks() {
        if (current != null) {
            char type = map[current.getBlockOne().getX()][current.getBlockOne().getY()];
            
            map[current.getBlockOne().getX()][current.getBlockOne().getY()] = '-';
            map[current.getBlockTwo().getX()][current.getBlockTwo().getY()] = '-';
            map[current.getBlockThree().getX()][current.getBlockThree().getY()] = '-';
            map[current.getBlockFour().getX()][current.getBlockFour().getY()] = '-';
            Shape temp = current;
            direction = temp.move();
            if (movable(temp))
                current = temp;
            else {
                if (direction.equals("LEFT"))
                    current.moveRight();
                else if (direction.equals("RIGHT"))
                    current.moveLeft();
                else if (direction.equals("DOWN"))
                    current.moveUp();
            }
            
            map[current.getBlockOne().getX()][current.getBlockOne().getY()] = type;
            map[current.getBlockTwo().getX()][current.getBlockTwo().getY()] = type;
            map[current.getBlockThree().getX()][current.getBlockThree().getY()] = type;
            map[current.getBlockFour().getX()][current.getBlockFour().getY()] = type;
        }
    }
    
    public void dropBlocks() { // drops blocks
        char temp;
        for(int i = 0; i < map.length; i++) { // CHANGED THE DROP METHOD A BIT
            for(int j = 0; j < map[i].length; j++) {
                /* when an 'o' encounters the bottom of the map or ends up on
                top of an 'x', then the 'o' becomes an 'x'
                */
                if(j == 0) {
                    if(map[i][j] == 'o') {
                        map[i][j] = 'x';
                    }
                } else if(map[i][j] == 'o' && map[i][j - 1] == '-') {
                    temp = map[i][j];
                    map[i][j] = '-';
                    map[i][j - 1] = temp;
                } else if(map[i][j] == 'o' && map[i][j - 1] == 'x') {
                    map[i][j] = 'x';
                }
            }
        }
    }
    
    public void rotateBlocks() {
        if (current != null) {
            char type = map[current.getBlockOne().getX()][current.getBlockOne().getY()];
            
            map[current.getBlockOne().getX()][current.getBlockOne().getY()] = '-';
            map[current.getBlockTwo().getX()][current.getBlockTwo().getY()] = '-';
            map[current.getBlockThree().getX()][current.getBlockThree().getY()] = '-';
            map[current.getBlockFour().getX()][current.getBlockFour().getY()] = '-';
            Shape temp = current;
            
            temp.rotate();
            if (movable(temp))
                current = temp;
            else {
                current.rotation();
                current.rotation();
                current.rotation();
            }
            
            map[current.getBlockOne().getX()][current.getBlockOne().getY()] = type;
            map[current.getBlockTwo().getX()][current.getBlockTwo().getY()] = type;
            map[current.getBlockThree().getX()][current.getBlockThree().getY()] = type;
            map[current.getBlockFour().getX()][current.getBlockFour().getY()] = type;
        }
    }
    
    public char[][] getMap() { // this is the connection to the outside world
        return map;
    }
}
