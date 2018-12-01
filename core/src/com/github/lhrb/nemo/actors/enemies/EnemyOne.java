package com.github.lhrb.nemo.actors.enemies;

import com.github.lhrb.nemo.actors.weapons.Weapon;
import com.github.lhrb.nemo.actors.weapons.WeaponNormal;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class EnemyOne extends Enemy {
    public EnemyOne(Stage stage) {
        super(stage);
    }
    /**
     * Simple Enemie Implementation
     * @author Thorsten Rösler
     *
     */

    private Weapon weaponleft;
    private Weapon weaponright;

    public void setCharacteristics(Stage stage) {
        setAnimation(AnimationLoader.loadTexture("gegner1.png"));
        setRotation(180);
        setAcceleration(1000);
        setSpeedMax(75);
        setDeceleration(1000000);
        setShapePolygon(8);

        weaponleft = new WeaponNormal(stage);
        weaponright = new WeaponNormal(stage);
    }

        /**
         * input handling
         */
        @Override
        public void act(float delta) {
            super.act(delta);
            accelerationAtAngle(270);

            applyObjectPhysics(delta);

            weaponleft.fire(getX()+(getWidth()/2)-20,getY()-30, 270);
            weaponright.fire(getX()+(getWidth()/2)+20,getY()-30, 270);
        }

}
