/**
 * 
 */
package com.github.lhrb.nemo.actors;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.actors.enemies.Kraken;
import com.github.lhrb.nemo.screen.LevelScreen;

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
            e.perishExplosion();
            e.removeShapePolygon();
        }
    }
    
    protected int getPartSize() {
        if(parts == null) return 0;
        return parts.size();
    }
    
    
    protected void handleCollision(Section section) {
        if(section.getDmg()) {
            removePart(section);

            if (getPartSize() == 0) {
                addAction(Actions.removeActor());
            }
        }
    }
    
    public ArrayList<Section> getPartCollection(){
        return parts;
    }
    
    

}
