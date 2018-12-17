/**
 *
 */
package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.GuiManager;
import com.github.lhrb.nemo.util.Highscore;
import com.github.lhrb.nemo.util.SoundManager;

import java.util.ArrayList;

/**
 * @author exa
 *
 */
public class LevelDoneScreen extends AbstractScreen {

    Label levelDone,levelDone2;
    GameManager game = GameManager.get();
    ArrayList<Highscore> score;



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
        score = game.getHighscores();

        levelDone = new Label("Level Geschafft :)",
                GuiManager.getInstance().getLabelStyleBig());
        if(game.getScore()>score.get(9).getScore()){
            Input.TextInputListener levelDone2 = new Input.TextInputListener() {
                @Override
                public void input(String text) {
                    score.add(new Highscore(text,game.getScore()));

                }

                @Override
                public void canceled() {

                }
            };
            Gdx.input.getTextInput(levelDone2, "Username", "Ihr Username", "Hint Value");
        }
        else {
            levelDone2 = new Label("Sie haben " + game.getScore() + " Punkte erreicht!",
                    GuiManager.getInstance().getLabelStyle());
        }

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

        table.add(levelDone).spaceBottom(50);
        table.row();
        table.add(levelDone2).spaceBottom(50);
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
