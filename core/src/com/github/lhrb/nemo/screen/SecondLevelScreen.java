/**
 * 
 */
package com.github.lhrb.nemo.screen;

import java.beans.PropertyChangeSupport;

import com.github.lhrb.nemo.SpawnFactory.EnemyFactory;
import com.github.lhrb.nemo.actors.Background;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.ui.HUD;

/**
 * @author exa
 *
 */
public class SecondLevelScreen extends LevelScreen {
	
	public SecondLevelScreen() {
		super();
	}
	
	public SecondLevelScreen(Player player, HUD hud) {
		
	}
    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#init()
     */
    @Override
    public void init() {
    	
    	changes = new PropertyChangeSupport(this);
    	factory = new EnemyFactory(gameStage);
    	bg = new Background(0, 0, gameStage, 2);
        bg2 = new Background(0, 1200, gameStage, 2);
    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.LevelScreen#spawnEndboss()
     */
    @Override
    protected void spawnEndboss() {
        // TODO Auto-generated method stub
        
    }

}
