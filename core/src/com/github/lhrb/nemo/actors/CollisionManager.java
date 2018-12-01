/**
 * 
 */
package com.github.lhrb.nemo.actors;

import java.util.ArrayList;

import com.github.lhrb.nemo.actors.enemies.Enemy;
import com.github.lhrb.nemo.actors.shots.Shots;

/**
 * @author exa
 *
 */
public class CollisionManager {
    
    public static void checkCollision(ArrayList<PhysicalActor> list) {
        ArrayList<Player> player = new ArrayList<Player>();
        ArrayList<Shots> shots = new ArrayList<Shots>();
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for(PhysicalActor e : list) {
            if(e instanceof Player) {
                player.add( (Player)e );
            }
            if(e instanceof Shots) {
                shots.add( (Shots)e);
            }
            if(e instanceof Enemy) {
                enemies.add( (Enemy)e );
            }
        }
        
        /**
         * grade brain fuck
         * sollte effizienter gehen?
         */
        for(Enemy e : enemies) {
            for(Player p : player) {
                if(e.overlap((PhysicalActor)p)) {
                    //beide einzel prüfen, falls ein object schon removed wurde
                    if(e != null) {
                        e.collision(); 
                    }
                    if(p != null) {
                        p.collision();
                    }
                    
                }
            }
            for(Shots s : shots) {
                if(e.overlap(s)) {
                    if(e != null) {
                        e.collision();
                    }
                    if( s != null) {
                        s.collision();
                    }
                }
            }       
            
        }
       
        
    }

}
