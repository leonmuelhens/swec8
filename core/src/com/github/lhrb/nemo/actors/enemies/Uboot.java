/**
 * 
 */
package com.github.lhrb.nemo.actors.enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.actors.MultiPartActor;
import com.github.lhrb.nemo.actors.Section;
import com.github.lhrb.nemo.actors.powerups.CType;
import com.github.lhrb.nemo.actors.shots.Bomb;
import com.github.lhrb.nemo.actors.shots.Torpedo;
import com.github.lhrb.nemo.actors.weapons.Weapon;
import com.github.lhrb.nemo.actors.weapons.WeaponSalve;
import com.github.lhrb.nemo.actors.weapons.WeaponSpread;
import com.github.lhrb.nemo.screen.LevelScreen;
import com.github.lhrb.nemo.util.AnimationLoader;

import java.util.ArrayList;


public class Uboot extends MultiPartActor {
    
    private float moveAngle;
    private ArrayList<Weapon> weapons;
    private Vector3 moveArea;
    
    public Uboot(Stage stage){
        super(stage);
        init();
    }
    
    public Uboot(float x, float y, Stage stage) {
        super(x,y,stage);
        init();
    }
    
    private void init() {
        weapons = new ArrayList<Weapon>(); 
        weapons.add( new WeaponSpreadSalve(getStage()) );
        weapons.add( new WeaponTorpedo(getStage()) );
        weapons.add( new WeaponBombdrop(getStage()) );

        addPart(new Section(0, this, 0, 0, 15, 100,
                AnimationLoader.get().texture("uboot_part1.png")));
        addPart(new Section(1, this, 37, 0, 15, 100,
                AnimationLoader.get().texture("uboot_part2.png")));
        addPart(new Section(2, this, 70, 0, 15, 100,
                AnimationLoader.get().texture("uboot_part3.png")));
        addPart(new Section(3, this, 142, 0, 15, 100,
                AnimationLoader.get().texture("uboot_part4.png")));
        
        setRotation(0);
        setAcceleration(1000);
        setSpeedMax(75);
        setDeceleration(0);
        
        moveArea = new Vector3(50f, 550f, 500f);

    }
    
    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.actors.MultiPartActor#handleCollision(com.github.lhrb.nemo.actors.Section)
     */
    /*@Override
    public void handleCollision(Section section) {
        //prevent middle part getting dmg
        if(getPartSize() > 1 && section.getID() != 1) {
            if(section.getDmg()) {
                removePart(section);
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
    }*/
    
    
    
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
    
    private class WeaponSpreadSalve extends WeaponSalve {

        private int salveRepetition = 10;
        private int shotsFiredinSalve = 0;

        private WeaponSpread weapon;

        public WeaponSpreadSalve(float cooldown, Stage stage) {
            super(cooldown, 0.1f, 10, stage);
            weapon = new WeaponSpread(0,45,stage);
        }

        public WeaponSpreadSalve(Stage stage) {
            super(12, 0.1f, 10, stage);
            weapon = new WeaponSpread(0,45,stage);
        }

        @Override
        public void fire(float x, float y, float angle) {
            if (isReady()) {
                weapon.fire(x,y,angle);
                resetCooldownTimer();
            }
        }
    }

    private class WeaponTorpedo extends Weapon {

        public WeaponTorpedo(Stage stage) {
            super(8,0, stage);
        }

        @Override
        public void fire(float x, float y, float angle) {
            if (isReady()) {
                new Torpedo(x,y,angle, stage);
                resetCooldownTimer();
            }
        }

    }

    private class WeaponBombdrop extends Weapon {

        public WeaponBombdrop(Stage stage) {
            super(10,5, stage);
        }

        @Override
        public void fire(float x, float y, float angle) {
            if (isReady()) {
                new Bomb(x,y,angle, stage);
                resetCooldownTimer();
            }
        }

    }



}
