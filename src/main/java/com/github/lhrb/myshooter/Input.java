package com.github.lhrb.myshooter;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Input extends GLFWKeyCallback {

    // Key codes for all keys available in GLFW (max number is unsigned int)
    public static boolean[] keys = new boolean[65536];

    /**
     *
     * @param window - we do not need this in this context
     * @param key is our Key Code from GLFW (for example GLFW_KEY_SPACE = 32)
     * @param scancode - we do not need this
     * @param action - What action is happening on this key ? {release, press, repeat ...}
     * @param mods - we do not need this
     */
    public void invoke(long window, int key, int scancode, int action, int mods) {
        keys[key] = action != GLFW.GLFW_RELEASE;
    }


}
