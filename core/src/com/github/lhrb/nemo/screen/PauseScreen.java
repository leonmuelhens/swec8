package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.GuiManager;

public class PauseScreen extends AbstractScreen{
    AbstractScreen backScreen;
    float escTime= GameManager.get().getTimeSinceESC();
    public PauseScreen(Screen activeScreen) {
        gameStage = new Stage();
        guiStage = new Stage();

        backScreen = (AbstractScreen)activeScreen;
        init();

    }
    private Table pauseMenu;

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#init()
     */
    @Override
    public void init() {
        //GameInterface gui = new GameInterface(guiStage);

        ActorPrefab bg = new ActorPrefab(0,0, gameStage);
        bg.setAnimation(AnimationLoader.get().texture("ui/pausemenu_background.png"));

        pauseMenu = new Table();
        pauseMenu.setFillParent(true); // fill full screen
        //pauseMenu.setDebug(true);
        TextButton.TextButtonStyle style = GuiManager.getInstance().getTxtBtnStyle();
        TextButton mainMenuBtn = new TextButton("Hauptmenü", style);
        TextButton keysBtn = new TextButton("Tastenbelegung", style);
        TextButton closeBtn = new TextButton("Beenden", style);

        mainMenuBtn.addListener(
                (Event e) ->{
                    if( !(e instanceof InputEvent)) { return false; }
                    if( !((InputEvent)e).getType().equals(InputEvent.Type.touchDown) ) { return false; }
                    //set next screen
                    KillingNemo.setActiveScreen(new MainMenuScreen());
                    return true;
                });
        keysBtn.addListener(
                (Event e) ->{
                    if( !(e instanceof InputEvent)) { return false; }
                    if( !((InputEvent)e).getType().equals(InputEvent.Type.touchDown) ) { return false; }
                    //set next screen
                    this.pause();
                    KillingNemo.setActiveScreen(new OptionScreen(KillingNemo.getActiveScreen()));
                    return true;
                });


        closeBtn.addListener(
                (Event e) ->{
                    if( !(e instanceof InputEvent)) { return false; }
                    if( !((InputEvent)e).getType().equals(InputEvent.Type.touchDown) ) { return false; }
                    Gdx.app.exit();;
                    return true;
                });


        TextButton backBtn = new TextButton("Zurück", GuiManager.getInstance().getTxtBtnStyleSmall());
        backBtn.setPosition(5, 530);
        backBtn.addListener(
                (Event e) ->{
                    if( !(e instanceof InputEvent)) { return false; }
                    if( !((InputEvent)e).getType().equals(InputEvent.Type.touchDown) ) { return false; }
                    //set next screen
                    backScreen.resume();
                    KillingNemo.setActiveScreen(backScreen);
                    return true;
                });


        int size = 375;

        pauseMenu.add(backBtn).colspan(3).left().top().pad(10).expandX().padBottom(120);
        pauseMenu.row();
        pauseMenu.add(mainMenuBtn).width(size).spaceBottom(5).colspan(3);
        pauseMenu.row();
        pauseMenu.add(keysBtn).width(size).spaceBottom(5).colspan(3);
        pauseMenu.row();
        pauseMenu.add(closeBtn).width(size).colspan(3);
        pauseMenu.row();
        pauseMenu.add().expandY().colspan(3);

        guiStage.addActor(pauseMenu);
    }

    @Override
    public void update(float delta) {
        escTime =escTime +delta;
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            float timeSinceESC = GameManager.get().getTimeSinceESC();
            if((escTime-timeSinceESC) > 0.5f) {
                backScreen.resume();
                KillingNemo.setActiveScreen(backScreen);
                GameManager.get().setTimeSinceESC(escTime);

            } else {
                // ignore the collision
            }

        }

    }
}
