/**
 * 
 */
package com.github.lhrb.nemo;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
    private float timeSinceESC = 0;
    
    private GameManager() {
        
    }

    public float getTimeSinceESC() {
        return timeSinceESC;
    }

    public void setTimeSinceESC(float timeSinceESC) {
        this.timeSinceESC = timeSinceESC;
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
    public void removeEnemiesAndShots(Stage stage) {
        if(stage == null) return;
        for (Actor a : stage.getActors()) {
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
    
    public float getPlayerX() {
        if(player == null) return 0;
        return player.getX();
    }

    

    
}
