package com.github.lhrb.nemo.SpawnFactory;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.powerups.CActor;
import com.github.lhrb.nemo.actors.powerups.CType;

import java.util.Random;

public class CollectibleFactory {
    //SEED 111 FOR TESTING
 private static Random rand = new Random();

    public CollectibleFactory() {
    }


    public static void spawnC(float x, float y, Stage stage) {
        
        Random rand = new Random();
        //if ((rand.nextInt(10)+1) < 9) {
        CType type;
            switch (rand.nextInt(7) + 1) {
                case 1:
                    type = CType.Bomb;
                    break;
                case 2:
                    type = CType.Heart;
                    break;
                case 3:
                    type = CType.Multiplicator;
                    break;
                case 4:
                    type = CType.Shield;
                    break;
                case 5:
                    type = CType.Star;
                    break;
                case 6:
                    type = CType.Spread;
                    break;
                case 7:
                    type = CType.Laser;
                    break;
                //Auf anfrage des Kunden rausgenommen
                //case 8:
                    //type = CType.Normal;
                    //break;
                default:
                    type = CType.Bomb;
                    break;
            }
            CActor pu = new CActor(x, y, stage, type);
            //stage.addActor(pu);;

    }
}