/**
 * 
 */
package com.github.lhrb.nemo.actors;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
        setPosition(parent.getX()+x, parent.getY()+y);
        setAnimation(texture);
        setHp(hp);
        setScoreValue(score);
        setShapePolygon(4);
        parent.addActor(this);
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

}
