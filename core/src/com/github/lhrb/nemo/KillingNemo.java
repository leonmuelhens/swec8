package com.github.lhrb.nemo;

import com.github.lhrb.nemo.screen.MainMenuScreen;
import com.github.lhrb.nemo.util.SoundManager;

public class KillingNemo extends AbstractGame {
	
	@Override
	public void create () {
	    super.create();
	    SoundManager.getInstance().playTrack("menu");
	    setActiveScreen(new MainMenuScreen());
	}
}
