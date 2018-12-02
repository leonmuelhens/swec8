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
public class PowerUP_Bombe extends PowerUP {
    public PowerUP_Bombe(Stage stage) {
        super(stage);
    }

    public void setCharacteristics(Stage stage) {
        setAnimation(AnimationLoader.loadTexture("powerup_bombe.png"));
        setAcceleration(1000);
        setSpeedMax(75);
        setDeceleration(1000000);
        setShapePolygon(8);
    }

    @Override
    public void collision() {
        for (Actor a : getStage().getActors()) {
            if (a instanceof Player) {
                ((Player) a).powerupIcon = new ActivePowerUPIcon("Bombe",getStage());
                ((Player) a).powerup = this;
            }
        }
        remove();
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