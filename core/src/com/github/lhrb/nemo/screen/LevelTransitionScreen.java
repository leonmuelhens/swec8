/**
 *
 */
package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.ui.HUD;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.GuiManager;
import com.github.lhrb.nemo.util.SoundManager;

/**
 * @author exa
 *
 */
public class LevelTransitionScreen extends AbstractScreen {

    private Label levelDone;
    private Player player1;
    private Player player2;
    private HUD hud;
    private float time;
    private int level;
    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#init()
     */


    public LevelTransitionScreen(Player player1,Player player2,int lvl){
        this.player1=player1;
        this.player2=player2;
        level =lvl;
    }

    public LevelTransitionScreen(Player player, HUD hud, float gameTime, int lvl) {
        player1 = player;
        level = lvl;
        this.hud=hud;
        time =gameTime;
    }


    @Override
    public void init() {
        SoundManager.getInstance().playTrack("menu");

        ActorPrefab bg = new ActorPrefab(0,0, gameStage);
        bg.setAnimation(AnimationLoader.get().texture("bg1.png"));

        Table table = new Table();
        table.setFillParent(true);

        levelDone = new Label("Level Geschafft :)",
                GuiManager.getInstance().getLabelStyleBig());

        TextButton continueBtn = new TextButton("Weiter Spielen", GuiManager.getInstance().getTxtBtnStyle());
        continueBtn.addListener(
                (Event e) ->{
                    if( !(e instanceof InputEvent)) {
                        return false;
                    }
                    if( !((InputEvent)e).getType().equals(Type.touchDown) ) {
                        return false;
                    }
                    //set next screen
                    switch (level) {
                        //First Level
                        case (1):
                            SequenceAction sqA = new SequenceAction();
                            sqA.addAction(Actions.fadeOut(2f));
                            sqA.addAction(Actions.run(() -> {
                                KillingNemo.setActiveScreen(new SecondLevelScreen(player1,hud,time));
                            }));
                            gameStage.getRoot().addAction(sqA);
                            break;
                            //Second Level
                        case(2):
                            SequenceAction sqB = new SequenceAction();
                            sqB.addAction(Actions.fadeOut(2f));
                            sqB.addAction(Actions.run(() -> {
                                KillingNemo.setActiveScreen(new ThirdLevelScreen(player1,hud,time));
                            }));
                            gameStage.getRoot().addAction(sqB);
                            break;
                        default:
                            return false;
                    }
                    return true;
                });


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
                    //TODO Needs to be changed after merge with highscore board
                    KillingNemo.setActiveScreen(new LevelDoneScreen());
                    return true;
                });


        //guiStage.addActor(backBtn);

        table.add(levelDone).spaceBottom(50);
        table.row();
        table.add(continueBtn).spaceBottom(25);
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
