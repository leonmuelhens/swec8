/**
 * 
 */
package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.GuiManager;

/**
 * @author exa
 *
 */
public class OptionScreen extends AbstractScreen {

    AbstractScreen backScreen = new MainMenuScreen();

    public OptionScreen(){super();}
    public OptionScreen(Screen activeScreen) {
        gameStage = new Stage();
        guiStage = new Stage();

        backScreen = (AbstractScreen)activeScreen;
        init();

    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#init()
     */
    @Override
    public void init() {
        ActorPrefab bg = new ActorPrefab(0,0, gameStage);
        bg.setAnimation(AnimationLoader.get().texture("setting.png"));
        TextButton backBtn = new TextButton("Back", GuiManager.getInstance().getTxtBtnStyleSmall());
        backBtn.setPosition(5, 530);
        backBtn.addListener(
                (Event e) ->{
                    if( !(e instanceof InputEvent)) { return false; }
                    if( !((InputEvent)e).getType().equals(Type.touchDown) ) { return false; }
                    //set next screen
                    backScreen.resume();
                    KillingNemo.setActiveScreen(backScreen);
                    return true;
                });
        guiStage.addActor(backBtn);
    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#update(float)
     */
    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub

    }

}
