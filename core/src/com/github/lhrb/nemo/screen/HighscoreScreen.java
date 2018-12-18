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
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.Align;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.GuiManager;
import com.github.lhrb.nemo.util.Highscore;

import java.util.ArrayList;

/**
 * @author exa
 *
 */
public class HighscoreScreen extends AbstractScreen {

    Table highscore;
    ArrayList<Highscore> stats;
    private Label rank, points, name, titel;

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#init()
     */
    @Override
    public void init() {


        stats= GameManager.get().getHighscores();
        ActorPrefab bg = new ActorPrefab(0,0, gameStage);
        bg.setAnimation(AnimationLoader.get().texture("highscore.png"));
        highscore = new Table();
        highscore.setFillParent(true); // fill full screen

        rank = new Label("Rang", GuiManager.getInstance().getLabelStyle());
        points = new Label("Punkte", GuiManager.getInstance().getLabelStyle());
        name = new Label("Name", GuiManager.getInstance().getLabelStyle());
        titel = new Label("HIGHSCORE", GuiManager.getInstance().getLabelStyleBig());

        TextButton backBtn = new TextButton("Back", GuiManager.getInstance().getTxtBtnStyleSmall());
        //backBtn.setPosition(5, 530);
        backBtn.addListener(
                (Event e) ->{
                    if( !(e instanceof InputEvent)) { return false; }
                    if( !((InputEvent)e).getType().equals(Type.touchDown) ) { return false; }
                    //set next screen
                    KillingNemo.setActiveScreen(new MainMenuScreen());
                    return true;
                });
        int size = 450;
        highscore.add(titel).expandY().colspan(3).align(Align.center);
        highscore.row();

        highscore.add(rank).pad(10).align(Align.left).top().padBottom(0);
        highscore.add(points).pad(10).align(Align.center).top().padBottom(0);
        highscore.add(name).pad(10).align(Align.right).top().padBottom(0);
        highscore.row();

        addScoreToTable();
        highscore.add(backBtn).colspan(3).spaceBottom(5).expandY().width(450);

        //highscore.setDebug(true);
        guiStage.addActor(highscore);
        //highscore.debug();

    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#update(float)
     */
    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub

    }

    public  void addScoreToTable(){
        int i =1;
        for (Highscore stat :stats) {
            Label place = new Label(String.valueOf(i), GuiManager.getInstance().getLabelStyle());
            Label score = new Label(String.valueOf(stat.getScore()), GuiManager.getInstance().getLabelStyle());
            Label name = new Label(stat.getName(), GuiManager.getInstance().getLabelStyle());

            highscore.add(place).align(Align.left).pad(5).padLeft(10).width(100);
            highscore.add(score).align(Align.center).pad(5).expandX();
            highscore.add(name).align(Align.right).pad(5).width(300);
            highscore.row();
            i++;
        }

    }

}
