package com.vj017877.spacebound;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Is responsible for the functionality of the high score tab of the game
 *
 * @author - Andreas Paridis
 * @version - Submition
 */
public class HighscoreActivity extends AppCompatActivity {

    HighscoreActivity highscoreActivity; // Declaration of the view

    /**
     *  Responsible for creating the view's functionality upon creation
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN); //sets game into Fullscreen

        Point point = new Point(); // contains the size of the screen on X and Y axis
        getWindowManager().getDefaultDisplay().getSize(point); // gets the Size of screen

        // Initializes gameview with the correct screen dimesnions
        highscoreActivity = new HighscoreActivity();
        setContentView(R.layout.activity_highscore);

        findViewById(R.id.arrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to home screen
                startActivity(new Intent(HighscoreActivity.this, MainActivity.class));
                finish();
            }
        });

        final SharedPreferences sharedPreferences = getSharedPreferences("game",
                MODE_PRIVATE);

        /* highscore lvl 1 */
        TextView highscorelvl1 = findViewById(R.id.lvl1score);
        // Shared preferences variable containing all need-to-be shared resources
        highscorelvl1.setText("LEVEL 1:  " + sharedPreferences.getInt("highscorelvl1",
                0));

        /* highscore lvl 2 */
        TextView highscorelvl2 = findViewById(R.id.lvl2score);
        // Shared preferences variable containing all need-to-be shared resources
        highscorelvl2.setText("LEVEL 2:  " + sharedPreferences.getInt("highscorelvl2",
                0));

        /* highscore lvl 2 */
        TextView highscorelvl3 = findViewById(R.id.lvl3score);
        // Shared preferences variable containing all need-to-be shared resources
        highscorelvl3.setText("LEVEL 3:  " + sharedPreferences.getInt("highscorelvl3",
                0));

    }
}
