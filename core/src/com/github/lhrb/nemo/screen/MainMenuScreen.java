/**
 * 
 */
package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
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
        //GameInterface gui = new GameInterface(guiStage);

        ActorPrefab bg = new ActorPrefab(0,0, gameStage);
        bg.setAnimation(AnimationLoader.get().texture("mainmenu_background.png"));
        
        Table table = new Table();
        table.setFillParent(true); // fill full screen
        //table.setDebug(true);
        TextButtonStyle style = GuiManager.getInstance().getTxtBtnStyle();
        
        TextButton startBtn = new TextButton("Neues Spiel", style);
        //startBtn.setFillParent(true);
        //startBtn.setPosition(200, 300);
        
        TextButton lvlBtn = new TextButton("Level", style);
        TextButton hsBtn = new TextButton("High Score", style);
        TextButton keysBtn = new TextButton("Tastenbelegung", style);
        TextButton closeBtn = new TextButton("Beenden", style);
        
        startBtn.addListener(
                (Event e) ->{
                    if( !(e instanceof InputEvent)) { return false; }
                    if( !((InputEvent)e).getType().equals(Type.touchDown) ) { return false; }
                    //set next screen
                    KillingNemo.setActiveScreen(new FirstLevelScreen());
                    return true;
                });
        
        lvlBtn.addListener(
                (Event e) ->{
                    if( !(e instanceof InputEvent)) { return false; }
                    if( !((InputEvent)e).getType().equals(Type.touchDown) ) { return false; }
                    //set next screen
                    KillingNemo.setActiveScreen(new ChooseLvlScreen());
                    return true;
                });
        hsBtn.addListener(
                (Event e) ->{
                    if( !(e instanceof InputEvent)) { return false; }
                    if( !((InputEvent)e).getType().equals(Type.touchDown) ) { return false; }
                    //set next screen
                    KillingNemo.setActiveScreen(new HighscoreScreen());
                    return true;
                });
        keysBtn.addListener(
                (Event e) ->{
                    if( !(e instanceof InputEvent)) { return false; }
                    if( !((InputEvent)e).getType().equals(Type.touchDown) ) { return false; }
                    //set next screen
                    KillingNemo.setActiveScreen(new OptionScreen());
                    return true;
                });
        
        
        closeBtn.addListener(
                (Event e) ->{
                    if( !(e instanceof InputEvent)) { return false; }
                    if( !((InputEvent)e).getType().equals(Type.touchDown) ) { return false; }
                    Gdx.app.exit();;
                    return true;
                });
        

        int size = 450;
        
        table.add(startBtn).width(size).spaceBottom(5);
        table.row();
        table.add(lvlBtn).width(size).spaceBottom(5);
        table.row();
        table.add(hsBtn).width(size).spaceBottom(5);
        table.row();
        table.add(keysBtn).width(size).spaceBottom(5);
        table.row();
        table.add(closeBtn).width( (size - 100) );
        
        //guiStage.addActor(startBtn);
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