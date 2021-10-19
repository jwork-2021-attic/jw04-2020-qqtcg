package com.anish.calabashbros;

import asciiPanel.AsciiPanel;

public class Sign extends Thing{
    Sign(World world){
        super(AsciiPanel.yellow, (char) 15, world);
    }
}
