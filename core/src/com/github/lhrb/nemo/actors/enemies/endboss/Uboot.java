package com.github.lhrb.nemo.actors.enemies.endboss;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.CollisionEvent;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.actors.enemies.endboss.EndBoss;
import com.github.lhrb.nemo.actors.enemies.endboss.Sections;
import com.github.lhrb.nemo.actors.shots.Bomb;
import com.github.lhrb.nemo.actors.shots.Torpedo;
import com.github.lhrb.nemo.actors.weapons.Weapon;
import com.github.lhrb.nemo.actors.weapons.WeaponSpread;
import com.github.lhrb.nemo.util.AnimationLoader;

public class Uboot extends EndBoss {

    private Sections sections[];
    private Weapon weapons[];

    private float moveAngle;

    public Uboot() {
        super();
    }

    @Override
    protected void setCharacteristics() {
        setAnimation(AnimationLoader.get().texture("uboot.png"));

        weapons = new Weapon[3];
        weapons[0] = new WeaponSalve();
        weapons[1] = new WeaponTorpedo();
        weapons[2] = new WeaponBombdrop();

        sections = new Sections[3];
        sections[0] = new Sections();
        sections[1] = new Sections();
        sections[2] = new Sections();

        setRotation(0);
        setAcceleration(1000);
        setSpeedMax(75);
        setDeceleration(1000000);
        setShapePolygon(8);

        hp = 10;
        scoreValue = 100;
    }

    @Override
    public void collision(CollisionEvent col) {
        if (!sections[0].destroyed) {
            sections[0].collision(col);
        }
        else if (!sections[1].destroyed) {
            sections[1].collision(col);
        }
        else if (!sections[2].destroyed) {
            sections[2].collision(col);
        }
        else {
            super.collision(col);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (getY() > getStage().getHeight() - (getStage().getHeight()/8)) {
            moveAngle = 270;
        }
        else if (moveAngle == 270) {
            moveAngle = 0;
        }
        else if (moveAngle == 0 && getX() > getStage().getWidth() - (getStage().getWidth()/8) - this.getWidth()) {
            moveAngle = 180;
        }
        else if (moveAngle == 180 && getX() < getStage().getWidth()/8) {
            moveAngle = 0;
        }

        accelerationAtAngle(moveAngle);

        applyObjectPhysics(delta);

        if (getStage() != null && getY()+20 < getStage().getHeight()) {
            for (Weapon w:
                 weapons) {
                w.fire(getX()+(getWidth()/2),getY()-(getHeight()/2),270);
            }
        }
    }

    @Override
    public void setPosition (float x, float y) {
        super.setPosition(x,y);
        setSectionPosition(x,y);
    }

    protected void setSectionPosition(float x, float y)
    {
        for (Sections s:
                sections) {
            s.setPosition(x,y);
        }
    }



    private class WeaponSalve extends WeaponSpread {

        private int salveRepetition = 10;

        private int shotsFiredinSalve = 0;

        public WeaponSalve(float cooldown) {
            super(cooldown);
        }

        public WeaponSalve() {
            super(12, 45);
        }

        @Override
        public void resetCooldownTimer() {
            if (shotsFiredinSalve < salveRepetition) {
                shotsFiredinSalve++;
                cooldown = 0.1f;
                super.resetCooldownTimer();
            }
            else {
                shotsFiredinSalve = 0;
                cooldown = 12;
                super.resetCooldownTimer();
            }
        }
    }

    private class WeaponTorpedo extends Weapon {

        public WeaponTorpedo() {
            super(8,0);
        }

        @Override
        public void fire(float x, float y, float angle) {
            if (isReady()) {
                new Torpedo(x,y,angle);
                resetCooldownTimer();
            }
        }
    }

    private class WeaponBombdrop extends Weapon {

        public WeaponBombdrop() {
            super(10,5);
        }

        @Override
        public void fire(float x, float y, float angle) {
            if (isReady()) {
                new Bomb(x,y,angle);
                resetCooldownTimer();
            }
        }
    }
}
