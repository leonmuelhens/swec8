/**
 * 
 */
package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.github.lhrb.nemo.actors.CollisionManager;
import com.github.lhrb.nemo.actors.MultiPartActor;
import com.github.lhrb.nemo.actors.PhysicalActor;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.util.AnimationLoader;

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
        
        
        MultiPartActor test = new MultiPartActor(0,200, gameStage);
        PhysicalActor back = new PhysicalActor();
        PhysicalActor middle = new PhysicalActor();
        PhysicalActor front = new PhysicalActor();
        back.setAnimation(AnimationLoader.get().texture("uboot_back.png"));
        middle.setAnimation(AnimationLoader.get().texture("uboot_middle.png"));
        front.setAnimation(AnimationLoader.get().texture("uboot_front.png"));
        back.setShapePolygon(8);
        middle.setShapePolygon(8);
        front.setShapePolygon(8);
        test.addPart(back, 0, 200);
        test.addPart(middle, 72, 200);
        test.addPart(front, 144, 200);
        
        //back.setPosition(0, 200);
        //middle.setPosition(72, 200);
        //front.setPosition(144, 200);
        //test.setPosition(200, 200);
        //gameStage.addActor(test);
        
        Player player = new Player(50,50, gameStage);

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
