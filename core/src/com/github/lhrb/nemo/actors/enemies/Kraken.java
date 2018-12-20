/**
 * 
 */
package com.github.lhrb.nemo.actors.enemies;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.MultiPartActor;
import com.github.lhrb.nemo.actors.Section;
import com.github.lhrb.nemo.util.AnimationLoader;

/**
 * @author exa
 *
 */
public class Kraken extends MultiPartActor {
    
    public Kraken(Stage stage) {
        super(stage);
        init();
    }
    
    public Kraken(float x, float y, Stage stage) {
        super(x,y,stage);
        init();
    }
    
    private void init() {
        addPart(new Section(0, this, 75,50, 15, 500, 
                    AnimationLoader.get().texture("krake_kopf.png")) );
        addPart(new Section(0, this, 30,100, 5, 50, 
                AnimationLoader.get().texture("krake_flosse1.png")) );
        addPart(new Section(0, this, 0,50, 5, 50, 
                AnimationLoader.get().texture("krake_flosse2.png")) );
        addPart(new Section(0, this, 60,0, 5, 50, 
                AnimationLoader.get().texture("krake_flosse3.png")) );
        addPart(new Section(0, this, 140,0, 5, 50, 
                AnimationLoader.get().texture("krake_flosse4.png")) );
        addPart(new Section(0, this, 170,50, 5, 50, 
                AnimationLoader.get().texture("krake_flosse5.png")) );
        addPart(new Section(0, this, 170,100, 5, 50, 
                AnimationLoader.get().texture("krake_flosse6.png")) );
    }

}
