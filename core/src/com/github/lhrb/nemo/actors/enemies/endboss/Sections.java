package com.github.lhrb.nemo.actors.enemies.endboss;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.actors.CollisionEvent;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.actors.shots.Shots;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.SoundManager;

public class Sections extends EndBoss {

    protected boolean destroyed = false;

    Stage parentStage;

    public Sections() {
        super();
        setAnimation(null);
    }

    @Override
    protected void setCharacteristics() {
        setHp(10);
        setScoreValue(100);
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public void collision(CollisionEvent col) {
        if (col.getSource() instanceof Shots) {
            decreaseHp();
        }

        if(getHp() <= 0) {
            if(getStage() != null) {

                GameManager.get().addScore(getScoreValue());
                SoundManager.getInstance().playSound("explosion");

                //code below is bad
                new ActorPrefab(getX(), getY(), getStage())
                        .setAnimation(AnimationLoader.get().animation(
                                "explosion.png", 6, 6, 0.05f, false));
                //end

                destroyed = true;
                remove();
            }
        }
    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
