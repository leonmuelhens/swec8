/**
 * 
 */
package com.github.lhrb.nemo.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * @author exa
 *
 */
public class EnemyActor extends PhysicalActor{
    
    protected int hp;
    protected int scoreValue;

    public EnemyActor() {
        super();
    }
    
    public EnemyActor(float x, float y, Stage stage) {
        super(x, y, stage);
    }

}
