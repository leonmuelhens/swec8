package com.github.lhrb.nemo.actors.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.shots.SingleShot;
import com.github.lhrb.nemo.util.AnimationLoader;

public class WeaponSpread extends Weapon {
    Sound sound;

    public WeaponSpread(Stage stage) {
        super(stage, 0.6f);
        setAnimation(AnimationLoader.loadTexture("IconSpread.png"));
        sound = Gdx.audio.newSound(Gdx.files.internal("sound/laser.ogg"));
    }

    @Override
    public void fire(float x, float y, float angle) {
        if (isReady()) {
            new SingleShot(x, y, getStage(), angle-45);
            new SingleShot(x, y, getStage(), angle);
            new SingleShot(x, y, getStage(), angle+45);
            resetCooldownTimer();

            sound.play(0.3f);
        }
    }
}
