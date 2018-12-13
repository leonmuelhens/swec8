/**
 * 
 */
package com.github.lhrb.nemo;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.github.lhrb.nemo.actors.enemies.Enemy;
import com.github.lhrb.nemo.actors.shots.Shots;

/**
 * @author exa
 * 
 *  Manages the score and maybe more in future
 */
public class GameManager {
    
    private static GameManager gameMng;
    
    private int score;
    private String scoreTxt;
        
    private GameManager() {
        score = 0;
        scoreToString();
    }
    
    public static GameManager getInstance() {
        if(gameMng == null) {
            gameMng = new GameManager();
        }
        return gameMng;
    }
    
    public Integer getScore() {
        return score;
    }
    
    public String getScoreAsString() {
        return scoreTxt;
    }

    // In future we need to check that no boss will be removed here
    public void removeEnemiesAndShots() {
        for (Actor a : AbstractGame.getGameStage().getActors()) {
            if (a instanceof Shots || a instanceof Enemy) {
                a.remove();
            }
        }
    }
           
    
    /**
     * increase score by 1
     */
    public synchronized void addScore() {
        addScore(1);
        scoreToString();
    }

    /**
     * increase score by i
     * @param i
     */
    public synchronized void addScore(int i) {
        score += i;
        scoreToString();
    }
    
    public synchronized void resetScore() {
        score = 0;
        scoreToString();
    }
    
    private void scoreToString() {
        scoreTxt = String.valueOf(score);
    }
    

    
}
