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
    
    private ArrayList<Section> parts;
    
    public MultiPartActor(Stage stage) {
        super();
        setStage(stage);
        init();
    }
    
    public MultiPartActor(float x, float y, Stage stage) {
        super(x,y,stage);
        init();
    }
    
    private void init() {
        parts = new ArrayList<Section>();
    }
    
    
    /**
     * 
     * @param pA
     */
    protected void addPart(Section e) {
        if(parts == null) return;
        parts.add(e);
    }
    
    protected void removePart(Section e) {
        if(parts != null) {
            parts.remove(e);
        }
    }
    
    protected int getPartSize() {
        if(parts == null) return 0;
        return parts.size();
    }
    
    
    protected void handleCollision(Section section) {
        if(section.getDmg()) {
            parts.remove(section);
            section.perish();
        }
    }
    
    public ArrayList<Section> getPartCollection(){
        return parts;
    }
    
    

}
