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
    
    public Player(float x, float y, Stage stage) {
        super(x,y,stage);
        setAnimation(AnimationLoader.loadTexture("player.png"));
        
        setAcceleration(3600);
        setSpeedMax(800);
        setDeceleration(100000);
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
        
        applyPhysics(delta);
        
        /**
         * ATTENTION
         * this method does not provide any security mechanism
         */
        setWorldBounds();
        
        
    }

}
