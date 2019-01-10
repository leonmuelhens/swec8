package com.github.lhrb.nemo.actors.weapons;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.powerups.CType;
import com.github.lhrb.nemo.actors.shots.Laser;
import com.github.lhrb.nemo.util.SoundManager;

public class WeaponLaser extends Weapon {
    public WeaponLaser(Stage stage) {
        super(1.0f, stage);
    }

    public WeaponLaser(float cooldown, Stage stage) {
        super(cooldown, stage);
    }

    @Override
    public void fire(float x, float y, float angle) {
        if (isReady()) {
            new Laser(x, y, angle, stage);
            resetCooldownTimer();

            SoundManager.getInstance().playSound("laser");
        }
    }


}
