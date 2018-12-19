package com.github.lhrb.nemo.actors.weapons;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.util.PropertyListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Weapon implements PropertyListener {

    protected float cooldown;
    protected float cooldownTimer;
    protected Stage stage;
    public PropertyChangeSupport changes;


    public Weapon(float cooldown, Stage stage) {        
        this.cooldown = cooldown;
        this.cooldownTimer = cooldown;
        changes = new PropertyChangeSupport(this);
        this.stage = stage;
    }

    public Weapon(float cooldown, float initialCooldownTime, Stage stage)
    {
        this(cooldown, stage);
        this.cooldownTimer = initialCooldownTime;
        changes = new PropertyChangeSupport(this);

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

    public float getCooldown() {
        return cooldown;
    }


    public void act(float delta) {
        /*
        if(cooldownTimer != 0) {
            changes.firePropertyChange("shottimer",cooldownTimer,(cooldownTimer+delta));
        } else {
            changes.firePropertyChange("shottimer",cooldownTimer,cooldown);
        }*/

        cooldownTimer += delta;
        //changes.firePropertyChange("shottimer",remainingPercentage,cooldownTimer);

        /*if (System.currentTimeMillis() - lastUpdate > 25L) {

            remainingPercentage -= 0.01f;
            lastUpdate = System.currentTimeMillis();

            if (remainingPercentage <= 0.0f) {
                remainingPercentage = 1.0f;
            }
        }*/

    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }


}
