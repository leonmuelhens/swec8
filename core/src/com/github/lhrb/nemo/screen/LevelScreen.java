package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.SpawnFactory.EnemyFactory;
import com.github.lhrb.nemo.actors.*;
import com.github.lhrb.nemo.actors.enemies.Uboot;
import com.github.lhrb.nemo.ui.HUD;
import com.github.lhrb.nemo.util.PropertyListener;
import com.github.lhrb.nemo.util.SoundManager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class LevelScreen extends AbstractScreen implements PropertyListener{
    protected int level;
    float gameTime;
    String gameTimeString, gameTimeStringBefore;
    HUD hud;
    Player player;
    Background bg, bg2;
    EnemyFactory factory;
    MultiPartActor endBoss;
    float afterDeathTime;
    float soundVolume;

    //Normal Constructor
    public LevelScreen(){
        super();
    }

    //Constructor with Player
    public LevelScreen(Player oldPlayer){
        super();
        //Updates Score, PowerUp, Weapon and Life if it was over the minimum
        player.addScore(oldPlayer.getScore());
        player.collision(new CollisionEvent(player, oldPlayer.getPowerUP()));
        player.collision(new CollisionEvent(player,oldPlayer.getWeapon()));
        player.collision(new CollisionEvent(player,oldPlayer.getBomb()));
        if(oldPlayer.getLife()>player.getLife())
            for(int i = 0; i<oldPlayer.getLife()-3;i++)
                oldPlayer.addLife();

    }

    //Preparation for Coop
    public LevelScreen(Player player1,Player player2){
        player.addScore(player1.getScore());
        player.collision(new CollisionEvent(player, player1.getPowerUP()));
        player.collision(new CollisionEvent(player,player1.getWeapon()));
        player2=player2;
    }


    PropertyChangeSupport changes;

    public void increaseVolume () {    }

    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub

        gameTimeStringBefore = String.format("%d:%02d",(int) gameTime / 60,(int) gameTime % 60);

        gameTime += delta;

        gameTimeString = String.format("%d:%02d",(int) gameTime / 60,(int) gameTime % 60);

        changes.firePropertyChange("gametime",gameTimeStringBefore,gameTimeString);

        /* Once we define an abstract class for gameScreens, we can define a variable
           for how long the level shall take and replace the hardcorded 3*6
         */
        CollisionManager.checkCollision(getPhysicalActors());


        // For testing
        if (Gdx.input.isKeyPressed(Input.Keys.F10)) {
            gameTime += 3*60;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.F11)) {
            KillingNemo.setActiveScreen(new LevelTransitionScreen(GameManager.get().getPlayer() ,1));

        }

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            float timeSinceESC = GameManager.get().getTimeSinceESC();

            if((gameTime-timeSinceESC) > 0.5f) {
                KillingNemo.getActiveScreen().pause();
                KillingNemo.setActiveScreen(new PauseScreen(KillingNemo.getActiveScreen()));
                GameManager.get().setTimeSinceESC(timeSinceESC);

            } else {
                // ignore the collision
            }

        }

        if (soundVolume < 0.25f) {
            changes.firePropertyChange("gametime",gameTime,(int)(gameTime+delta));
            increaseVolume();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.F11)) {
            KillingNemo.setActiveScreen(new LevelDoneScreen());
        }


        if (gameTime < 3 * 60) {
            factory.continueManufacture(delta);
        }
        else if (gameTime >= 3*60 && endBoss == null) {
            if (this instanceof FirstLevelScreen) {
                SoundManager.getInstance().playTrack("boss");
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
