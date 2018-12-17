package com.github.lhrb.nemo.actors.enemies.endboss;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.enemies.Enemy;
import com.github.lhrb.nemo.actors.weapons.Weapon;

public abstract class EndBoss extends Enemy {

    protected boolean destroyed = false;

    private Sections sections[];
    private Weapon weapons[];

    private float moveAngle;
    public EndBoss(Stage stage) {
        super(stage);
        setCharacteristics(stage);
    }


    public boolean isDestroyed() {
        return destroyed;
    }
}
