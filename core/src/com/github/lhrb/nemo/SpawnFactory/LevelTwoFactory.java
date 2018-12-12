package com.github.lhrb.nemo.SpawnFactory;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.enemies.Enemy;
import com.github.lhrb.nemo.actors.enemies.EnemyOne;
import com.github.lhrb.nemo.actors.enemies.EnemyThree;
import com.github.lhrb.nemo.actors.enemies.EnemyTwo;

import java.util.Random;

public class LevelTwoFactory extends EnemyFactory {

    public LevelTwoFactory(Stage gameStage) {
        super(gameStage);
        randSpawnPossibilityStart = 2;
    }

    public void spawnEnemy() {
        Random unitRand = new Random();
        int randomInt = unitRand.nextInt(100);

        Enemy newEnemy;

        if(randomInt <= 40) {
            newEnemy = new EnemyOne(gameStage);
        } else if (randomInt <= 75) {
            newEnemy = new EnemyTwo(gameStage);
        } else {
            newEnemy = new EnemyThree(gameStage);
        }

        Random xCoord = new Random();
        float x = xCoord.nextInt((int) gameStage.getWidth()- (int) newEnemy.getWidth() ) + 1;
        float y = gameStage.getHeight();

        newEnemy.setPosition(x,y);
        gameStage.addActor(newEnemy);
    }

    @Override
    public void modifySpawnRate() {
        // Idea is to have a different spawnRate in every level!
        // Here is still the rates from first level

        //-(atan(0.8x-3))+1.9
        // x = 9 equals 3 min of game
        // x = 0.05 equals 1 second

        //here we get the x value for atan function for time passed
        float x = (gameTime /2 / 10);

        // here we get the y value which will modify the spawn rate!
        // we can just modify this function to change the spawnrate
        double y = -Math.atan((0.8F * x) -3)+1.9;
        spawnRate = (float)y;

        // SpawnPossibility is calculated with gametime. As x = 9 is 3 min, ever min the
        // random spawnPossibility is incremented. As we do this with int, this is not exactly
        // every min, but more or less if we find a better function, go ahead
        randSpawnPossibility = ((int) x/3) +randSpawnPossibilityStart;
    }
}
