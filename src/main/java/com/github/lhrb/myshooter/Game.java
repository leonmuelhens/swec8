package com.github.lhrb.myshooter;

import com.github.lhrb.myshooter.Input.*;

import org.lwjgl.glfw.*;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Game implements Runnable {
    /* Config start */
    private static int WINDOW_WIDTH;
    private static int WINDOW_HEIGHT;
    private static String GAME_NAME ;

    private Thread gameThread;

    // While game is running, this should be true
    private boolean running;
    // This is the ID for the Window
    private long gameWindow;

    /**
     * Default Constructor initializing static vars for our game
     */
    public Game () {
        GAME_NAME = "myShooter";
        WINDOW_WIDTH = 1024;
        WINDOW_HEIGHT = 768;
        gameWindow = 0;
    }


    /**
     * Starts a new Thread for the application and sets the running variable
     */
    public void start() {
        running = true;
        gameThread = new Thread(this, GAME_NAME);
        gameThread.start();
    }

    /**
     * Initializes the game with gameWindow properties and start values
     */
    public void init() {
        // Check if glfw starts correctly
        if (!glfwInit()) {
            // TODO: we have to handle if glfw does not initiate correctly
        }
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        gameWindow = glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_NAME, NULL, NULL);

        if (gameWindow == 0) {
            // TODO: we have to handle if glfw can not create a Window
        }

        //get position and center widow
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        if (vidmode != null) {
            glfwSetWindowPos(gameWindow, (vidmode.width() - WINDOW_WIDTH) / 2, (vidmode.height() - WINDOW_HEIGHT) / 2);
        } else {
            // TODO: we have to handle if if no Video Mode can be found
        }

        glfwSetKeyCallback(gameWindow, new Input());

        // Prepare the game window settings
        glfwMakeContextCurrent(gameWindow);
        glfwShowWindow(gameWindow);
        GL.createCapabilities();


        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        glEnable(GL_DEPTH_TEST);

        glfwFocusWindow(gameWindow);
        //System.out.println("OPENGL: "+ glGetString(GL_VERSION));
    }

    /**
     * This is our game Loop. we initialize, update and render continuously
     */
    public void run() {
        init();
        while (running) {
            update();
            render();

            if (glfwWindowShouldClose(gameWindow)) {
                running = false;
            }
        }
    }

    /**
     * This is the function to be run, when anything (sprites, images, stats) have to be updated
     * but just prepares everything. Rendering is done in function render
     */
    public void update() {
        glfwPollEvents();

        if (Input.keys[GLFW_KEY_SPACE]) {
            System.out.println("I pressed space and released it!");
        }
    }

    /**
     * This is used to render the GameWindow.
     */
    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glfwSwapBuffers(gameWindow);
    }

    /**
     * This is our applicatiojn start. Main is run at the beginning and start function is called from this Class
     *
     * @param args arguments passed by compiler on execution
     */
    public static void main(String[] args) {
        new Game().start();
    }
}
