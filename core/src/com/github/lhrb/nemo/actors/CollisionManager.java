/**
 * 
 */
package com.github.lhrb.nemo.actors;

import com.github.lhrb.nemo.actors.powerups.PowerUP;
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
        ArrayList<EnemyActor> enemies = new ArrayList<EnemyActor>();
        ArrayList<PowerUP> powerups = new ArrayList<PowerUP>();
        for (PhysicalActor e : list) {
            if (e instanceof Player) {
                player.add((Player) e);
            }
            if (e instanceof Shots) {
                shots.add((Shots) e);
            }
            if (e instanceof EnemyActor) {
                enemies.add((EnemyActor) e);
            }
            if (e instanceof PowerUP) {
                powerups.add((PowerUP) e);
            }
        }

        /**
         * grade brain fuck
         * sollte effizienter gehen?
         *
         * Collision source reihenfolge:
         * - Shots
         * - Enemy
         * - Player  -> ist nie source
         */


        // Collision Source sollte immer der Schuss oder der Gegner sein
        for (Player p : player) {
            for (EnemyActor e : enemies) {
                if (!p.isInvincible()) {
                    // Enemy collosion with player
                    if (e.overlap(p)) {
                        if (e != null) {
                            e.collision(new CollisionEvent(e, p));
                        }
                        if (p != null) {
                            p.collision(new CollisionEvent(p, e));
                        }
                    }
                }
                // Enemy collosion with Shots
                for (Shots s : shots) {
                    if (e.overlap(s) && s.isPlayerShot()) {
                        if (e != null) {
                            e.collision(new CollisionEvent(s, e));
                        }
                        s.collision(null);
                    }
                }
            }
            if (!p.isInvincible()){
                for (Shots s : shots) {
                    // Player collision with Shots
                    if (s.overlap(p) && !s.isPlayerShot()) {
                        if (p != null) {
                            p.collision(new CollisionEvent(s, p));
                            s.collision(null);
                        }
                    }
                }
            }
            // Collision Source sollte das Powerup sein
            for (PowerUP pu : powerups) {
                //Player collision with powerups
                if (pu.overlap(p)) {
                    if (pu != null) {
                        pu.collision(new CollisionEvent(p, pu));
                    }
                    if (p != null) {
                        p.collision(new CollisionEvent(p, pu));
                    }
                }
            }
        }
    }
}
