package com.anish.screen;

import asciiPanel.AsciiPanel;


public class EndScreen extends RestartScreen{

    public void displayOutput(AsciiPanel terminal){
        terminal.write("Congratulations!", 0, 0);
        terminal.write("Press Enter to start again", 0, 1);
    }

    
    
}
