/**
 * 
 */
package com.github.lhrb.nemo.actors.enemies;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.PhysicalActor;

/**
 * @author exa
 *
 */
public abstract class Enemy extends PhysicalActor{
    
    public Enemy(){
        super();
        setCharacteristics();
    }
    
    public Enemy(float x, float y, Stage stage) {
        super(x,y,stage);
        setCharacteristics();
    }
    
    int hp;
    
    protected abstract void setCharacteristics();

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.actors.PhysicalActor#collision()
     */
    @Override
    public void collision() {
        hp -= 1;
        if(hp <= 0) {
            System.out.println("destroyed");
        }
    }
    
    
    
    
}
