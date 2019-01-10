package com.github.lhrb.nemo.actors.weapons;

import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class WeaponSalve extends Weapon {

    private int salveRepetition;
    private int shotsFiredinSalve;

    private final float outerCooldown;
    private final float innerSalveCooldown;

    public WeaponSalve(float cooldown, float innerSalveCooldown, int salveRepetition, Stage stage) {
        super(cooldown, stage);

        this.outerCooldown = cooldown;
        this.innerSalveCooldown = innerSalveCooldown;
        this.salveRepetition = salveRepetition;
    }

    public WeaponSalve(float cooldown, float initialCooldown, float innerSalveCooldown, int salveRepetition, Stage stage) {
        super(cooldown, initialCooldown, stage);

        this.outerCooldown = cooldown;
        this.innerSalveCooldown = innerSalveCooldown;
        this.salveRepetition = salveRepetition;
    }

    @Override
    public void resetCooldownTimer() {
        if (shotsFiredinSalve < salveRepetition) {
            shotsFiredinSalve++;
            cooldown = innerSalveCooldown;
            super.resetCooldownTimer();
        } else {
            shotsFiredinSalve = 0;
            cooldown = outerCooldown;
            super.resetCooldownTimer();
        }
    }
}
