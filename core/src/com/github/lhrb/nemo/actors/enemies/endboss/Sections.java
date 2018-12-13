package com.github.lhrb.nemo.actors.enemies.endboss;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.SoundManager;

public class Sections extends EndBoss {

    protected boolean destroyed = false;

    Stage parentStage;

    public Sections(Stage stage) {
        super(stage);
        parentStage = stage;
        setAnimation(null);
    }

    @Override
    protected void setCharacteristics(Stage stage) {
        hp = 10;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public void collision(Player p) {
        hp -= 1;
        if(hp <= 0) {
            if(getStage() != null) {

                GameManager.getInstance().addScore(scoreValue);
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
    public Stage getStage() {
        return parentStage;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
