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
    
    ArrayList<PhysicalActor> parts;
    
    public MultiPartActor(float x, float y, Stage stage) {
        super(x,y,stage);
        parts = new ArrayList<PhysicalActor>();
    }
    
    
    
    /**
     * 
     * @param pA
     */
    public void addPart(PhysicalActor e) {
        if(parts == null) return;
        parts.add(e);
        this.addActor(e);
    }
    
    public void addPart(PhysicalActor e, float x, float y) {
        e.setPosition(x, y);
        addPart(e);
    }
    
    public ArrayList<PhysicalActor> getPartCollection(){
        return parts;
    }
    
    

}
