/**
 * 
 */
package com.github.lhrb.nemo.actors;

/**
 * @author exa
 *
 */
public interface Existence {
    public void collision(CollisionEvent col);
    public void perish();
}
