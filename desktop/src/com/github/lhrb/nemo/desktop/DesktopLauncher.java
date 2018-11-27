package com.github.lhrb.nemo.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.TestStage;

public class DesktopLauncher {
	public static void main (String[] arg) {
		//LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//new LwjglApplication(new KillingNemo(), config);
	    new LwjglApplication(new TestStage(), "Killing Nemo", 1200, 600);
	}
}
