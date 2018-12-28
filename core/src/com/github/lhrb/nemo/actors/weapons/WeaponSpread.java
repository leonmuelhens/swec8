package com.github.lhrb.nemo.actors.weapons;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.powerups.CType;
import com.github.lhrb.nemo.actors.shots.SingleShot;
import com.github.lhrb.nemo.util.SoundManager;

public class WeaponSpread extends Weapon {
    protected float angleModifier;

    public WeaponSpread(Stage stage) {
        super(0.6f, stage);
        angleModifier = 25;
    }

    public WeaponSpread(float cooldown, Stage stage) {
        super(cooldown, stage);
    }

    public WeaponSpread(float cooldown,float angleModifier, Stage stage) {
        super(cooldown, stage);
        this.angleModifier = angleModifier;
    }

    @Override
    public void fire(float x, float y, float angle) {
        if (isReady()) {
            new SingleShot(x, y, angle - angleModifier, stage);
            new SingleShot(x, y, angle, stage);
            new SingleShot(x, y, angle + angleModifier, stage);
            resetCooldownTimer();

            SoundManager.getInstance().playSound("laser");
        }
    }

    @Override
    public CType getType() {
        return CType.Spread;
    }
}
