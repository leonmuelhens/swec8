package com.github.lhrb.nemo;

import com.github.lhrb.nemo.screen.MainMenuScreen;
import com.github.lhrb.nemo.screen.SecondLevelScreen;
import com.github.lhrb.nemo.screen.TestScreen;
import com.github.lhrb.nemo.util.SoundManager;

public class KillingNemo extends AbstractGame {
	
	@Override
	public void create () {
	    super.create();
	    SoundManager.getInstance().playTrack("menu");
	    setActiveScreen(new MainMenuScreen(true));
	    //setActiveScreen(new TestScreen());
	    //setActiveScreen(new SecondLevelScreen());
	}
}
