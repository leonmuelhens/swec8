/**
 * 
 */
package com.github.lhrb.nemo.screen;

import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.util.CollisionManager;
import com.github.lhrb.nemo.actors.enemies.Kraken;
import com.github.lhrb.nemo.util.SoundManager;

/**
 * @author exa
 *
 */
public class TestScreen extends AbstractScreen {
    
    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#init()
     */
    @Override
    public void init() {
        SoundManager.getInstance().playTrack("boss");
        
        Kraken krake = new Kraken(300,600, gameStage);
        
        Player player = new Player(200,50, gameStage);

    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#update(float)
     */
    @Override
    public void update(float delta) {
        CollisionManager.checkCollision(getPhysicalActors());
        // TODO Auto-generated method stub
    }
}
