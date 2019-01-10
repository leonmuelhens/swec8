/**
 * 
 */
package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.ui.ActionButton;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.GuiManager;

/**
 * @author exa
 *
 */
public class ChooseLvlScreen extends AbstractScreen {
    private Table selection;

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#init()
     */
    @Override
    public void init() {
        ActorPrefab bg = new ActorPrefab(0,0, gameStage);
        bg.setAnimation(AnimationLoader.get().texture("levelauswahl.png"));

        ActionButton back = new ActionButton("Zurück",  GuiManager.getInstance().getTxtBtnStyleSmall(),MainMenuScreen.class);
        ActionButton lvlOne = new ActionButton("Level 1",  GuiManager.getInstance().getTxtBtnStyleSmall(),FirstLevelScreen.class);
        ActionButton lvlTwo = new ActionButton("Level 2",  GuiManager.getInstance().getTxtBtnStyleSmall(),SecondLevelScreen.class);
        ActionButton lvlThree = new ActionButton("Level 3",  GuiManager.getInstance().getTxtBtnStyleSmall(),ThirdLevelScreen.class);

        int size = 450;

        selection = new Table();
        selection.setFillParent(true); // fill full screen

        selection.add(back).expandX().pad(10).left().top();
        selection.row();
        selection.add(lvlOne).expandY().width(size).pad(10).bottom();
        selection.row();
        selection.add(lvlTwo).width(size).pad(10);
        selection.row();
        selection.add(lvlThree).expandY().width(size).pad(10).top();

        guiStage.addActor(selection);

        //selection.debug();
    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#update(float)
     */
    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub

    }

}
