package com.github.lhrb.nemo.actors;

import com.github.lhrb.nemo.actors.enemies.Enemy;
import com.github.lhrb.nemo.actors.shots.Shots;

public class CollisionEvent {

    private Player player;
    private Enemy enemy;
    private Shots shot;

    public CollisionEvent(Player p, Enemy e, Shots s) {
        this.player = p;
        this.enemy = e;
        this.shot = s;
    }


    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public Shots getShot() {
        return shot;
    }

    public boolean isShot() {
        if (shot != null) {
            return true;
        } else {
            return false;
        }
    }
}
