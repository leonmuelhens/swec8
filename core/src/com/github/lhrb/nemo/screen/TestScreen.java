/**
 * 
 */
package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.actors.CollisionManager;
import com.github.lhrb.nemo.actors.MultiPartActor;
import com.github.lhrb.nemo.actors.PhysicalActor;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.actors.Section;
import com.github.lhrb.nemo.actors.powerups.CActor;
import com.github.lhrb.nemo.actors.powerups.CType;
import com.github.lhrb.nemo.util.AnimationLoader;

/**
 * @author exa
 *
 */
public abstract class TestScreen extends AbstractScreen {
    
    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#init()
     */
    @Override
    public abstract void init();

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#update(float)
     */
    @Override
    public void update(float delta) {
        CollisionManager.checkCollision(getPhysicalActors());
        // TODO Auto-generated method stub
    }
}
