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
import com.github.lhrb.nemo.actors.Section;
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
        
        Section back = new Section(test, 0, 200, 5, 5, AnimationLoader.get().texture("uboot_back.png"));
        Section middle = new Section(test, 72, 200, 5, 5, AnimationLoader.get().texture("uboot_middle.png"));
        Section front = new Section(test, 144, 200, 5, 5, AnimationLoader.get().texture("uboot_front.png"));

        test.addPart(back);
        test.addPart(middle);
        test.addPart(front);
        
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
