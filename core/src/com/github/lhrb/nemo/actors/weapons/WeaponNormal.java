package com.github.lhrb.nemo.actors.weapons;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.shots.SingleShot;
import com.github.lhrb.nemo.util.SoundManager;

public class WeaponNormal extends Weapon {
    public WeaponNormal(Stage stage) {
        super(0.4f, stage);
    }

    public WeaponNormal(float cooldown, Stage stage) {
        super(cooldown, stage);
    }

    @Override
    public void fire(float x, float y, float angle) {
        if (isReady()) {
            new SingleShot(x, y, angle, stage);
            resetCooldownTimer();

            SoundManager.getInstance().playSound("laser");
        }
    }
}
