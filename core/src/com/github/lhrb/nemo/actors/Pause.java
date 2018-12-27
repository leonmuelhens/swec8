package com.github.lhrb.nemo.actors;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
public class Pause extends Window {
    public Pause() {
        super("Pause", windowStyle);
    }

    private static final WindowStyle windowStyle;
    static {
        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("windows.pack"));

        windowStyle = new WindowStyle(new BitmapFont(), Color.BLACK, new TextureRegionDrawable(textureAtlas.findRegion("backgrounds/bg1.png")));

    }
}
