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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.GuiManager;
import com.github.lhrb.nemo.util.Highscore;
import com.github.lhrb.nemo.util.SoundManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author exa
 *
 */
public class LevelDoneScreen extends AbstractScreen {

    Label levelDone,levelDone2,levelDone4;
    TextField levelDone3;
    GameManager game;
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
        //table.setDebug(true);
        game = GameManager.get();
        score = game.getHighscores();

        levelDone = new Label("Level Beendet",
                GuiManager.getInstance().getLabelStyleBig());

        table.add(levelDone).colspan(3).align(Align.center).padBottom(20);
        table.row();
        TextButton backBtn = new TextButton("Zum Hauptmenü", GuiManager.getInstance().getTxtBtnStyle());
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
        //Test if the players reached score is high enough
        if(game.getScore()>score.get(9).getScore()){

            levelDone3 = new TextField("Username",GuiManager.getInstance().getTxtFldStyle());
            levelDone2 = new Label("Username:",
                    GuiManager.getInstance().getLabelStyle());
            levelDone3.setWidth(600);
            levelDone2.setAlignment(Align.right);
            table.add(levelDone2).spaceBottom(50).align(Align.right).width(400);
            table.add(levelDone3).spaceBottom(50).align(Align.left).width(300);
            table.row();
            //Save Button removes the input fields from the table so you can`t save your score multiple times
            TextButton subBtn = new TextButton("Save", GuiManager.getInstance().getTxtBtnStyle());
            subBtn.addListener(
                    (Event e) ->{
                        if( !(e instanceof InputEvent)) {
                            return false;
                        }
                        if( !((InputEvent)e).getType().equals(Type.touchDown) ) {
                            return false;
                        }
                        //save
                        score.add(new Highscore(levelDone3.getText(),game.getScore()));
                        Collections.sort(score,Comparator.comparing(Highscore::getScore));
                        Collections.reverse(score);
                        score.remove(10);
                        game.setHighscores(score);
                        table.removeActor(levelDone2);
                        table.removeActor(levelDone3);
                        table.removeActor(subBtn);
                        table.removeActor(backBtn);

                        levelDone4=new Label("Dein Highscore wurde gespeichert \n"+levelDone3.getText(),GuiManager.getInstance().getLabelStyleSmall());
                        levelDone4.setAlignment(Align.center);
                        table.row();
                        table.add(levelDone4).colspan(3).align(Align.center).padBottom(20);
                        table.row();
                        table.add(backBtn).colspan(3).align(Align.center);
                        return true;
                    });
            table.add(subBtn).colspan(3).align(Align.center);
        }
        else {
            //Falls kein Platz auf der Highscore Liste erreicht wurde
            levelDone2 = new Label("Sie haben " + game.getScore() + " Punkte erreicht!",
                    GuiManager.getInstance().getLabelStyle());
            table.add(levelDone2).colspan(3).align(Align.center).padBottom(20);
            table.row();
        }
        table.row();
        table.add(backBtn).colspan(3).align(Align.center);
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
