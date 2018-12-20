/**
 * 
 */
package com.github.lhrb.nemo.actors;

import com.github.lhrb.nemo.util.CollisionEvent;

/**
 * @author exa
 *
 */
public interface Existence {
    public void collision(CollisionEvent col);
    public void perish();
}
