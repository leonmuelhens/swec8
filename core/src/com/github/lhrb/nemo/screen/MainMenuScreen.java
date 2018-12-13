/**
 * 
 */
package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.GuiManager;

/**
 * @author exa
 *
 */
public class MainMenuScreen extends AbstractScreen {

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#init()
     */
    @Override
    public void init() {
        ActorPrefab bg = new ActorPrefab(0,0, gameStage);
        bg.setAnimation(AnimationLoader.get().texture("mainmenu_background.png"));
        
        TextButton startBtn = new TextButton("Neues Spiel", GuiManager.getInstance().getTxtBtnStyle());
        startBtn.setPosition(200, 300);
        startBtn.addListener(
                (Event e) ->{
                    if( !(e instanceof InputEvent)) {
                        return false;
                    }
                    if( !((InputEvent)e).getType().equals(Type.touchDown) ) {
                        return false;
                    }
                    //set next screen
                    KillingNemo.setActiveScreen(new FirstLevelScreen());
                    return true;
                });
        
        
        guiStage.addActor(startBtn);
        
        
        
    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#update(float)
     */
    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub

    }

}
