package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.actors.Background;
import com.github.lhrb.nemo.util.AnimationLoader;

public class FirstLevelScreen extends AbstractScreen{
    
    Player player;
    ActorPrefab explosion;
    private Music test;

    @Override
    public void init() {
        Background bg = new Background(0,0,gameStage,1);
        Background bg2 = new Background(0,1200,gameStage,1);

        player = new Player(20,20, gameStage);
        player.setShapePolygon(8);
        
        // player.setWorldDimension(1200, 600); // should get reworked
        explosion = new ActorPrefab(200,200, gameStage);
        explosion.setAnimation(AnimationLoader.loadAnimation(
                               "explosion.png", 6, 6, 0.05f, true));
        explosion.setShapePolygon(8);
        test = Gdx.audio.newMusic(Gdx.files.internal("sound/test.ogg"));
        test.setLooping(true);
        test.setVolume(0.2f);
        test.play();
        
    }

    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub
        if(player.overlap(explosion)) {
            System.out.println("Collision " + delta);
        }
    }

}
