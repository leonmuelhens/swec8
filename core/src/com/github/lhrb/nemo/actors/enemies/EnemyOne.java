package com.github.lhrb.nemo.actors.enemies;

import com.github.lhrb.nemo.util.AnimationLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class EnemyOne extends Enemy {
    public EnemyOne(Stage stage) {
        super(stage);
    }
    /**
     * Simple Enemie Implementation
     * @author Thorsten Rösler
     *
     */


    public void setCharacteristics(Stage stage) {
        setAnimation(AnimationLoader.loadTexture("gegner1.png"));
        setRotation(180);
        setAcceleration(1000);
        setSpeedMax(75);
        setDeceleration(1000000);
        setShapePolygon(8);
    }

        /**
         * input handling
         */
        @Override
        public void act(float delta) {
            super.act(delta);
            accelerationAtAngle(270);

            applyObjectPhysics(delta);
        }

}
