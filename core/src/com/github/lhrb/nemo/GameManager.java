/**
 * 
 */
package com.github.lhrb.nemo;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.actors.Removable;

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
              
    
    // In future we need to check that no boss will be removed here
    public void removeEnemiesAndShots() {
        for (Actor a : AbstractGame.getGameStage().getActors()) {
            if(a instanceof Removable) {
                ((Removable) a).destroy();
            }
        }
    }

    /**
     * increase score by i
     * @param p
     */
    public void addScore(int p) {
        player.addScore(p);
    }

    

    
}
