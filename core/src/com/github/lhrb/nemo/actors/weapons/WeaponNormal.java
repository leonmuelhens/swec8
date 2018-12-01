package com.github.lhrb.nemo.actors.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.shots.SingleShot;
import com.github.lhrb.nemo.util.AnimationLoader;

public class WeaponNormal extends Weapon {
    public WeaponNormal(Stage stage) {
        super(stage, 0.6f);
        sound = Gdx.audio.newSound(Gdx.files.internal("sound/laser.ogg"));
    }

    @Override
    public void fire(float x, float y, float angle) {
        if (isReady()) {
            new SingleShot(x, y, getStage(), angle);
            resetCooldownTimer();

            sound.play(0.3f);
        }
    }
}
