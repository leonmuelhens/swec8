package com.github.lhrb.nemo.actors.enemies;

import com.github.lhrb.nemo.util.AnimationLoader;

public class EnemyTwo extends Enemy {

    /**
     * Simple Enemie Implementation
     * @author Thorsten Rösler
     *
     */

    public void setCharacteristics() {
        
        setAnimation(AnimationLoader.loadTexture("gegner2.png"));
        //setRotation(180);
        setAcceleration(30);
        setSpeedMax(30);
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
