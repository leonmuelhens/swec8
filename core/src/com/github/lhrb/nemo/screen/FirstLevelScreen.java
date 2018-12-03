package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.actors.weapons.ActiveWeaponIcon;
import com.github.lhrb.nemo.ui.GameInterface;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.GuiManager;
import com.github.lhrb.nemo.actors.Background;
import com.github.lhrb.nemo.actors.CollisionManager;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.SpawnFactory.EnemyFactory;
import com.github.lhrb.nemo.util.SoundManager;

import java.util.ArrayList;
import java.util.HashMap;


public class FirstLevelScreen extends AbstractScreen {
    private EnemyFactory factory = new EnemyFactory(1, gameStage);
    private float gameTime;
    private Label gameTimeText;
    private Player player;
    private Label score, life;
    private ActiveWeaponIcon playerWeapon;

    @Override
    public void init() {
        gameTime = 0F;

        Background bg = new Background(0, 0, gameStage, 1);
        Background bg2 = new Background(0, 1200, gameStage, 1);

        player = new Player(20, 20, gameStage);

        GameInterface gui = new GameInterface(guiStage,prepareTableObjects());

    }

    public HashMap<String, Actor> prepareTableObjects() {
        HashMap<String, Actor> tableObjects = new HashMap<String, Actor>();

        score = new Label(GameManager.getInstance().getScoreAsString(),
                GuiManager.getInstance().getLabelStyle());

        gameTimeText = new Label(String.valueOf(gameTime),GuiManager.getInstance().getLabelStyle());

        tableObjects.put("score",score);
        tableObjects.put("gametime",gameTimeText);
        Actor heart = new ActorPrefab();
        ((ActorPrefab) heart).setAnimation(AnimationLoader.loadTexture("heart.png"));
        tableObjects.put("heart", heart);
        life = new Label(player.getLife(),
                GuiManager.getInstance().getLabelStyle());
        tableObjects.put("life",life);
        //guiStage.addActor(life);
        playerWeapon = player.getWeaponIcon();
        tableObjects.put("weapon", playerWeapon);

        SoundManager.getInstance().playTrack("firstlevel");

        return tableObjects;
    }

    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub
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
        
        //needs rework since String is immutable (memory performance) 
        score.setText(GameManager.getInstance().getScoreAsString());
        life.setText(player.getLife());
        gameTimeText.setText(String.valueOf(Math.round(gameTime)));
    }
}
