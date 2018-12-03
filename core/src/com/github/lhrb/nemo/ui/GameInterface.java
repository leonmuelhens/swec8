package com.github.lhrb.nemo.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.util.GuiManager;

import java.util.ArrayList;
import java.util.HashMap;

public class GameInterface {
    Table gameInterface = new Table();

    public GameInterface(Stage stage, HashMap<String, Actor> tableObjects) {

        Label o1 = new Label("1", GuiManager.getInstance().getLabelStyle());
        Label o2 = new Label("2", GuiManager.getInstance().getLabelStyle());
        Label o3 = new Label("3", GuiManager.getInstance().getLabelStyle());
        Label o4 = new Label("4", GuiManager.getInstance().getLabelStyle());
        Label o5 = new Label("5", GuiManager.getInstance().getLabelStyle());
        Label o6 = new Label("6", GuiManager.getInstance().getLabelStyle());
        Label o7 = new Label("7", GuiManager.getInstance().getLabelStyle());
        Label o9 = new Label("8", GuiManager.getInstance().getLabelStyle());


        Label four = new Label("1000:", GuiManager.getInstance().getLabelStyle());

        Table table = new Table();
        table.setWidth(stage.getWidth());
        table.setHeight(stage.getHeight());

        // first Row: Level Indicator
        table.add().width(75).pad(10);
        table.add(o1).expandX().height(50).right().pad(10); // middle
        table.row();

        // second row:Highscore indicatro
        table.add().width(75).pad(10);
        table.add(tableObjects.get("score")).expandX().height(50).right().pad(10);
        table.row();

        // third row:
        // left: weapon
        // right: life + heart
        table.add(tableObjects.get("weapon")).expandY().width(75).bottom().pad(10);
        table.add(tableObjects.get("life")).expandX().pad(10).bottom().right();

        stage.addActor(table);
    }

    public void addActor(Actor actor) {
        gameInterface.add(actor);
    }


}
