/**
 * 
 */
package com.github.lhrb.nemo.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

/**
 * @author exa
 *
 */
public class GuiManager {

    private static GuiManager guiMng;
    private LabelStyle labelStyle;
    private LabelStyle labelStyleSmall;
    private LabelStyle labelStyleBig;

    private TextButtonStyle txtBtnStyle;
    private TextButtonStyle txtBtnStyleSmall;
    private TextButtonStyle txtBtnStyleBig;

    private TextFieldStyle txtFldStyle;

    private GuiManager() {
        // Create Label Styles
        labelStyleSmall = new LabelStyle(createFont(24), Color.WHITE);
        labelStyle = new LabelStyle(createFont(36), Color.WHITE);
        labelStyleBig = new LabelStyle(createFont(48), Color.WHITE);

        // Create button Styles

        txtBtnStyle = new TextButtonStyle(createTextButtonStyle(36));
        txtBtnStyleSmall = new TextButtonStyle(createTextButtonStyle(24));
        txtBtnStyleBig = new TextButtonStyle(createTextButtonStyle(48));

        //Create TextField Style
        txtFldStyle = new TextFieldStyle(createTextFieldStyle(36, Color.BLACK));

        txtBtnStyleSmall = new TextButtonStyle(createTextButtonStyle(20));
        txtBtnStyle = new TextButtonStyle(createTextButtonStyle(25));
        txtBtnStyleBig = new TextButtonStyle(createTextButtonStyle(30));

    }


    private TextFieldStyle createTextFieldStyle(int fontSize, Color color) {
        Skin skin = new Skin();

        Texture backgoundTex = new Texture(Gdx.files.internal("Highscore_field.png"));
        Texture curse = new Texture(Gdx.files.internal("Highscore_cursor.png"));

        skin.add(
                "background",
                new NinePatch(backgoundTex, 5, 5, 5, 5));
        skin.add("cursor", curse);

        TextFieldStyle tStyle = new TextFieldStyle();
        tStyle.font = createFont(fontSize);
        tStyle.fontColor = color;
        tStyle.background = skin.getDrawable("background");
        tStyle.cursor = skin.newDrawable("cursor", Color.WHITE);
        tStyle.cursor.setMinWidth(2f);
        tStyle.selection = skin.newDrawable("background", 0.5f, 0.5f, 0.5f,
                0.5f);
        return tStyle;
    }

    private TextButtonStyle createTextButtonStyle(int fontSize) {
        return createTextButtonStyle(fontSize, Color.BLACK);
    }

    private TextButtonStyle createTextButtonStyle(int fontSize, Color color) {
        TextButtonStyle tmp = new TextButtonStyle();
        Texture buttonTex = new Texture(Gdx.files.internal("button.png"));
        NinePatch buttonPatch = new NinePatch(buttonTex, 10, 10, 8, 8);
        tmp.up = new NinePatchDrawable(buttonPatch);
        tmp.font = createFont(fontSize);
        tmp.fontColor = Color.BLACK;
        return tmp;
    }

    // Creates font with size X and Color white as default
    private BitmapFont createFont(int fontSize) {
        return createFont(fontSize, Color.WHITE);
    }

    // Creates font with size X anc Color Y
    private BitmapFont createFont(int fontSize, Color color) {
        FreeTypeFontGenerator fontGenerator =
                new FreeTypeFontGenerator(Gdx.files.internal("font/pixelfj8pt1.normal.ttf"));
        FreeTypeFontParameter fontParameters = new FreeTypeFontParameter();
        fontParameters.size = fontSize;
        fontParameters.color = color;
        fontParameters.borderWidth = 1;
        fontParameters.borderColor = Color.BLACK;
        fontParameters.borderStraight = false;
        fontParameters.minFilter = TextureFilter.Linear;
        fontParameters.magFilter = TextureFilter.Linear;

        return fontGenerator.generateFont(fontParameters);
    }

    public static GuiManager getInstance() {
        if (guiMng == null) {
            guiMng = new GuiManager();
        }
        return guiMng;
    }

    public LabelStyle getLabelStyleSmall() {
        return labelStyleSmall;
    }

    public LabelStyle getLabelStyle() {
        return labelStyle;
    }

    public LabelStyle getLabelStyleBig() {
        return labelStyleBig;
    }

    public TextButtonStyle getTxtBtnStyleSmall() {
        return txtBtnStyleSmall;
    }

    public TextButtonStyle getTxtBtnStyle() {
        return txtBtnStyle;
    }

    public TextButtonStyle getTxtBtnStyleBig() {
        return txtBtnStyleBig;
    }


    public TextFieldStyle getTxtFldStyle() {
        return txtFldStyle;
    }

}
