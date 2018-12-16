package com.github.lhrb.nemo.actors.enemies;

import com.github.lhrb.nemo.actors.EnemyActor;
import com.github.lhrb.nemo.actors.weapons.Weapon;
import com.github.lhrb.nemo.actors.weapons.WeaponNormal;
import com.github.lhrb.nemo.util.AnimationLoader;

public class EnemyOne extends EnemyActor {

    /**
     * Simple Enemie Implementation
     * @author Thorsten Rösler
     *
     */

    private Weapon weaponleft;
    private Weapon weaponright;
    private float fireRate = 3f;
    
    public void setCharacteristics() {
        setAnimation(AnimationLoader.get().texture("gegner1.png"));
        setRotation(180);
        setAcceleration(1000);
        setSpeedMax(75);
        setDeceleration(0);
        setShapePolygon(8);

        hp = 2;
        scoreValue = 1;
        
      
        weaponleft = new WeaponNormal(fireRate);
        weaponright = new WeaponNormal(fireRate);
    }

        /**
         * input handling
         */
        @Override
        public void act(float delta) {
            super.act(delta);
            weaponleft.act(delta);
            weaponright.act(delta);
            accelerationAtAngle(270);

            applyObjectPhysics(delta);
            
            
            if(getElapsedTime() > 2f) { // muss man mal testen ob sich das bei unterschiedlichen rechner gleich verhält
                weaponleft.fire(getX() + (getWidth() / 2) - 20, getY() - 30, 270);
                weaponright.fire(getX() + (getWidth() / 2) + 20, getY() - 30, 270);
            }
            
            /**
            if (getStage() != null && getY() + getHeight() - 10 < getStage().getHeight()) {
                weaponleft.fire(getX() + (getWidth() / 2) - 20, getY() - 30, 270);
                weaponright.fire(getX() + (getWidth() / 2) + 20, getY() - 30, 270);
            }
            */
        }

}
