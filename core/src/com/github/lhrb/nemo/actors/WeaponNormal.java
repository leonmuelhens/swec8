package com.github.lhrb.nemo.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.util.AnimationLoader;

public class WeaponNormal extends Weapon {
    public WeaponNormal(Stage stage, float cooldown) {
        super(stage, cooldown);
        setAnimation(AnimationLoader.loadTexture("IconNormal.png"));
    }

    @Override
    public void fire(float x, float y, float angle) {
        if (isReady()) {
            new Shot(x, y, getStage(), angle);
            resetCooldownTimer();
        }
    }
}
