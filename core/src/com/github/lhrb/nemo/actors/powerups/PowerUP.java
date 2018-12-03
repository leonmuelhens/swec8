package com.github.lhrb.nemo.actors.powerups;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.actors.PhysicalActor;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.SoundManager;

/**
 * @author exa
 *
 */
public abstract class PowerUP extends PhysicalActor{

    public PowerUP(Stage stage){
        super();
        setCharacteristics(stage);
    }

    public PowerUP(float x, float y, Stage stage) {
        super(x,y,stage);
        setCharacteristics(stage);
    }

    protected abstract void setCharacteristics(Stage stage);

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.actors.PhysicalActor#collision()
     */
    @Override
    public void collision(){
        remove();
    }
}