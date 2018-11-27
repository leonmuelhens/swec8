/**
 * 
 */
package com.github.lhrb.nemo.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * @author exa
 *
 */
public class ActorPrefab extends Actor {
    
    private TextureRegion textureRegion;
    private Rectangle rectangle;
    
    public ActorPrefab() {
        super();
        textureRegion = new TextureRegion();
        rectangle = new Rectangle();
    }
    
    public ActorPrefab(Texture texture) {
        super();
        textureRegion = new TextureRegion();
        rectangle = new Rectangle();
        setTexture(texture);
    }
    
    
    /**
     * sets the texture region 
     * note the method is not meant to handle 
     * texture atlases atm
     * @param texture
     */
    public void setTexture(Texture texture) {
        textureRegion.setRegion(texture);
        setSize(texture.getWidth(), texture.getHeight() );
        rectangle.setSize(texture.getWidth(), texture.getHeight());
    }
    
    /**
     * needed for first implementation of collision detection
     * @return
     */
    public Rectangle getRectangle() {
        rectangle.setPosition(getX(), getY());
        return rectangle;
    }
    
    /**
     * very simple collision
     * @param other
     * @return
     */
    public boolean collision(ActorPrefab other) {
        return this.getRectangle().overlaps(other.getRectangle());
    }
    
    
    /**
     * updates the actor based on time
     */
    public void act(float delta) {
        super.act(delta);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a * parentAlpha);
        if( isVisible() ) {
            
            batch.draw(textureRegion, 
                       getX(), getY(), 
                       getOriginX(), getOriginY(), 
                       getWidth(), getHeight(), 
                       getScaleX(), getScaleY(), getRotation() );
                      
        }        
        
    }

}
