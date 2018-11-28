package com.github.lhrb.nemo.screen;

import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.util.AnimationLoader;

public class FirstLevelScreen extends AbstractScreen{

    @Override
    public void init() {
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