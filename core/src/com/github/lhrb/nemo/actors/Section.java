/**
 * 
 */
package com.github.lhrb.nemo.actors;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author exa
 *
 */
public class Section extends EnemyActor {
    
    private MultiPartActor parent;
    
    public Section(MultiPartActor parent, float x, float y, 
                   Animation<TextureRegion> texture) {
        super();
        this.parent = parent;
        setPosition(x, y);
        setAnimation(texture);
        parent.addActor(this);
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
        // TODO Auto-generated method stub
        super.collision(col);
    }

}
