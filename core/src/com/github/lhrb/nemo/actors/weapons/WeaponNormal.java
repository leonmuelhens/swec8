package com.github.lhrb.nemo.actors.weapons;

import com.github.lhrb.nemo.actors.shots.SingleShot;
import com.github.lhrb.nemo.util.SoundManager;

public class WeaponNormal extends Weapon {
    public WeaponNormal() {
        super(0.4f);
    }

    public WeaponNormal(float cooldown) {
        super(cooldown);
    }

    @Override
    public void fire(float x, float y, float angle) {
        if (isReady()) {
            new SingleShot(x, y, angle);
            resetCooldownTimer();

            SoundManager.getInstance().playSound("laser");
        }
    }
}
