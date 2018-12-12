package com.github.lhrb.nemo.screen;

import com.github.lhrb.nemo.SpawnFactory.EnemyFactory;
import com.github.lhrb.nemo.actors.Background;
import com.github.lhrb.nemo.actors.CollisionManager;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.ui.HUD;
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

    PropertyChangeSupport changes;

    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub
        changes.firePropertyChange("gametime",gameTime,(int)(gameTime+delta));
        gameTime += delta;
        /* Once we define an abstract class for gameScreens, we can define a variable
           for how long the level shall take and replace the hardcorded 3*6
         */
        CollisionManager.checkCollision(getPhysicalActors());

        if (gameTime < 3 * 60) {
            factory.continueManufacture(delta);
        } else {
            // we have to initialize the bossScreen
            System.out.println("The Boss level should start here");
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
