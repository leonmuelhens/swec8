package com.github.lhrb.nemo.actors.shots;


import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.SoundManager;

import java.util.Random;

public class Bomb extends Shots {

    private float explodeY;

    public Bomb(float x, float y, float angle) {
        super(x, y, angle);

        setAnimation(AnimationLoader.get().texture("powerup_bombe.png"));
        setSpeedMax(100);
        setAcceleration(30000);
        setShapePolygon(8);

        explodeY = new Random().nextInt((int)(getStage().getHeight()*0.7));
    }

    public Bomb(float x, float y, float angle, float explodeY) {
        super(x, y, angle);

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

            new Explosion(getX(),getY());
            //code below is bad
            //new ActorPrefab(getX(), getY(), getStage())
            //        .setAnimation(AnimationLoader.get().animation(
            //                "big_explosion.png", 6, 6, 0.05f, false));

            remove();
        }
    }


    class Explosion extends Shots {

        public Explosion(float x, float y) {
            super(x, y, 0);

            setAnimation(AnimationLoader.get().animation(
                    "big_explosion.png", 6, 6, 0.05f, false));
            setShapePolygon(8);
            setPosition(getX()-(getWidth()/2), getY()-(getHeight()/2));
        }

        @Override
        public void act(float delta) {
            super.act(delta);
            if (isAnimationFinished()) {
                remove();
            }
        }

    }


}
