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
public class ThirdLevelScreen extends LevelScreen {

    @Override
    public void init() {
        gameTime = 0F;
        level = 3;
        afterDeathTime = 5f;

        changes = new PropertyChangeSupport(this);

        bg = new Background(0, 0, gameStage, 3);
        bg2 = new Background(0, 1200, gameStage, 3);

        player = new Player(20, 20, gameStage);
        hud = new HUD();
        hud.registerPropertyListener(this);
        hud.registerPropertyListener(player);
        guiStage.addActor(hud.getHUD());

        SoundManager.getInstance().playTrack("thirdlevel");

        factory = new EnemyFactory(gameStage);
    }


    @Override
    protected void startBossFight() {
        SoundManager.getInstance().playTrack("boss3");
        // we need the shark!
        endBoss = new Kraken(gameStage.getWidth()/2,gameStage.getHeight(),gameStage);
    }

}
