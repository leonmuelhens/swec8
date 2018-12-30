package com.github.lhrb.nemo.util;

import java.io.Serializable;

public class Highscore implements Serializable {
    private String name="";
    private int score=0;

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public Highscore(){

    }

    public Highscore(String name,int score){
        this.name =name;
        this.score = score;
    }
}
