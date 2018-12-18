/**
 *
 */
package com.github.lhrb.nemo.screen;


import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.*;
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

    Label levelDone,levelDone2;
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
        table.setDebug(true);
        game = GameManager.get();
        score = game.getHighscores();

        levelDone = new Label("Level Geschafft :)",
                GuiManager.getInstance().getLabelStyleBig());

        table.add(levelDone).spaceBottom(50).width(Value.percentWidth(.75F,table)).center();
        table.row();

        if(/*game.getScore()>score.get(9).getScore()*/true){

            levelDone3 = new TextField("Username",GuiManager.getInstance().getTxtFldStyle());
            levelDone2 = new Label("Username:",
                    GuiManager.getInstance().getLabelStyle());
            levelDone3.setWidth(600);
            table.add(levelDone2).spaceBottom(50).align(Align.left);
            table.add(levelDone3).spaceBottom(50).align(Align.left).fillX();
            table.row();
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
                        levelDone2.setText("Dein Highscore wurde gespeichert "+levelDone3.getText());
                        table.removeActor(levelDone3);
                        table.removeActor(subBtn);
                        return true;
                    });
            table.add(subBtn);
        }
        else {
            levelDone2 = new Label("Sie haben " + game.getScore() + " Punkte erreicht!",
                    GuiManager.getInstance().getLabelStyle());
            table.add(levelDone2).spaceBottom(50);
            table.row();
        }


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
