/**
 *
 */
package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.GuiManager;
import com.github.lhrb.nemo.util.SoundManager;

/**
 * @author exa
 *
 */
public class GameOverScreen extends AbstractScreen {

    Label gameOver;
    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#init()
     */
    @Override
    public void init() {
        SoundManager.getInstance().playTrack("menu");
        
        ActorPrefab bg = new ActorPrefab(0,0, gameStage);
        bg.setAnimation(AnimationLoader.get().texture("bg1.png"));
                
        Table table = new Table();
        table.setFillParent(true);
        
        gameOver = new Label("Game Over :(",
                GuiManager.getInstance().getLabelStyleBig());
        //guiStage.addActor(gameOver);

        TextButton backBtn = new TextButton("zum Hauptmenü", GuiManager.getInstance().getTxtBtnStyle());
        backBtn.addListener(
                (Event e) ->{
                    if( !(e instanceof InputEvent)) {
                        return false;
                    }
                    if( !((InputEvent)e).getType().equals(Type.touchDown) ) {
                        return false;
                    }
                    //set next screen
                    KillingNemo.setActiveScreen(new MainMenuScreen());
                    return true;
                });


        //guiStage.addActor(backBtn);
        
        table.add(gameOver).spaceBottom(50);
        table.row();
        table.add(backBtn);
        
        guiStage.addActor(table);


    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#update(float)
     */
    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub

    }

}
