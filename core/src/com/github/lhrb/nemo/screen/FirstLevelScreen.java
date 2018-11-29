package com.github.lhrb.nemo.screen;

import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.actors.Background;
import com.github.lhrb.nemo.util.AnimationLoader;

public class FirstLevelScreen extends AbstractScreen{

    @Override
    public void init() {
        Background bg = new Background(0,0,gameStage,1);
        Background bg2 = new Background(0,1200,gameStage,1);

        Player player = new Player(20,20, gameStage);
        // player.setWorldDimension(1200, 600); // should get reworked
    }

    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub
        
    }

}
