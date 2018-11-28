package com.github.lhrb.nemo;

import com.github.lhrb.nemo.screen.MainMenuScreen;

public class KillingNemo extends AbstractGame {
	
	@Override
	public void create () {
	    super.create();
	    setActiveScreen(new MainMenuScreen());
	}
}
