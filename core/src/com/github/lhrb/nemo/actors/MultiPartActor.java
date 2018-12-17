/**
 * 
 */
package com.github.lhrb.nemo.actors;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * @author exa
 *
 */
public class MultiPartActor extends ActorPrefab {
    
    ArrayList<Section> parts;
    
    public MultiPartActor(float x, float y, Stage stage) {
        super(x,y,stage);
        parts = new ArrayList<Section>();
        setDebug(true);
    }
    
    
    
    /**
     * 
     * @param pA
     */
    public void addPart(Section e) {
        if(parts == null) return;
        parts.add(e);
    }
    
    
    public void handleCollision(Section section) {
        if(section.getDmg()) {
            parts.remove(section);
            section.perish();
        }
    }
    
    public ArrayList<Section> getPartCollection(){
        return parts;
    }
    
    

}
