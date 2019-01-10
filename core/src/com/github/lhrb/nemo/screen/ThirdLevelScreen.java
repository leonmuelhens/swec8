/**
 * 
 */
package com.github.lhrb.nemo.screen;

import com.github.lhrb.nemo.SpawnFactory.EnemyFactory;
import com.github.lhrb.nemo.actors.Background;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.actors.enemies.Hai;
import com.github.lhrb.nemo.actors.enemies.Kraken;
import com.github.lhrb.nemo.ui.HUD;
import com.github.lhrb.nemo.util.SoundManager;

import java.beans.PropertyChangeSupport;

/**
 * @author exa
 *
 */
public class ThirdLevelScreen extends LevelScreen {
    
    public ThirdLevelScreen() {
        super();
        
        gameTime = 0f;
        player = new Player(20, 20, gameStage); 
        hud = new HUD();
        hud.registerPropertyListener(this);
        hud.registerPropertyListener(player);
        guiStage.addActor(hud.getHUD());
    }

    public ThirdLevelScreen(Player player, HUD hud, float time) {
        super();
        gameTime = time;
        timeForLevel += time;
        this.player = player;
        this.hud = hud;
        hud.registerPropertyListener(this);
        guiStage.addActor(hud.getHUD());
        //player.setPosition(x, y);
        player.setChildStage(gameStage);
        gameStage.addActor(player);
    }
    
    @Override
    public void init() {
        level = 3;
        afterDeathTime = 5f;

        changes = new PropertyChangeSupport(this);

        bg = new Background(0, 0, gameStage, 3);
        bg2 = new Background(0, 1200, gameStage, 3);

        SoundManager.getInstance().playTrack("thirdlevel");

        factory = new EnemyFactory(gameStage);
    }


    @Override
    protected void startBossFight() {
        SoundManager.getInstance().playTrack("boss3");
        // we need the shark!
        endBoss = new Hai(gameStage.getWidth()/2,gameStage.getHeight(),gameStage);
    }

    @Override
    public void removeScreen(){switchScreen(new LevelDoneScreen());
    }

}
