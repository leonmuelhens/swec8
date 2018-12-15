package com.github.lhrb.nemo.actors.weapons;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.github.lhrb.nemo.KillingNemo;


public abstract class Weapon extends Actor{

    protected float cooldown;
    private float cooldownTimer;

    public Weapon(float cooldown) {
        super();
        KillingNemo.getGameStage().addActor(this);
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

    @Override
    public void act(float delta) {
        super.act(delta);

        cooldownTimer += delta;
        //System.out.println("hier");
    }
}
