package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;


import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.FloorPased;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Calabash bro;
    private int[][] map;

    public WorldScreen() {
        world = new World();
        map = world.getMap();
        bro = new Calabash(AsciiPanel.brightWhite, 1, world);
        world.put(bro, 1, 1);

       

        
    }

    

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        int left = bro.getX()-1;
        int right = bro.getX()+1;
        int up = bro.getY()-1;
        int down = bro.getY()+1;
        int nowX = bro.getX();
        int nowY = bro.getY();


        switch (key.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (map[left][nowY] != 0){
                    bro.moveTo(left, nowY);
                    this.world.put(new FloorPased(this.world, (char)27), nowX, nowY);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (map[right][nowY] != 0){
                    bro.moveTo(right, nowY);
                    this.world.put(new FloorPased(this.world, (char)26), nowX, nowY);
                }
                break;
            case KeyEvent.VK_UP:
                if (map[nowX][up] != 0){
                    bro.moveTo(nowX, up);
                    this.world.put(new FloorPased(this.world, (char)24), nowX, nowY);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (map[nowX][down] != 0){
                    bro.moveTo(nowX, down);
                    this.world.put(new FloorPased(this.world, (char)25), nowX, nowY);
                }
                break;
        }

        if (bro.getX() == 40 && bro.getY() == 40){
            return new EndScreen();
        }
        return this;
       
    }

}
