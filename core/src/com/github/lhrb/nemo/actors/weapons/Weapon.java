package com.github.lhrb.nemo.actors.weapons;

import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class Weapon {

    protected float cooldown;
    protected float cooldownTimer;
    protected Stage stage;

    public Weapon(float cooldown, Stage stage) {        
        this.cooldown = cooldown;
        this.cooldownTimer = cooldown;
        this.stage = stage;
    }

    public Weapon(float cooldown, float initialCooldownTime, Stage stage)
    {
        this(cooldown, stage);
        this.cooldownTimer = initialCooldownTime;
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


    public void act(float delta) {
        cooldownTimer += delta;
    }
}
