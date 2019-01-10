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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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

        //Richtiger Code = korall20
        TextField Code1 = new TextField("Code1",GuiManager.getInstance().getTxtFldStyle());
        //Richtiger Code = tiefSee330
        TextField Code2 = new TextField("Code2",GuiManager.getInstance().getTxtFldStyle());

        ActionButton back = new ActionButton("Zurück",  GuiManager.getInstance().getTxtBtnStyleSmall(),MainMenuScreen.class);
        ActionButton lvlOne = new ActionButton("Starten",  GuiManager.getInstance().getTxtBtnStyleSmall(),FirstLevelScreen.class);
        TextButton lvlTwo = new TextButton("Starten",  GuiManager.getInstance().getTxtBtnStyleSmall());
        TextButton lvlThree = new TextButton("Starten",  GuiManager.getInstance().getTxtBtnStyleSmall());

        lvlTwo.addListener(
                (Event e) ->{
                    if( !(e instanceof InputEvent)) {
                        return false;
                    }
                    if( !((InputEvent)e).getType().equals(Type.touchDown) ) {
                        return false;
                    }
                    //check LvlCode

                    if(Code1.getText().equals("korall20")){
                        KillingNemo.setActiveScreen(new SecondLevelScreen());
                    }
                    else{
                        Code1.setText("Falsch");
                    }

                    return true;
                });

        lvlThree.addListener(
                (Event e) ->{
                    if( !(e instanceof InputEvent)) {
                        return false;
                    }
                    if( !((InputEvent)e).getType().equals(Type.touchDown) ) {
                        return false;
                    }
                    //check LvlCode
                    if(Code2.getText().equals("tiefSee330")){
                        KillingNemo.setActiveScreen(new ThirdLevelScreen());
                    }
                    else{
                        Code2.setText("Falsch");
                    }

                    return true;
                });

        Code1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x,float y){
                super.clicked(event,x,y);
                Code1.setText("");
            }
        });

        Code2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x,float y){
                super.clicked(event,x,y);
                Code2.setText("");
            }
        });

        int size = 126;
        int sizeText = 250;
        int padRight = 64;

        selection = new Table();
        selection.setFillParent(true); // fill full screen

        selection.add(back).expandX().left().top();
        selection.row().expandY();
        selection.add(lvlOne).width(size).right().padTop(47).padRight(-size);
        selection.row().expandY();
        selection.add(Code1).width(sizeText).right().padRight(5);
        selection.add(lvlTwo).width(size).right().padRight(padRight);
        selection.row().expandY();
        selection.add(Code2).width(sizeText).right().padBottom(53).padRight(5);
        selection.add(lvlThree).width(size).right().padBottom(53).padRight(padRight);

        guiStage.addActor(selection);

       // selection.debug();
    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#update(float)
     */
    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub

    }

}
