/**
 * 
 */
package com.github.lhrb.nemo.actors;

import com.github.lhrb.nemo.actors.enemies.Enemy;
import com.github.lhrb.nemo.actors.powerups.CActor;
import com.github.lhrb.nemo.actors.shots.Shots;

import java.util.ArrayList;

/**
 * @author exa
 *
 */
public class CollisionManager {
    
    public static void checkCollision(ArrayList<PhysicalActor> list) {
        ArrayList<Player> player = new ArrayList<Player>();
        ArrayList<Shots> shots = new ArrayList<Shots>();
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        ArrayList<CActor> collectibles = new ArrayList<CActor>();
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
            if(e instanceof CActor) {
                collectibles.add( (CActor)e );
            }
        }
        
        /**
         * grade brain fuck
         * sollte effizienter gehen?
         */

        for(Player p : player) {
            for (Enemy e : enemies) {
                // Enemy collosion with player
                if (e.overlap(p)) {
                    if (e != null) {
                        e.collision(p);
                    }
                    if (p != null) { p.collision(); }
                }
                // Enemy collosion with Shots
                for (Shots s : shots) {
                    if(e.overlap(s) && s.isPlayerShot()) {
                        if (e != null) {
                            e.collision(p);
                        }
                        if(s != null) { s.collision(); }
                    }
                }
            }
            for (Shots s : shots) {
                // Player collision with Shots
                if (s.overlap(p) && !s.isPlayerShot()) {
                    if(p != null) { p.collision(); }
                    if(s != null) { s.collision(); }
                }
            }
            for (CActor c : collectibles) {
                //Player collision with powerups
                if (c.overlap(p)) {
                    if (c != null) {
                        c.collision();
                    }
                    if(p != null) { p.collision(c); }
                }
            }
        }
    }
}
