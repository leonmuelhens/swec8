package com.github.lhrb.nemo.actors.powerups;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.util.AnimationLoader;
/**
 * Simple PowerUP Implementation
 * @author Thorsten Rösler
 *
 */
public class PowerUPBomb extends PowerUP {
    public PowerUPBomb(Stage stage) {
        super(stage);
    }

    public void setCharacteristics(Stage stage) {
        setAnimation(AnimationLoader.loadTexture("powerup_bombe.png"));
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