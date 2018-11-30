/**
 * 
 */
package com.github.lhrb.nemo.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

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
    private Polygon shape;

    public ActorPrefab() {
        initializeActor();
    }

    public ActorPrefab(float x, float y, Stage stage) {
        super(); // Unnecessary? call actually
        setPosition(x,y);
        stage.addActor(this);

        initializeActor();
    }

    public void initializeActor() {
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
    }


    /**
     * sets the animation
     * @param animation
     */
    public void setAnimation(Animation<TextureRegion> animation) {
        if (animation == null) {
            System.out.println("The animation could not be created as the file does not exist!");
            return;
        }

        this.animation = animation;
        TextureRegion region = animation.getKeyFrame(0);
        float width = region.getRegionWidth();
        float height = region.getRegionHeight();
        setSize(width, height);
        setOrigin(width/2, height/2);
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
    public void setBoundToWorld() {
        if(worldDimension == null) return;
        if(getX() < 0) {
            setX(0);
        }
        if(getX() + getWidth() > worldDimension.width) {
            setX(worldDimension.width - getWidth());
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
        velocity.add(Math.round(accelerationVector.x * delta), 
                     Math.round(accelerationVector.y * delta));
        float speed = getSpeed();
        
        if(accelerationVector.len() == 0) {
            speed -= Math.round(deceleration * delta);
        }
        
        speed = MathUtils.clamp(speed, 0, speedMax);
        setSpeed(speed);
        
        moveBy(Math.round(velocity.x * delta), Math.round(velocity.y * delta));
        
        accelerationVector.set(0,0);
    }

    public void applyObjectPhysics(float delta) {
        if (getY() > getStage().getHeight() || getY() + getHeight() <= 0 ) {
            remove();
        } else {
            applyPhysics(delta);
        }
    }

    
    /**
     * ####################################
     * ####### COLLISION ##################
     * ####################################
     */
    
    /**
     * sets a basic polygon for collision detection
     * @param numEdges
     */
    public void setShapePolygon(int numEdges) {
        float width = getWidth();
        float height = getHeight();
        
        float[] vertices = new float[2*numEdges];
        for(int i = 0; i < numEdges; i++) {
            // 6.28 is ~360 degree in radian measure ;)
            float radians = i * 6.28f / numEdges; 
            vertices[2*i] = width/2 * MathUtils.cos(radians) + width/2;
            vertices[2*i+1] = height/2 * MathUtils.sin(radians) + height/2;
        }
        shape = new Polygon(vertices);
    }
    
    
    /**
     * ATTENTION
     * could possible return null 
     * adjusts the shape position according to the actors parameters
     * @return
     */
    public Polygon getShape() {
        if(shape != null) {
            shape.setPosition(getX(), getY());
            shape.setOrigin(getOriginX(), getOriginY());
            // probably not necessary 
            shape.setRotation(getRotation());
            shape.setScale(getScaleX(), getScaleY());
            
        }        
        return shape;
    }
    
    /**
     * Determines whether to polygons overlap or not
     * @param other 
     * @return
     */
    public boolean overlap(ActorPrefab other) {
        if(shape == null || other == null ) return false;
        Polygon p1 = this.getShape();
        Polygon p2 = other.getShape();
        
        //test rectangle first (performance)
        if( !p1.getBoundingRectangle().overlaps(p2.getBoundingRectangle())) {
            return false;
        }
        
        return Intersector.overlapConvexPolygons(p1, p2);
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
        if( animation != null && isVisible() ) {
            
            batch.draw(animation.getKeyFrame(elapsedTime), 
                       getX(), getY(), 
                       getOriginX(), getOriginY(), 
                       getWidth(), getHeight(), 
                       getScaleX(), getScaleY(), getRotation() );
                      
        }
        super.draw(batch, parentAlpha);
        
    }

}
