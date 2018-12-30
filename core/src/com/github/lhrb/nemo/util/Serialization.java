package com.github.lhrb.nemo.util;

import java.io.*;
import java.util.ArrayList;

public class Serialization {
    public static void serialise(ArrayList<Highscore> toSave){
        try(FileOutputStream fs = new FileOutputStream("Highscore.s");
            ObjectOutputStream os = new ObjectOutputStream(fs);) {
            os.writeObject(toSave);
            os.close();
            fs.close();
        }catch (IOException e) {
            //TODO optimise exception handling
            e.printStackTrace();
        }
    }

    public static ArrayList<Highscore>  deserialise(){
        File test = new File("Highscore.s");
        try {
            if (test.exists()) {
                try (FileInputStream fs = new FileInputStream("Highscore.s");
                     ObjectInputStream objectInputStream = new ObjectInputStream(fs)) {
                         return (ArrayList<Highscore> ) objectInputStream.readObject();
                }
            }
        }catch (IOException|ClassNotFoundException e){
            //TODO optimise exception handling
            System.out.println("Datei konnte nicht gelesen werden\n"+e.getMessage());
        }
        return new ArrayList<Highscore>(){{add(new Highscore("Empty", 0));
            add(new Highscore("Empty", 0));
            add(new Highscore("Empty", 0));
            add(new Highscore("Empty", 0));
            add(new Highscore("Empty", 0));
            add(new Highscore("Empty", 0));
            add(new Highscore("Empty", 0));
            add(new Highscore("Empty", 0));
            add(new Highscore("Empty", 0));
            add(new Highscore("Empty", 0));}};
    }
}

