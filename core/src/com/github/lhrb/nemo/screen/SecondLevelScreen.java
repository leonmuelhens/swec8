/**
 * 
 */
package com.github.lhrb.nemo.screen;

import com.github.lhrb.nemo.SpawnFactory.EnemyFactory;
import com.github.lhrb.nemo.actors.Background;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.actors.enemies.Kraken;
import com.github.lhrb.nemo.ui.HUD;
import com.github.lhrb.nemo.util.SoundManager;

import java.beans.PropertyChangeSupport;

/**
 * @author exa
 *
 */
public class SecondLevelScreen extends LevelScreen {

    @Override
    public void init() {
        gameTime = 0F;
        level = 2;
        soundVolume = 0f;
        afterDeathTime = 5f;

        changes = new PropertyChangeSupport(this);

        bg = new Background(0, 0, gameStage, 2);
        bg2 = new Background(0, 1200, gameStage, 2);

        player = new Player(20, 20, gameStage);
        hud = new HUD();
        hud.registerPropertyListener(this);
        hud.registerPropertyListener(player);
        guiStage.addActor(hud.getHUD());

        SoundManager.getInstance().playTrack("secondlevel");

        factory = new EnemyFactory(gameStage);
    }

    @Override
    public void increaseVolume () {
        // after 2.5seconds we reached the volume we want
        if (gameTime / 10 > 0.25f) soundVolume = 0.25f;
        else soundVolume = gameTime / 10;

        SoundManager.getInstance().setMusicStreamVolume("secondlevel",soundVolume);
    }

    @Override
    protected void startBossFight() {
        SoundManager.getInstance().playTrack("boss2");
        endBoss = new Kraken(gameStage.getWidth()/2,gameStage.getHeight(),gameStage);
    }

}
