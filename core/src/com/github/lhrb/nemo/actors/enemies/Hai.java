/**
 *
 */
package com.github.lhrb.nemo.actors.enemies;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.actors.MultiPartActor;
import com.github.lhrb.nemo.actors.Section;
import com.github.lhrb.nemo.actors.shots.Bomb;
import com.github.lhrb.nemo.actors.shots.Torpedo;
import com.github.lhrb.nemo.actors.weapons.Weapon;
import com.github.lhrb.nemo.actors.weapons.WeaponSpread;
import com.github.lhrb.nemo.screen.LevelScreen;
import com.github.lhrb.nemo.util.AnimationLoader;


public class Hai extends MultiPartActor {

    private float moveAngle;
    private ArrayList<Weapon> weapons;
    private Vector3 moveArea;

    public Hai(Stage stage){
        super(stage);
        init();
    }

    public Hai(float x, float y, Stage stage) {
        super(x,y,stage);
        init();
    }

    private void init() {

        setRotation(0);
        setAcceleration(1000);
        setSpeedMax(75);
        setDeceleration(0);

        weapons = new ArrayList<Weapon>();

        moveArea = new Vector3(50f, 550f, 500f);

    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.actors.MultiPartActor#handleCollision(com.github.lhrb.nemo.actors.Section)
     */
    @Override
    public void handleCollision(Section section) {
        //prevent middle part getting dmg
        if(getPartSize() > 1 && section.getID() != 1) {
            if(section.getDmg()) {
                removePart(section);
                section.perish();
            }
        }else if(getPartSize() == 1) {
            if(section.getDmg()) {
                removePart(section);
                section.perish();
                addAction(Actions.removeActor());
                // first level over
                // bad practice just for testing
                try {
                    ((LevelScreen)KillingNemo.getActiveScreen()).removeScreen();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



    @Override
    public void act(float delta) {
        super.act(delta);

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

        accelerationAtAngle(moveAngle);

        applyObjectPhysics(delta);

        if (getElapsedTime() > 2f) {
            for (Weapon w: weapons) {
                w.act(delta);
                w.fire(getX()+108,getY()-(getHeight()/2),270);
            }
        }
    }

}
