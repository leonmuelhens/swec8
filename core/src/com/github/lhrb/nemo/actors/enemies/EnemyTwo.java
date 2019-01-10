package com.github.lhrb.nemo.actors.enemies;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.EnemyActor;
import com.github.lhrb.nemo.actors.weapons.Weapon;
import com.github.lhrb.nemo.actors.weapons.WeaponSpread;
import com.github.lhrb.nemo.util.AnimationLoader;

public class EnemyTwo extends EnemyActor {

    public EnemyTwo(Stage stage) {
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
        
        setAnimation(AnimationLoader.get().texture("gegner2.png"));
        //setRotation(180);
        setAcceleration(30);
        setSpeedMax(30);
        setDeceleration(1000000);
        setShapePolygon(8);

        setHp(4);
        setScoreValue(20);
        fireRate = 3f;  
        weapon = new WeaponSpread(fireRate, 45f, getStage());
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
        
        if(getElapsedTime() > 4f) {
            weapon.fire(getX() + (getWidth() / 2), getY() - 40, 270);
        }
        
    }
}
