package com.github.lhrb.nemo.screen;


import com.github.lhrb.nemo.SpawnFactory.EnemyFactory;
import com.github.lhrb.nemo.actors.Background;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.actors.enemies.Uboot;
import com.github.lhrb.nemo.ui.HUD;
import com.github.lhrb.nemo.util.SoundManager;

import java.beans.PropertyChangeSupport;



public class FirstLevelScreen extends LevelScreen {
    @Override
    public void init() {
        gameTime = 0F;
        level = 1;
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

        factory = new EnemyFactory(gameStage, level);
    }


    @Override
    protected void startBossFight() {
        SoundManager.getInstance().playTrack("boss");
        endBoss = new Uboot(gameStage.getWidth()/2,gameStage.getHeight(),gameStage);
    }


    @Override
    public void removeScreen() {
        hud.removePropertyListener(this);
        switchScreen(new LevelTransitionScreen(player, hud, gameTime,1));
    }
}