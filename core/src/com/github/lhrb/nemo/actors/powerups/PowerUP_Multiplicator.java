package com.github.lhrb.nemo.actors.powerups;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.util.AnimationLoader;

public class PowerUP_Multiplicator extends PowerUP {
        public PowerUP_Multiplicator(Stage stage) {
            super(stage);
        }

        public void setCharacteristics(Stage stage) {
            setAnimation(AnimationLoader.loadTexture("powerup_multiplicator.png"));
            setAcceleration(1000);
            setSpeedMax(75);
            setDeceleration(1000000);
            setShapePolygon(8);
        }

        @Override
        public void collision() {
            for (Actor a : getStage().getActors()) {
                if (a instanceof Player) {
                    ((Player) a).powerupIcon = new ActivePowerUPIcon("Multiplicator",getStage());
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
