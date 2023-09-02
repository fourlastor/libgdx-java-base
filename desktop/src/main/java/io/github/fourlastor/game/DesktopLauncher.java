package io.github.fourlastor.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.glutils.HdpiMode;
import org.lwjgl.glfw.GLFW;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration.getDisplayMode();
        long monitor = GLFW.glfwGetMonitors().get(0);
        float[] scaleX = new float[1];
        float[] scaleY = new float[1];
        GLFW.glfwGetMonitorContentScale(monitor, scaleX, scaleY);
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("Game");
        config.setHdpiMode(HdpiMode.Pixels);
        setWindowedMode(config, scaleX[0], scaleY[0]);
        new Lwjgl3Application(GdxGame.createGame(), config);
    }

    private static void setWindowedMode(Lwjgl3ApplicationConfiguration config, float scaleX, float scaleY) {
        int width = (int) (720 * scaleX);
        int height = (int) (450 * scaleY);
        config.setWindowedMode(width, height);
    }
}
