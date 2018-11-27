/**
 * 
 */
package com.github.lhrb.nemo.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

/**
 * @author exa
 *
 */
public class ActorPrefab extends Group {
    
    // Animation Parameters
    private Animation<TextureRegion> animation;
    private float elapsedTime;
    private boolean animationPause;
    
    //some easy motion parameters for fluent movement
    private float acceleration;
    private float speedMax;
    private float deceleration;
    
    //test area
    private Vector2 velocity;
    private Vector2 accelerationVector;
    
    private Rectangle worldDimension; 

    
    public ActorPrefab(float x, float y, Stage stage) {
        super(); // Unnecessary? call actually
        setPosition(x,y);
        stage.addActor(this);
        
        // animation init
        animation = null;
        elapsedTime = 0;
        animationPause = false;
        
        //motion init
        acceleration = 0;
        deceleration = 0;
        speedMax = 100;
        
        //test area
        velocity = new Vector2(0,0);
        accelerationVector = new Vector2(0,0);
        
        worldDimension = new Rectangle(0,0,stage.getWidth(), stage.getHeight());
       
    }
    
    /**
     * sets the animation
     * @param animation
     */
    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
        TextureRegion region = animation.getKeyFrame(0);
        float width = region.getRegionWidth();
        float height = region.getRegionHeight();
        setSize(width, height);
        setOrigin(width/2, height/2);
    }
    
    
    /**
     * Returns an animation based on a sequence inside a 
     * image file
     * 
     * This method is actually a canidate for an static method in
     * another class though
     * 
     * be aware this method does not provide any security mechanism
     * pass an empty filename or null or a zero value as row/col
     * will cause an error
     * 
     * @param fileName
     * @param rows
     * @param cols
     * @param frameDuration
     * @param loop
     * @return
     */
    public Animation<TextureRegion> loadAnimation(String fileName, 
                                                  int rows, int cols, 
                                                  float frameDuration, boolean loop){
        
        Texture texture = new Texture(Gdx.files.internal(fileName), true);
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        int tileWidth = texture.getWidth() / cols;
        int tileHeight = texture.getHeight() / rows;
        TextureRegion[][] tmp = TextureRegion.split(texture, tileWidth, tileHeight);
        
        Array<TextureRegion> textureArray = new Array<TextureRegion>();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                textureArray.add(tmp[i][j]);
            }
        }
        
        Animation<TextureRegion> tmpAnimation = new Animation<TextureRegion>
                                                (frameDuration, textureArray);
        
        if(loop) {
            tmpAnimation.setPlayMode(Animation.PlayMode.LOOP);
        }else {
            tmpAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        }
        
        return tmpAnimation;
    }
    
    /**
     * Simple helper method 
     * for game objects that may be respresentetd
     * by a single image
     * 
     * @param fileName
     * @return
     */
    public Animation<TextureRegion> loadTexture(String fileName){
        return loadAnimation(fileName, 1, 1, 1, true);
    }
    
   
    public boolean isAnimationFinished() {
        return animation.isAnimationFinished(elapsedTime);
    }
    
    public void setAnimationPause(boolean pause) {
        animationPause = pause;
    }
    
    public void toggleAnimationPause() {
        animationPause = !animationPause;
    }
    
    
    
    public void setWorldDimension(float width, float height) {
        worldDimension = new Rectangle(0,0, width, height);
    }
    
    /**
     * NO SECURITY MECHANISM IMPLEMENTED YET
     * PAY ATTENTION USING THIS
     */
    public void setWorldBounds() {
        if(getX() + getWidth() < 0) {
            setX(worldDimension.width);
        }
        if(getX() > worldDimension.width) {
            setX(-getWidth());
        }
        // bound Y
        if(getY() < 0) {
            setY(0);
        }
        if(getY() + getHeight() > worldDimension.height) {
            setY(worldDimension.height - getHeight());
        }
    }
    
    
    /**
     * sets opacity 
     * can be used for a fade out effect 
     * @param opacity
     */
    public void setOpacity(float opacity) {
        this.getColor().a = opacity;
    }
    
    
    /**
     * #############################################
     *               ACTOR MOTION  
     * #############################################
     */
    
    /**
     * 
     * @param acceleration
     */
    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }
    
    /**
     * 
     * @param deceleration
     */
    public void setDeceleration(float deceleration) {
        this.deceleration = deceleration;
    }
    
    public void setSpeedMax(float maxSpeed) {
        this.speedMax = maxSpeed;
    }
    
    public void setSpeed(float speed) {
        if(velocity.len() == 0) {
            velocity.set(speed, 0);
        }else {
            velocity.setLength(speed);
        }
    }
    
    public float getSpeed() {
        return velocity.len();
    }
    
    public boolean isMoving() {
        return (getSpeed()>0);
    }
    
    public void accelerationAtAngle(float angle) {
        accelerationVector.add(new Vector2(acceleration,0).setAngle(angle));
    }
    
    public void accelerateForward() {
        accelerationAtAngle(getRotation());
    }
    
    
    /**
     * 
     * @param delta
     */
    public void applyPhysics(float delta) {
        velocity.add(accelerationVector.x * delta, accelerationVector.y * delta);
        float speed = getSpeed();
        
        if(accelerationVector.len() == 0) {
            speed -= deceleration * delta;
        }
        
        speed = MathUtils.clamp(speed, 0, speedMax);
        setSpeed(speed);
        
        moveBy(velocity.x * delta, velocity.y * delta);
        
        accelerationVector.set(0,0);
    }

    
    /**
     * updates the actor based on time
     */
    public void act(float delta) {
        super.act(delta);
        if(!animationPause) {
            elapsedTime += delta;
        }
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);
        if( animation != null & isVisible() ) {
            
            batch.draw(animation.getKeyFrame(elapsedTime), 
                       getX(), getY(), 
                       getOriginX(), getOriginY(), 
                       getWidth(), getHeight(), 
                       getScaleX(), getScaleY(), getRotation() );
                      
        }
        super.draw(batch, parentAlpha);
        
    }

}
