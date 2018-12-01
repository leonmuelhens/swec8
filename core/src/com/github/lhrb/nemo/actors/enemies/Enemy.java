/**
 * 
 */
package com.github.lhrb.nemo.actors.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.actors.PhysicalActor;
import com.github.lhrb.nemo.actors.shots.Laser;
import com.github.lhrb.nemo.actors.shots.Shots;
import com.github.lhrb.nemo.util.AnimationLoader;

/**
 * @author exa
 *
 */
public abstract class Enemy extends PhysicalActor{
    
    public Enemy(Stage stage){
        super();
        setCharacteristics(stage);
    }
    
    public Enemy(float x, float y, Stage stage) {
        super(x,y,stage);
        setCharacteristics(stage);
    }
    
    int hp;
    
    protected abstract void setCharacteristics(Stage stage);

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.actors.PhysicalActor#collision()
     */
    @Override
    public void collision() {
        hp -= 1;
        if(hp <= 0) {
            if(getStage() != null) {
                Gdx.audio.newSound(Gdx.files.internal("sound/explosion1.ogg")).play();

                new ActorPrefab(getX(), getY(), getStage())
                        .setAnimation(AnimationLoader.loadAnimation(
                                "explosion.png", 6, 6, 0.05f, false));
                remove();
            }
        }
    }
    
    
    
    
}
