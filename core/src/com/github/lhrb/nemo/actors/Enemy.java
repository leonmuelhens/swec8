/**
 * 
 */
package com.github.lhrb.nemo.actors;

/**
 * @author exa
 * 
 * Needed for collision Management
 */
public interface Enemy {
    
    public void collision();
    public boolean overlap(PhysicalActor other);
    
}
