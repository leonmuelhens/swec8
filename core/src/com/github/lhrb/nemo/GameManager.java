/**
 * 
 */
package com.github.lhrb.nemo;

import com.github.lhrb.nemo.actors.Player;

/**
 * @author exa
 * 
 *  Manages the score and maybe more in future
 */
public class GameManager {
    
    private Player player; //needs to change for multiplayer
    
    private static GameManager gameMng;
    
    private GameManager() {
        
    }
    
    public static GameManager get() {
        if(gameMng == null) {
            gameMng = new GameManager();
        }
        return gameMng;
    }
    
    public void registerPlayer(Player player) {
        this.player = player;
    }
              
    

    /**
     * increase score by i
     * @param i
     */
    public void addScore(int p) {
        player.addScore(p);
    }

    

    
}
