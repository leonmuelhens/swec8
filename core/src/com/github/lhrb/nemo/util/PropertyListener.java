/**
 * 
 */
package com.github.lhrb.nemo.util;

import java.beans.PropertyChangeListener;

/**
 * @author exa
 *
 */
public interface PropertyListener {
    
    public void addPropertyChangeListener(PropertyChangeListener l);
    public void removePropertyChangeListener(PropertyChangeListener l);
    
}
