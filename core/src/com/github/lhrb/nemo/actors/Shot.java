package com.github.lhrb.nemo.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.util.AnimationLoader;

public class Shot extends ActorPrefab {

    private final float angle;

    public Shot(float x, float y, Stage stage, float angle) {
        super(x, y, stage);

        setAnimation(AnimationLoader.loadTexture("SchussStandart.png"));
        setSpeedMax(800);
        setAcceleration(30000);
        this.angle = angle;

        setX(getX()-(getWidth()/2));
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        accelerationAtAngle(angle);

        if (getY() <= getStage().getHeight()) {
            applyPhysics(delta);
        }
        else {
            remove();
        }

        /**
         * ATTENTION
         * this method does not provide any security mechanism
         */
        //setWorldBounds();

    }
}
