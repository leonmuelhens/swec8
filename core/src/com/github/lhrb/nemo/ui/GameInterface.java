package com.github.lhrb.nemo.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.github.lhrb.nemo.util.GuiManager;

public class GameInterface {
    Table gameInterface = new Table();

    public GameInterface(Stage stage) {

        Label one = new Label("0:", GuiManager.getInstance().getLabelStyle());
        Label one2 = new Label("0:", GuiManager.getInstance().getLabelStyle());

        Label one3 = new Label("0:", GuiManager.getInstance().getLabelStyle());

        Label one4 = new Label("0:", GuiManager.getInstance().getLabelStyle());


        Label four = new Label("1000:", GuiManager.getInstance().getLabelStyle());

        Table table = new Table();
        table.setWidth(stage.getWidth());
        table.setHeight(stage.getHeight());

        // first Row
        table.add(one).width(100); // left column
        table.add(one2).expandX(); // middle
        table.add(one3).width(100); //right column
        table.row();

        // second row
        table.add().width(100); // left column
        table.add().expandY(); // middle
        table.add(four).width(100); //right column
        table.row();

        table.setPosition(0,0);
        System.out.println(table.getX());
        System.out.println(table.getY());


        stage.addActor(table);
        table.debug();
    }

    public void addActor(Actor actor) {
        gameInterface.add(actor);
    }


}
