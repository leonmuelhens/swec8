/**
 * 
 */
package com.github.lhrb.nemo.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.util.AnimationLoader;

/**
 * Simple player Implementation
 * @author exa
 * 
 */
public class Player extends ActorPrefab {

    private static float shotDelay = 0.8f;
    private static float shotDelayCount = 0;

    public Player(float x, float y, Stage stage) {
        super(x,y,stage);
        setAnimation(AnimationLoader.loadTexture("player_laser.png"));
        
        setAcceleration(30000);
        setSpeedMax(800);
        setDeceleration(30000);
    }
    
 
    
    /**
     * input handling
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        
        if(Gdx.input.isKeyPressed(Keys.LEFT)) {
            accelerationAtAngle(180);
        }
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
            accelerationAtAngle(0);
        }
        if(Gdx.input.isKeyPressed(Keys.UP)) {
            accelerationAtAngle(90);
        }
        if(Gdx.input.isKeyPressed(Keys.DOWN)) {
            accelerationAtAngle(270);
        }

        if(Gdx.input.isKeyPressed(Keys.SPACE) && shotDelayCount >= shotDelay) {
            new Shot(getX()+(getWidth()/2), getY()+(getHeight()/2), getStage(), 90);
            shotDelayCount = 0;
        }
        
        applyPhysics(delta);
        shotDelayCount += delta;
        
        /**
         * ATTENTION
         * this method does not provide any security mechanism
         */
        setWorldBounds();
        
        
    }

}
