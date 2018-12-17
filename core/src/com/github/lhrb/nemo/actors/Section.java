/**
 * 
 */
package com.github.lhrb.nemo.actors;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.github.lhrb.nemo.actors.shots.Shots;

/**
 * @author exa
 *
 */
public class Section extends EnemyActor {
    
    private MultiPartActor parent;
    
    /**
     * 
     * @param parent
     * @param x
     * @param y
     * @param hp
     * @param score
     * @param texture
     */
    public Section(MultiPartActor parent, float x, float y, 
                   int hp, int score, Animation<TextureRegion> texture) {
        super();
        this.parent = parent;        
        setAnimation(texture);
        setPosition(x,y);
        setHp(hp);
        setScoreValue(score);
        setShapePolygon(4);
        parent.addActor(this);
        setDebug(true);
        debuug();
    }
    
    public void debuug() {
        System.out.println("X: " + getX() + " Y: " + getY() 
                           +"\n OX: " + getOriginX() + " OY: " + getOriginY());
    }
    
    /**
     * 
     * @return true if hp is zero or less
     */
    public boolean getDmg() {
        decreaseHp();
        return (getHp() <= 0);
    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.actors.EnemyActor#perish()
     */
    @Override
    public void perish() {
        // TODO Auto-generated method stub
        super.perish();
    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.actors.EnemyActor#collision(com.github.lhrb.nemo.actors.CollisionEvent)
     */
    @Override
    public void collision(CollisionEvent col) {
        if(col == null) return;
        if(col.getSource() instanceof Shots) {
            parent.handleCollision(this);
        }
    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.actors.PhysicalActor#getShape()
     */
    @Override
    public Polygon getShape() {
        if(shape != null) {
            Vector2 origin = parent.localToStageCoordinates( new Vector2(getOriginX(), getOriginY()) );
            Vector2 pos = parent.localToStageCoordinates( new Vector2(getX(), getY()) );
            System.out.println("X:" + pos.x + " Y: " + pos.y);
            shape.setPosition( pos.x, pos.y);
            shape.setOrigin(origin.x, origin.y); 
            shape.setRotation(getRotation());
            shape.setScale(getScaleX()*0.5f, getScaleY());
        }        
        return shape;
    }

}
