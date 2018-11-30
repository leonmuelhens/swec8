package com.github.lhrb.nemo.actors.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.shots.Laser;
import com.github.lhrb.nemo.util.AnimationLoader;

public class WeaponLaser extends Weapon {
    public WeaponLaser(Stage stage) {
        super(stage, 2.0f);
        setAnimation(AnimationLoader.loadTexture("IconLaser.png"));
        sound = Gdx.audio.newSound(Gdx.files.internal("sound/laser.ogg"));
    }

    @Override
    public void fire(float x, float y, float angle) {
        if (isReady()) {
            new Laser(x, y, getStage(), angle);
            resetCooldownTimer();

            sound.play(0.3f);
        }
    }
}
