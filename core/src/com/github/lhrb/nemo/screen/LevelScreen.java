package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.SpawnFactory.EnemyFactory;
import com.github.lhrb.nemo.actors.Background;
import com.github.lhrb.nemo.actors.CollisionManager;
import com.github.lhrb.nemo.actors.MultiPartActor;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.actors.enemies.Uboot;
import com.github.lhrb.nemo.ui.HUD;
import com.github.lhrb.nemo.ui.RingCooldownTimer;
import com.github.lhrb.nemo.util.PropertyListener;
import com.github.lhrb.nemo.util.SoundManager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class LevelScreen extends AbstractScreen implements PropertyListener{
    protected int level;
    float gameTime;
    HUD hud;
    Player player;
    Background bg, bg2;
    EnemyFactory factory;
    MultiPartActor endBoss;
    float afterDeathTime;
    float soundVolume;


    PropertyChangeSupport changes;

    public void increaseVolume () {    }

    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub
        gameTime += delta;

        /* Once we define an abstract class for gameScreens, we can define a variable
           for how long the level shall take and replace the hardcorded 3*6
         */
        CollisionManager.checkCollision(getPhysicalActors());


        // For testing
        if (Gdx.input.isKeyPressed(Input.Keys.F10)) {
            gameTime += 3*60;
        }

        if (soundVolume < 0.25f) {
            changes.firePropertyChange("gametime",gameTime,(int)(gameTime+delta));
            increaseVolume();
        }


        if (gameTime < 3 * 60) {
            factory.continueManufacture(delta);
        }
        else if (gameTime >= 3*60 && endBoss == null) {
            if (this instanceof FirstLevelScreen) {
                endBoss = new Uboot(gameStage.getWidth()/2,gameStage.getHeight(),gameStage);
            }
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

}
