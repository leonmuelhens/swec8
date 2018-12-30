/**
 * 
 */
package com.github.lhrb.nemo.screen;

import java.beans.PropertyChangeSupport;

import com.github.lhrb.nemo.SpawnFactory.EnemyFactory;
import com.github.lhrb.nemo.actors.Background;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.actors.enemies.Kraken;
import com.github.lhrb.nemo.ui.HUD;
import com.github.lhrb.nemo.util.SoundManager;

/**
 * @author exa
 *
 */
public class SecondLevelScreen extends LevelScreen {
	
	public SecondLevelScreen() {
		super();		
		gameTime = 0f;
		player = new Player(20, 20, gameStage); 
		hud = new HUD();
        hud.registerPropertyListener(this);
        hud.registerPropertyListener(player);
        guiStage.addActor(hud.getHUD());
        
	}
	
	public SecondLevelScreen(Player player, HUD hud, float time) {
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
        level = 2;
        afterDeathTime = 5f;
        changes = new PropertyChangeSupport(this);

        bg = new Background(0, 0, gameStage, 2);
        bg2 = new Background(0, 1200, gameStage, 2);
        
        SoundManager.getInstance().playTrack("secondlevel");

        factory = new EnemyFactory(gameStage);
    }



    @Override
    protected void startBossFight() {
        SoundManager.getInstance().playTrack("boss2");
        endBoss = new Kraken(gameStage.getWidth()/2,gameStage.getHeight(),gameStage);
    }

    @Override
    public void removeScreen() {
        hud.removePropertyListener(this);
        switchScreen(new ThirdLevelScreen(player, hud, gameTime));
    }

}
