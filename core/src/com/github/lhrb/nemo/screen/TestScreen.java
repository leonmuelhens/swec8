/**
 * 
 */
package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.util.AnimationLoader;

/**
 * @author exa
 *
 */
public class TestScreen extends AbstractScreen {
    
    Group test;
    float sx = 0;
    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#init()
     */
    @Override
    public void init() {
        
        test = new Group();
        Image back = new Image(AnimationLoader.get().texture("uboot_back.png").getKeyFrame(0));
        Image middle = new Image(AnimationLoader.get().texture("uboot_middle.png").getKeyFrame(0));
        Image front = new Image(AnimationLoader.get().texture("uboot_front.png").getKeyFrame(0));
        
        test.addActor(back);
        test.addActor(middle);
        test.addActor(front);
        
        back.setPosition(0, 200);
        middle.setPosition(72, 200);
        front.setPosition(144, 200);
        //test.setPosition(200, 200);
        gameStage.addActor(test);
        
        Player player = new Player(50,50, gameStage);

    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#update(float)
     */
    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub
        test.setPosition(sx++, test.getOriginY());
    }

}
