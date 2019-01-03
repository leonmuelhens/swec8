/**
 * 
 */
package com.github.lhrb.nemo.actors;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.particles.Explosion;
import com.github.lhrb.nemo.actors.shots.Shots;
import com.github.lhrb.nemo.util.AnimationLoader;

/**
 * @author exa
 *
 */
public class Section extends EnemyActor {
    
    private MultiPartActor parent;
    private int ID;
    private Explosion explosion;
    
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
        setShapePolygon(8);
        parent.addActor(this);
    }
    
    /**
     * 
     * @param parent
     * @param x
     * @param y
     * @param id
     * @param hp
     * @param score
     * @param texture
     */
    public Section(int id, MultiPartActor parent, float x, float y,
            int hp, int score, Animation<TextureRegion> texture) {
        this(parent,x,y,hp,score,texture);
        this.ID = id;
       
    }
    
    
    public void setPos(float x, float y) {
        Vector2 t = parent.stageToLocalCoordinates(new Vector2(x,y));
        setPosition(t.x,t.y);
        
    }
    /**
     * 
     * @return true if hp is zero or less
     */
    public boolean getDmg() {
        decreaseHp();
        updateVisualDamage();
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

    @Override
    protected void perishExplosion() {
        explosion = new Explosion();
        Vector2 pos = parent.localToStageCoordinates( new Vector2(getX(), getY()) );
        explosion.setPosition(pos.x + getWidth()/2, pos.y + getHeight()/2);;
        Stage s = getStage();
        if(s != null) {
            getStage().addActor(explosion);
        }
        explosion.start();
        
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
            shape.setPosition( pos.x, pos.y);
            shape.setOrigin(origin.x, origin.y); 
            shape.setRotation(getRotation());
            shape.setScale(getScaleX(), getScaleY());
        }        
        return shape;
    }


    /**
     * @return the iD
     */
    public int getID() {
        return ID;
    }

}
