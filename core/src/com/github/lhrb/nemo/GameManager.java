/**
 * 
 */
package com.github.lhrb.nemo;



/**
 * @author exa
 * 
 *  Manages the score and maybe more in future
 */
public class GameManager {
    
    private static GameManager gameMng;
    
    private int score;
        
    private GameManager() {
        score = 0;
    }
    
    public static GameManager getInstance() {
        if(gameMng == null) {
            gameMng = new GameManager();
        }
        return gameMng;
    }
    
    public int getScore() {
        return score;
    }
    
    /**
     * increase score by 1
     */
    public synchronized void addScore() {
        addScore(1);
    }

    /**
     * increase score by i
     * @param i
     */
    public synchronized void addScore(int i) {
        score += i;
    }
    
    public synchronized void resetScore() {
        score = 0;
    }
    

    
}
