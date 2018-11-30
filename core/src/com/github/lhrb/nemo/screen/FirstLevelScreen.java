package com.github.lhrb.nemo.screen;

import com.github.lhrb.nemo.SpawnFactory.EnemyFactory;
import com.github.lhrb.nemo.actors.*;
import com.github.lhrb.nemo.util.AnimationLoader;

public class FirstLevelScreen extends AbstractScreen{
    EnemyFactory factory = new EnemyFactory(1,gameStage);

    @Override
    public void init() {
        Background bg = new Background(0,0,gameStage,1);
        Background bg2 = new Background(0,1200,gameStage,1);

        Player player = new Player(20,20, gameStage);

        
    }

    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub
        factory.continueManufacture(delta);
    }

}
