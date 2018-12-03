package com.github.lhrb.nemo.actors.powerups;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.util.AnimationLoader;

public class PowerUPStar extends PowerUP {
        public PowerUPStar(Stage stage) {
            super(stage);
        }

        public void setCharacteristics(Stage stage) {
            setAnimation(AnimationLoader.get().texture("powerup_star.png"));
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
