package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.actors.Background;
import com.github.lhrb.nemo.actors.CollisionManager;
import com.github.lhrb.nemo.SpawnFactory.EnemyFactory;
import com.github.lhrb.nemo.util.SoundManager;


public class FirstLevelScreen extends AbstractScreen {
    private EnemyFactory factory = new EnemyFactory(1, gameStage);
    private float gameTime;
    private Player player;
    private ActorPrefab explosion;
    private SoundManager sM;

    @Override
    public void init() {
        gameTime = 0F;
        Background bg = new Background(0, 0, gameStage, 1);
        Background bg2 = new Background(0, 1200, gameStage, 1);

        player = new Player(20, 20, gameStage);

        sM = SoundManager.getInstance();
        sM.playTrack("firstlevel");
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
    }
}
