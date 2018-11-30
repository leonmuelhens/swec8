package com.github.lhrb.nemo.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.util.AnimationLoader;

public class EnemyThree extends ActorPrefab {

    /**
     * Simple Enemie Implementation
     * @author Thorsten Rösler
     *
     */


        public EnemyThree(float x, float y, Stage stage) {
            super(x,y,stage);
            setAnimation(AnimationLoader.loadTexture("gegner3.png"));
            //setRotation(180);
            setAcceleration(30);
            setSpeedMax(30);
            setDeceleration(1000000);
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
