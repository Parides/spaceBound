package com.vj017877.spacebound;

import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Is reposnible for setting the scene and layout for the first level
 *
 * @author - Andreas Paridis
 * @version - Submition
 */
public class GameActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer; // Contains the background music
    private GameView gameView; // current game view

    /**
     * Resposnible for structuring the creating of everyone on launch
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Creates the background music and gets the sound file
        mediaPlayer = MediaPlayer.create(GameActivity.this, R.raw.backgroundmusic2);
        mediaPlayer.setLooping(true); // sets the music on loop
        mediaPlayer.start(); // starts the music

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN); //sets game into Fullscreen

        Point point = new Point(); // contains the size of the screen on X and Y axis
        getWindowManager().getDefaultDisplay().getSize(point); // gets the Size of screen

        // Initializes gameview with the correct screen dimesnions
        gameView = new GameView(this, point.x, point.y);
        setContentView(gameView); // display Surface View on screen

    }

    /**
     * Responsible for pausing the game
     */
    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.release(); //stops music
        gameView.pause(); // pauses game
    }

    /**
     * Responsible for resuming the game
     */
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume(); //resumes game
    }
}
