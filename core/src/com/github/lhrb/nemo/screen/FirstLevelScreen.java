package com.github.lhrb.nemo.screen;

import com.github.lhrb.nemo.actors.*;
import com.github.lhrb.nemo.util.AnimationLoader;

public class FirstLevelScreen extends AbstractScreen{

    @Override
    public void init() {
        Background bg = new Background(0,0,gameStage,1);
        Background bg2 = new Background(0,1200,gameStage,1);
        EnemyOne enemie = new EnemyOne(500,500,gameStage);
        EnemyTwo enemy2 = new EnemyTwo(600,500,gameStage);
        EnemyThree enemy3 = new EnemyThree(700,500,gameStage);
        Player player = new Player(20,20, gameStage);
        // player.setWorldDimension(1200, 600); // should get reworked
        ActorPrefab explosion = new ActorPrefab(200,200, gameStage);
        explosion.setAnimation(AnimationLoader.loadAnimation(
                               "explosion.png", 6, 6, 0.05f, true));

        
    }

    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub
        
    }

}
