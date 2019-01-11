package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.SpawnFactory.EnemyFactory;
import com.github.lhrb.nemo.actors.Background;
import com.github.lhrb.nemo.actors.CollisionManager;
import com.github.lhrb.nemo.actors.MultiPartActor;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.ui.HUD;
import com.github.lhrb.nemo.util.PropertyListener;
import com.github.lhrb.nemo.util.SoundManager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class LevelScreen extends AbstractScreen implements PropertyListener{
    protected int level;
    protected float gameTime;
    protected float timeForLevel = 120f;
    protected HUD hud;
    protected Player player;
    protected Background bg, bg2;
    protected MultiPartActor endBoss;
    protected EnemyFactory factory;
    protected float afterDeathTime;

    PropertyChangeSupport changes;

    protected void increaseVolume () {  
    	// after 2.5seconds we reached the volume we want
        if (gameTime  <= 2.5f) {
        	SoundManager.getInstance()
        	            .setMusicStreamVolume(gameTime/10);
        }
        
    }

    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub

        changes.firePropertyChange("gametime",(int)gameTime,(int)(gameTime+=delta));

        /* Once we define an abstract class for gameScreens, we can define a variable
           for how long the level shall take and replace the hardcorded 3*6
         */
        CollisionManager.checkCollision(getPhysicalActors());



        /*if (Gdx.input.isKeyPressed(Input.Keys.F10)) {
            gameTime += 3*60;
        }*/


        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            float timeSinceESC = GameManager.get().getTimeSinceESC();

            if((gameTime-timeSinceESC) > 0) {
                this.pause();
                KillingNemo.setActiveScreen(new PauseScreen(this));
                GameManager.get().setTimeSinceESC(timeSinceESC);
            }

        }


        increaseVolume();
        
        if (gameTime < timeForLevel) {
            factory.continueManufacture(delta, false);
        }
        else if(endBoss == null){
            startBossFight();
        }
        else if (endBoss != null && endBoss.getPartCollection().size() <= 0) {
            removeScreen();
        }
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

    protected abstract void startBossFight();
    
    public abstract void removeScreen();
    
    public void switchScreen(AbstractScreen screen) {
        SequenceAction sqA = new SequenceAction();
        sqA.addAction(Actions.fadeOut(2f));
        sqA.addAction(Actions.run(() -> {
            KillingNemo.setActiveScreen(screen);
            }));
        gameStage.getRoot().addAction(sqA);
    }
        
}
