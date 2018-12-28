package com.github.lhrb.nemo.ui;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.screen.AbstractScreen;

public class ActionButton extends TextButton {

    public <T extends AbstractScreen> ActionButton(String text, TextButtonStyle skin, Class<T> screen) {
        super(text, skin);
        buttonListener(screen);
    }

    private <T extends AbstractScreen> void buttonListener(Class<T> screen) {
        this.addListener(
            (Event e) ->{
                if( !(e instanceof InputEvent)) { return false; }
                if( !((InputEvent)e).getType().equals(InputEvent.Type.touchDown) ) { return false; }
                //set next screen
                try {
                    KillingNemo.setActiveScreen(screen.getConstructor().newInstance());
                } catch (Exception ex) {
                    // do something?
                }
                return true;
            });
    }
}
