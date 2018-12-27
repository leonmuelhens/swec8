package com.github.lhrb.nemo.actors.enemies;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.EnemyActor;
import com.github.lhrb.nemo.actors.weapons.Weapon;
import com.github.lhrb.nemo.actors.weapons.WeaponNormal;
import com.github.lhrb.nemo.util.AnimationLoader;

public class EnemyThree extends EnemyActor {

    public EnemyThree(Stage stage) {
        super(stage);
        // TODO Auto-generated constructor stub
    }

    /**
     * Simple Enemie Implementation
     * @author Thorsten Rösler
     *
     */

    private Weapon weapon;
    private float fireRate;

    public void setCharacteristics() {
        setAnimation(AnimationLoader.get().texture("enemies/gegner3.png"));
        //setRotation(180);
        setAcceleration(1000);
        setSpeedMax(100);
        setDeceleration(1000000);
        setShapePolygon(8);

        setHp(3);
        setScoreValue(2);
        fireRate = 3f;  
        weapon = new WeaponNormal(fireRate, getStage());
    }

    /**
     * input handling
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        weapon.act(delta);
        accelerationAtAngle(270);
        applyObjectPhysics(delta);
        
        if(getElapsedTime() > 0.8f) {
            weapon.fire(getX() + (getWidth() / 2), getY() - 40, 270);
        }

    }
}
