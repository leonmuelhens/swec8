package com.github.lhrb.nemo.actors.shots;


import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.SoundManager;

import java.util.Random;

public class Bomb extends Shots {

    private float explodeY;

    public Bomb(float x, float y, float angle, Stage stage) {
        super(x, y, angle, stage);

        setAnimation(AnimationLoader.get().texture("bombe.png"));
        setSpeedMax(100);
        setAcceleration(30000);
        setShapePolygon(8);

        explodeY = new Random().nextInt((int)(getStage().getHeight()*0.7));
    }

    public Bomb(float x, float y, float angle, float explodeY, Stage stage) {
        super(x, y, angle, stage);

        setAnimation(AnimationLoader.get().texture("powerup_bombe.png"));
        setSpeedMax(100);
        setAcceleration(30000);
        setShapePolygon(8);

        this.explodeY = explodeY;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // Reliable explosion with margin of [-2;+2]
        if (getY() > explodeY-2 && getY() < explodeY+2) {
            SoundManager.getInstance().playSound("explosion");

            new Explosion(getX(),getY(), getStage());
            //code below is bad
            //new ActorPrefab(getX(), getY(), getStage())
            //        .setAnimation(AnimationLoader.get().animation(
            //                "big_explosion.png", 6, 6, 0.05f, false));

            addAction(Actions.removeActor());
        }
    }


    class Explosion extends Shots {

        public Explosion(float x, float y, Stage stage) {
            super(x, y, 0, stage);

            setAnimation(AnimationLoader.get().animation(
                    "big_explosion.png", 6, 6, 0.05f, false));
            setShapePolygon(8);
            setPosition(getX()-(getWidth()/2), getY()-(getHeight()/2));
        }

        @Override
        public void act(float delta) {
            super.act(delta);
            if (isAnimationFinished()) {
                addAction(Actions.removeActor());
            }
        }

    }


    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.actors.shots.Shots#perish()
     */
    @Override
    public void perish() {
        
    }


}
