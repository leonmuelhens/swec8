package com.github.lhrb.nemo.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.util.AnimationLoader;

public class WeaponSpread extends Weapon {
    public WeaponSpread(Stage stage, float cooldown) {
        super(stage, cooldown);
        setAnimation(AnimationLoader.loadTexture("IconSpread.png"));
    }

    @Override
    public void fire(float x, float y, float angle) {
        if (isReady()) {
            new Shot(x, y, getStage(), angle-45);
            new Shot(x, y, getStage(), angle);
            new Shot(x, y, getStage(), angle+45);
            resetCooldownTimer();
        }
    }
}
