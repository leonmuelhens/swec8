package com.github.lhrb.nemo.actors.enemies;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.EnemyActor;
import com.github.lhrb.nemo.actors.weapons.Weapon;
import com.github.lhrb.nemo.actors.weapons.WeaponNormal;
import com.github.lhrb.nemo.util.AnimationLoader;

public class EnemyOne extends EnemyActor {

    public EnemyOne(Stage stage) {
        super(stage);
        // TODO Auto-generated constructor stub
    }

    /**
     * Simple Enemie Implementation
     * @author Thorsten Rösler
     *
     */

    private Weapon weaponleft;
    private Weapon weaponright;
    private float fireRate;
    
    public void setCharacteristics() {
        setAnimation(AnimationLoader.get().texture("enemies/gegner1.png"));
        setRotation(180);
        setAcceleration(1000);
        setSpeedMax(75);
        setDeceleration(0);
        setShapePolygon(8);

        setHp(2);
        setScoreValue(1);
        fireRate = 3f;      
        weaponleft = new WeaponNormal(fireRate, getStage());
        weaponright = new WeaponNormal(fireRate, getStage());
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
        }

}
