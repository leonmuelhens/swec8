package com.github.lhrb.nemo.screen;


import com.badlogic.gdx.graphics.Color;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.ui.HUD;
import com.github.lhrb.nemo.actors.Background;
import com.github.lhrb.nemo.SpawnFactory.EnemyFactory;
import com.github.lhrb.nemo.ui.RingCooldownTimer;
import com.github.lhrb.nemo.util.SoundManager;

import java.beans.PropertyChangeSupport;



public class FirstLevelScreen extends LevelScreen {

    @Override
    public void init() {
        gameTime = 0F;
        level = 1;
        soundVolume = 0f;
        afterDeathTime = 5f;

        changes = new PropertyChangeSupport(this);

        bg = new Background(0, 0, gameStage, 1);
        bg2 = new Background(0, 1200, gameStage, 1);

        player = new Player(20, 20, gameStage);
        hud = new HUD();
        hud.registerPropertyListener(this);
        hud.registerPropertyListener(player);
        guiStage.addActor(hud.getHUD());

        SoundManager.getInstance().playTrack("firstlevel");

        factory = new EnemyFactory(gameStage);
    }

    @Override
    public void increaseVolume () {
        // after 2.5seconds we reached the volume we want
        if (gameTime / 10 > 0.25f) soundVolume = 0.25f;
        else soundVolume = gameTime / 10;

        SoundManager.getInstance().setMusicStreamVolume("firstlevel",soundVolume);
    }
}
