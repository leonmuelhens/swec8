/**
 *
 */
package com.github.lhrb.nemo.actors.enemies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.actors.MultiPartActor;
import com.github.lhrb.nemo.actors.Section;
import com.github.lhrb.nemo.actors.shots.Laser;
import com.github.lhrb.nemo.actors.shots.Shots;
import com.github.lhrb.nemo.actors.shots.Torpedo;
import com.github.lhrb.nemo.actors.weapons.Weapon;
import com.github.lhrb.nemo.actors.weapons.WeaponLaser;
import com.github.lhrb.nemo.util.AnimationLoader;


public class Hai extends MultiPartActor {

    private float moveAngle;
    private ArrayList<Weapon> weapons;
    private Vector3 moveArea;
    private boolean chargeFront, chargeBack;
    private float stoptime;

    public Hai(Stage stage){
        super(stage);
        init();
    }

    public Hai(float x, float y, Stage stage) {
        super(x,y,stage);
        init();
    }

    private void init() {
        addPart(new Section(0, this, -3, 60, 10, 100,
                AnimationLoader.get().texture("hai_mitte.png")));
        addPart(new Section(0, this, -4, 36, 10, 100,
                AnimationLoader.get().texture("hai_finne.png")));
        addPart(new Section(0, this, 0, 0, 10, 100,
                AnimationLoader.get().texture("hai_kopf.png")));
        addPart(new Section(0, this, 16, 153, 10, 100,
                AnimationLoader.get().texture("hai_schwanzflosse.png")));
        addPart(new Section(0, this, -36, 24, 10, 100,
                AnimationLoader.get().texture("hai_flosse_links.png")));
        addPart(new Section(0, this, 49, 30, 10, 100,
                AnimationLoader.get().texture("hai_flosse_rechts.png")));

        setRotation(0);
        setAcceleration(1000);
        setSpeedMax(75);
        setDeceleration(4000);

        weapons = new ArrayList<Weapon>();
        weapons.add(new WeaponBigLaser(this.getStage(),this));
        weapons.add(new WeaponRotatingLaser(this.getStage(), this));
        weapons.add(new WeaponSmallLaser(this.getStage()));
        weapons.add(new WeaponTorpedo(this.getStage()));

        moveArea = new Vector3(50f, 550f, 410f);
        chargeFront = false;
        chargeBack = false;

    }


    @Override
    public void act(float delta) {
        super.act(delta);

        if(chargeFront) {
            if (getY() < 100f) {
                chargeFront = false;
                chargeBack = true;
                moveAngle = 90;
            }
        }
        else if (chargeBack) {
            if (getY() > moveArea.z) {
                chargeBack = false;
                setAcceleration(1000);
                setSpeedMax(75);
            }
        }
        else if (stoptime > 0) {
            setSpeedMax(0);
            stoptime -= delta;
            if (stoptime < 0) {
                setSpeedMax(75);
            }
        }
        else {
            if (getY() > moveArea.z) {
                moveAngle = 270;
            }
            else if (moveAngle == 270) {
                moveAngle = 0;
            }
            else if (moveAngle == 0 && getX() > moveArea.y) {
                moveAngle = 180;
            }
            else if (moveAngle == 180 && getX() < moveArea.x) {
                moveAngle = 0;
            }

            if (getElapsedTime() > 2f) {
                for (Weapon w: weapons) {
                    w.act(delta);
                    w.fire(getX()+20,getY()-400,270);
                }
            }

            if (getElapsedTime() > 2f && (int) getElapsedTime() % 10 == 0) {
                chargeFront = true;

                setAcceleration(5000);
                setSpeedMax(800);
                float dx = getX() - GameManager.get().getPlayer().getX();
                float dy = getY()+350 - GameManager.get().getPlayer().getY();
                moveAngle = 270 - (float)((Math.atan(dx/dy) * (180/Math.PI)) * (0.1 + Math.random() * (1 - 0.8)));
            }
        }

        accelerationAtAngle(moveAngle);

        applyObjectPhysics(delta);

    }

    private void chargeBigShot(float cooldown) {
        cooldown = (cooldown - 0.8f) * 5;
        for (Section s:
             getPartCollection()) {
            s.setColor(1-cooldown,1,1-cooldown,1);
        }
    }
    private void stop(float time) {
        stoptime = time;
    }

    private class WeaponBigLaser extends Weapon {

        private Hai parentHai;
        WeaponBigLaser(Stage stage, Hai hai) {
            super(11,8, stage);
            parentHai = hai;
        }

        @Override
        public void fire(float x, float y, float angle) {
            if (getCooldown() > 0.8f)
                parentHai.chargeBigShot(getCooldown());

            if (isReady()) {
                parentHai.stop(1);
                new BigLaser(x-82,y-100,angle, stage);

                resetCooldownTimer();
                parentHai.chargeBigShot(0);
            }
        }

        private class BigLaser extends Shots {

            BigLaser(float x, float y, float angle, Stage stage) {
                super(x, y, angle, stage);

                setAnimation(AnimationLoader.get().texture("SchussLaserBig.png"));
                setSpeedMax(0);
                //setAcceleration(30000);
                setShapePolygon(8);
            }

            @Override
            public void act(float delta) {
                super.act(delta);
            }
        }

    }

    private class WeaponRotatingLaser extends Weapon {
        private Hai parentHai;
        WeaponRotatingLaser(Stage stage, Hai hai) {
            super(10,8, stage);
            parentHai = hai;
        }

        @Override
        public void fire(float x, float y, float angle) {
            if (isReady()) {
                parentHai.stop(2);
                new RotatingLaser(x,y,angle+45, stage);

                resetCooldownTimer();
            }
        }
        private class RotatingLaser extends Shots {
            RotatingLaser(float x, float y, float angle, Stage stage) {
                super(x, y, angle, stage);

                setAnimation(AnimationLoader.get().texture("SchussLaserHalb.png"));
                setSpeedMax(0);
                setAcceleration(0);
                setShapePolygon(8);
            }
            @Override
            public void act(float delta) {
                super.act(delta);
                accelerationAtAngle(0);
                rotateBy(-1);
                applyPhysics(delta);
            }

            @Override
            public void perish() {
                //remove actor after 2 sec
                addAction(Actions.delay(2));
                addAction(Actions.after(Actions.removeActor()));
            }
        }
    }

    private class WeaponTorpedo extends Weapon {

        WeaponTorpedo(Stage stage) {
            super(4,0, stage);
        }

        @Override
        public void fire(float x, float y, float angle) {
            if (isReady()) {
                new Torpedo(x-10,y+350,angle, stage);
                new Torpedo(x+10,y+350,angle, stage);
                resetCooldownTimer();
            }
        }

    }

    private class WeaponSmallLaser extends Weapon {

        private int salveRepetition = 2;
        private int shotsFiredinSalve = 0;

        WeaponSmallLaser(Stage stage) {
            super(13, 0, stage);
        }

        @Override
        public void fire(float x, float y, float angle) {
            if (isReady()) {
                float dx = x - GameManager.get().getPlayer().getX();
                float dy = y+350 - GameManager.get().getPlayer().getY();
                new LaserSmall(x,y+350,angle - (float) (Math.atan(dx/dy) * (180/Math.PI)),stage);
                resetCooldownTimer();
            }
        }

        @Override
        public void resetCooldownTimer() {
            if (shotsFiredinSalve < salveRepetition) {
                shotsFiredinSalve++;
                cooldown = 0.3f;
                super.resetCooldownTimer();
            } else {
                shotsFiredinSalve = 0;
                cooldown = 12;
                super.resetCooldownTimer();
            }
        }

        private class LaserSmall extends Shots {

            LaserSmall(float x, float y, float angle, Stage stage) {
                super(x, y, angle, stage);

                setAnimation(AnimationLoader.get().texture("SchussLaserSmall.png"));
                setSpeedMax(1000);
                setAcceleration(30000);
                setShapePolygon(8);
            }

            @Override
            public void act(float delta) {
                super.act(delta);
            }

        }
    }
}
