package com.github.lhrb.nemo.actors.weapons;

import com.github.lhrb.nemo.actors.shots.Laser;
import com.github.lhrb.nemo.util.SoundManager;

public class WeaponLaser extends Weapon {
    public WeaponLaser() {
        super(2.0f);
    }

    public WeaponLaser(float cooldown) {
        super(cooldown);
    }

    @Override
    public void fire(float x, float y, float angle) {
        if (isReady()) {
            new Laser(x, y, angle);
            resetCooldownTimer();

            SoundManager.getInstance().playSound("laser");
        }
    }
}
