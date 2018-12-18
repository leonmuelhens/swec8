/**
 * 
 */
package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.actors.CollisionManager;
import com.github.lhrb.nemo.actors.MultiPartActor;
import com.github.lhrb.nemo.actors.PhysicalActor;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.actors.Section;
import com.github.lhrb.nemo.actors.powerups.CActor;
import com.github.lhrb.nemo.actors.powerups.CType;
import com.github.lhrb.nemo.util.AnimationLoader;

/**
 * @author exa
 *
 */
public class TestScreen extends AbstractScreen {
    
    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#init()
     */
    @Override
    public void init() {
        
        
        CActor a = new CActor(50,300,gameStage, CType.Laser);
        CActor b = new CActor(150,300,gameStage, CType.Bomb);
        CActor c = new CActor(250,300,gameStage, CType.Star);
        CActor d = new CActor(350,300,gameStage, CType.Shield);
        CActor e = new CActor(450,300,gameStage, CType.Spread);
        CActor f = new CActor(550,300,gameStage, CType.Normal);
        CActor g = new CActor(550,100,gameStage, CType.Multiplicator);
        
        
        Player player = new Player(200,50, gameStage);

    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.screen.AbstractScreen#update(float)
     */
    @Override
    public void update(float delta) {
        CollisionManager.checkCollision(getPhysicalActors());
        // TODO Auto-generated method stub
    }
}
