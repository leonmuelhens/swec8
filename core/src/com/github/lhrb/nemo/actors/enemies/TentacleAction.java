/**
 * 
 */
package com.github.lhrb.nemo.actors.enemies;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.actors.Section;

/**
 * @author exa
 *
 */
public class TentacleAction extends Action {
    
    private float elapsedTime, x, y, time;
    private Section s;
    private boolean setX;
    
    public TentacleAction(float time, float y, Section s) {
        super();
        this.time = time;
        this.y = y;
        this.s = s;
        elapsedTime = 0f;
        x = -1f;
        setX = true;
    }
    
    /* (non-Javadoc)
     * @see com.badlogic.gdx.scenes.scene2d.Action#act(float)
     */
    @Override
    public boolean act(float delta) {
        elapsedTime += delta;
        if(setX && elapsedTime >= 0.5f) {
            x = GameManager.get().getPlayerX();
            setX = false;
        }
        if(elapsedTime >= time) {
            if(x == -1f) {
                x = GameManager.get().getPlayerX(); 
            }
            s.setPos(x, y);
            return true;
        }
        return false;
    }

}
