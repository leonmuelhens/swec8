/**
 * 
 */
package com.github.lhrb.nemo.actors.powerups;

import java.util.HashMap;

/**
 * @author exa
 *
 */
public class Collectibles {
    
   private static Collectibles collectables;
   private HashMap<CType, String> collectibles;
   
   private Collectibles() {
       collectibles = new HashMap<CType, String>();
       init();
   }
   
   public static Collectibles get() {
       if(collectables == null) {
           collectables = new Collectibles();
       }
       return collectables;
   }
   
   private void init() {
       //Powerups
       collectibles.put(CType.Bomb, "collectibles/powerup_bombe.png");
       collectibles.put(CType.Heart, "collectibles/powerup_heart.png");
       collectibles.put(CType.Multiplicator, "collectibles/powerup_multiplicator.png");
       collectibles.put(CType.Shield, "collectibles/powerup_shield.png");
       collectibles.put(CType.Star, "collectibles/powerup_star.png");
       
       //Weapons
       collectibles.put(CType.Laser, "collectibles/weapon_laser.png");
       collectibles.put(CType.Spread, "collectibles/weapon_spread.png");
       collectibles.put(CType.Normal, "collectibles/weapon_normal.png");
   }
   
   /**
    * 
    * @param key
    * @return
    */
   public String getPath(CType key) {
       return collectibles.get(key);
   }
   
   
}
