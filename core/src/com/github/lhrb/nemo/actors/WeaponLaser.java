package com.github.lhrb.nemo.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.util.AnimationLoader;

public class WeaponLaser extends Weapon {
    public WeaponLaser(Stage stage, float cooldown) {
        super(stage, cooldown);
        setAnimation(AnimationLoader.loadTexture("IconLaser.png"));
    }

    @Override
    public void fire(float x, float y, float angle) {
        if (isReady()) {
            //TODO
            resetCooldownTimer();
        }
    }
}
