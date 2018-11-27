/**
 * 
 */
package com.github.lhrb.nemo.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

/**
 * Simple player Implementation
 * @author exa
 * 
 */
public class Player extends ActorPrefab {
    
    public Player() {
        super();
    }
    
    public Player(Texture texture) {
        super(texture);
    }
    
    /**
     * input handling
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        
        if(Gdx.input.isKeyPressed(Keys.LEFT)) {
            this.moveBy(-1, 0);
            System.out.println(this.getX());
        }
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
            this.moveBy(1, 0);
            System.out.println(this.getX());
        }
        if(Gdx.input.isKeyPressed(Keys.UP)) {
            this.moveBy(0, 1);
            System.out.println("up key");
        }
        if(Gdx.input.isKeyPressed(Keys.DOWN)) {
            this.moveBy(0, -1);
            System.out.println("down key");
        }
    }

}
