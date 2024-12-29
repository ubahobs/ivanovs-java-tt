package com.tdl.googleMeet.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class AudioVideo {

    private final JavascriptExecutor js;

    /**
     * Constructor to initialize the utility with a WebDriver instance.
     *
     * @param driver The WebDriver instance to use for executing JavaScript.
     */
    public AudioVideo(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
    }

    /**
     * Validates whether an audio stream is playing.
     *
     * @return true if the audio is playing, false otherwise.
     */
    public boolean isAudioPlaying() {
        try {
            return (Boolean) js.executeScript(
                    "var audio = document.querySelector('audio');" +
                            "return audio && audio.readyState >= 2 && !audio.paused && !audio.muted;"
            );
        } catch (Exception e) {
            System.err.println("Error while validating audio stream: " + e.getMessage());
            return false;
        }
    }

    /**
     * Validates whether a video stream is playing.
     *
     * @return true if the video is playing, false otherwise.
     */
    public boolean isVideoPlaying() {
        try {
            return (Boolean) js.executeScript(
                    "var video = document.querySelector('video');" +
                            "return video && video.readyState >= 2 && video.videoWidth > 0 && video.videoHeight > 0;"
            );
        } catch (Exception e) {
            System.err.println("Error while validating video stream: " + e.getMessage());
            return false;
        }
    }
}
