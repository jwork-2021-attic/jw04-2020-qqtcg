package com.anish.calabashbros;

import java.awt.Color;

public class Creature extends Thing {

    Creature(Color color, char glyph, World world) {
        super(color, glyph, world);
    }

    //加入将走过的位置还原为floorPased
    public void moveTo(int xPos, int yPos) {
        
        this.world.put(this, xPos, yPos);
        
    }

    

}
