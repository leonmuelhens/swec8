package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.SpawnFactory.EnemyFactory;
import com.github.lhrb.nemo.actors.Background;
import com.github.lhrb.nemo.actors.CollisionManager;
import com.github.lhrb.nemo.actors.MultiPartActor;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.ui.HUD;
import com.github.lhrb.nemo.util.PropertyListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class LevelScreen extends AbstractScreen implements PropertyListener{
    protected int level;
    protected float gameTime;
    protected HUD hud;
    protected Player player;
    protected Background bg, bg2;
    protected MultiPartActor endBoss;
    protected EnemyFactory factory;
    protected float afterDeathTime;
    protected float soundVolume;


    PropertyChangeSupport changes;

    public void increaseVolume () {    }

    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub

        changes.firePropertyChange("gametime",(int)gameTime,(int)(gameTime+=delta));

        /* Once we define an abstract class for gameScreens, we can define a variable
           for how long the level shall take and replace the hardcorded 3*6
         */
        CollisionManager.checkCollision(getPhysicalActors());


        // For testing
        if (Gdx.input.isKeyPressed(Input.Keys.F10)) {
            gameTime += 3*60;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            float timeSinceESC = GameManager.get().getTimeSinceESC();

            if((gameTime-timeSinceESC) > 0) {
                this.pause();
                KillingNemo.setActiveScreen(new PauseScreen(this));
                GameManager.get().setTimeSinceESC(timeSinceESC);
            }

        }

        if (soundVolume < 0.25f) {
            increaseVolume();
        }


        if (gameTime < 3 * 60) {
            factory.continueManufacture(delta);
        }
        else if(endBoss == null){
            startBossFight();
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
}