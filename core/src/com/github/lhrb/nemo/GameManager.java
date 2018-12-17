/**
 * 
 */
package com.github.lhrb.nemo;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.github.lhrb.nemo.actors.EnemyActor;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.actors.Removable;
import com.github.lhrb.nemo.actors.shots.Shots;

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
        System.out.println("Actor count: " + KillingNemo.getGameStage().getActors().size);
        for (Actor a : KillingNemo.getGameStage().getActors()) {
            if(a instanceof Removable) {
                System.out.print("+ ");
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
