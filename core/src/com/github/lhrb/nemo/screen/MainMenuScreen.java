/**
 * 
 */
package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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

    private float menuDelay;
    private Table mainMenu;

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#init()
     */
    @Override
    public void init() {
        //GameInterface gui = new GameInterface(guiStage);
        menuDelay = 0;
        ActorPrefab bg = new ActorPrefab(0,0, gameStage);
        bg.setAnimation(AnimationLoader.get().texture("mainmenu_background.png"));
        
        mainMenu = new Table();
        mainMenu.setFillParent(true); // fill full screen
        //table.setDebug(true);
        TextButtonStyle style = GuiManager.getInstance().getTxtBtnStyle();
        
        TextButton startBtn = new TextButton("Neues Spiel", style);
        //startBtn.setFillParent(true);
        //startBtn.setPosition(200, 300);
        
        TextButton lvlBtn = new TextButton("Level", style);
        TextButton hsBtn = new TextButton("High Score", style);
        TextButton keysBtn = new TextButton("Tastenbelegung", style);
        TextButton closeBtn = new TextButton("Beenden", style);

        Label gameName = new Label("Killing Nemo", GuiManager.getInstance().getLabelStyleBig());
        
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

        mainMenu.add(gameName).spaceBottom(40);
        mainMenu.row();
        mainMenu.add(startBtn).width(size).spaceBottom(5);
        mainMenu.row();
        mainMenu.add(lvlBtn).width(size).spaceBottom(5);
        mainMenu.row();
        mainMenu.add(hsBtn).width(size).spaceBottom(5);
        mainMenu.row();
        mainMenu.add(keysBtn).width(size).spaceBottom(5);
        mainMenu.row();
        mainMenu.add(closeBtn).width( (size - 100) );

        //guiStage.addActor(startBtn);
        guiStage.addActor(mainMenu);
        mainMenu.setVisible(false);
        mainMenu.setTransform(true);
        mainMenu.addAction(Actions.fadeOut(0f));
        
        
    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#update(float)
     */
    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub
        if (menuDelay <=1) {
            menuDelay += delta;
        } else {
            mainMenu.setTransform(true);
            mainMenu.setVisible(true);
            mainMenu.addAction(Actions.fadeIn(5f));
        }
    }

}