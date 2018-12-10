package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.actors.enemies.endboss.Uboot;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.GuiManager;
import com.github.lhrb.nemo.actors.Background;
import com.github.lhrb.nemo.actors.CollisionManager;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.SpawnFactory.EnemyFactory;
import com.github.lhrb.nemo.util.SoundManager;


public class FirstLevelScreen extends AbstractScreen {
    private EnemyFactory factory = new EnemyFactory(1, gameStage);
    private float gameTime;
    private Player player;
    private Label score, life;

    private Uboot uboot;

    @Override
    public void init() {
        gameTime = 0F;
        Background bg = new Background(0, 0, gameStage, 1);
        Background bg2 = new Background(0, 1200, gameStage, 1);
        
        score = new Label(GameManager.getInstance().getScoreAsString(),
                                 GuiManager.getInstance().getLabelStyle());
        score.setPosition(740, 550);
        guiStage.addActor(score);

        player = new Player(20, 20, gameStage);

        new ActorPrefab(750, 30, guiStage)
                .setAnimation(AnimationLoader.get().texture("heart.png"));
        life = new Label(player.getLife(),
                GuiManager.getInstance().getLabelStyle());
        life.setPosition(740, 60);
        guiStage.addActor(life);

        SoundManager.getInstance().playTrack("firstlevel");

    }

    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub
        gameTime += delta;

        /* Once we define an abstract class for gameScreens, we can define a variable
           for how long the level shall take and replace the hardcorded 3*6
         */
        CollisionManager.checkCollision(getPhysicalActors());

        if (Gdx.input.isKeyPressed(Input.Keys.F10)) {
            gameTime += 3 * 60;
        }

        if (gameTime < 3 * 60) {
            factory.continueManufacture(delta);
        } else if (gameTime > 3*60 && uboot == null) {
            // we have to initialize the bossScreen
            uboot = new Uboot(gameStage);
            uboot.setPosition(gameStage.getWidth()/2,gameStage.getHeight());
            gameStage.addActor(uboot);
        }
        else {
            // End level screen here
        }
        
        //needs rework since String is immutable (memory performance) 
        score.setText(GameManager.getInstance().getScoreAsString());
        life.setText(player.getLife());
    }
}
