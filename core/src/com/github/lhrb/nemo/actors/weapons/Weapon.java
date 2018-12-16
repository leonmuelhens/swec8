package com.github.lhrb.nemo.actors.weapons;

public abstract class Weapon {

    protected float cooldown;
    protected float cooldownTimer;

    public Weapon(float cooldown) {        
        this.cooldown = cooldown;
        this.cooldownTimer = cooldown;
    }

    public Weapon(float cooldown, float initialCooldownTime)
    {
        this(cooldown);

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
