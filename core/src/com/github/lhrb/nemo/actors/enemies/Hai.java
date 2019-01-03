/**
 *
 */
package com.github.lhrb.nemo.actors.enemies;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.MultiPartActor;
import com.github.lhrb.nemo.actors.Section;
import com.github.lhrb.nemo.actors.weapons.Weapon;
import com.github.lhrb.nemo.util.AnimationLoader;


public class Hai extends MultiPartActor {

    private float moveAngle;
    private ArrayList<Weapon> weapons;
    private Vector3 moveArea;
    private boolean chargeFront, chargeBack;

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
        } else if (chargeBack) {
            if (getY() > moveArea.z) {
                chargeBack = false;
                setAcceleration(1000);
                setSpeedMax(75);
            }
        }  else {
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
                    w.fire(getX()+108,getY()-(getHeight()/2),270);
                }
            }

            if (getElapsedTime() > 2f && (int) getElapsedTime() % 10 == 0) {
                chargeFront = true;

                setAcceleration(5000);
                setSpeedMax(800);
                moveAngle = 270;
            }
        }

        accelerationAtAngle(moveAngle);

        applyObjectPhysics(delta);

    }

}
