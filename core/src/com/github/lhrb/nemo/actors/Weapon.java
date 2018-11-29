package com.github.lhrb.nemo.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class Weapon extends ActorPrefab {

    private final float cooldown;
    private static float cooldownTimer;

    public Weapon(Stage stage, float cooldown) {
        super(0, 0, stage); // TODO: Waffenslot Grafik

        this.cooldown = cooldown;
        this.cooldownTimer = cooldown;
    }

    public boolean isReady() {
        return cooldownTimer >= cooldown;
    }

    public void resetCooldownTimer() {
        cooldownTimer = 0;
    }

    /**
     * Fires the weapon if cooldown-time elapsed
     * @param x x-Pos of shot origin
     * @param y y-Pos of shot origin
     * @param angle Angle of shot direction. Regular: Player shoots 90 (Up), Enemies shoot 270 (Down)
     */
    public abstract void fire(float x, float y, float angle);

    @Override
    public void act(float delta) {
        super.act(delta);

        cooldownTimer += delta;
    }
}
