package com.example.smartcity.utils;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class AnimationUtils {

    /**
     * Creates and starts a smooth translation animation for a given node.
     *
     * @param node      The Node to animate.
     * @param fromX     The starting X position.
     * @param toX       The ending X position.
     * @param fromY     The starting Y position.
     * @param toY       The ending Y position.
     * @param duration  The duration of the animation in milliseconds.
     */
    public static void createTranslateAnimation(Node node, double fromX, double toX, double fromY, double toY, double duration) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(duration), node);
        transition.setFromX(fromX);
        transition.setToX(toX);
        transition.setFromY(fromY);
        transition.setToY(toY);
        transition.setCycleCount(1); // Play only once
        transition.setAutoReverse(false); // No reverse playback
        transition.play();
    }

    /**
     * Repeats a translation animation for a given node (infinite looping).
     *
     * @param node      The Node to animate.
     * @param toX       The X position to translate to.
     * @param toY       The Y position to translate to.
     * @param duration  The duration of the animation in milliseconds.
     */
    public static void createLoopingTranslateAnimation(Node node, double toX, double toY, double duration) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(duration), node);
        transition.setByX(toX);
        transition.setByY(toY);
        transition.setCycleCount(TranslateTransition.INDEFINITE); // Infinite loop
        transition.setAutoReverse(true); // Reverse playback
        transition.play();
    }

    /**
     * Stops any ongoing animations for the given node.
     *
     * @param node The Node to stop animations for.
     */
    public static void stopAnimation(Node node) {
        node.setTranslateX(0);
        node.setTranslateY(0);
    }
}
